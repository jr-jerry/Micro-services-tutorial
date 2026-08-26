package com.Ducat.order_service.Service;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.Ducat.order_service.DTO.GenericRequestDTO;
import com.Ducat.order_service.DTO.GenericResponseDTO;
import com.Ducat.order_service.DTO.OrderRequestDTO;
import com.Ducat.order_service.DTO.OrderResponseDTO;
import com.Ducat.order_service.DTO.QuantityResponseDTO;
import com.Ducat.order_service.Entity.OrderEntity;
import com.Ducat.order_service.Repository.OrderRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;


    public ResponseEntity<GenericResponseDTO<OrderResponseDTO>> saveBook(OrderRequestDTO orderRequestDTO){
        //order --request --> check book (ava ->yes/no )-->yest->order --save-->succesfully 
        String bookBaseURI="http://localhost:8080/api/book-service";
        String bookName=orderRequestDTO.getBookName();
        String bookAuthro=orderRequestDTO.getBookAuthor();
        int bookQuantity=orderRequestDTO.getBookQuantity();

       ResponseEntity<GenericResponseDTO> resonseEntity=restTemplate.postForEntity(bookBaseURI+"/quantity", orderRequestDTO, GenericResponseDTO.class);
       GenericResponseDTO genericResponseDTO= resonseEntity.getBody();
       QuantityResponseDTO quantityResponseDTO=(QuantityResponseDTO)genericResponseDTO.getData();

       int avaliableQuantity=quantityResponseDTO.getBookQuantity();
       int requiredQuantity=orderRequestDTO.getBookQuantity();

       if(avaliableQuantity<requiredQuantity){
            throw new OutofStockException("Book km avaliable hai ");
       }

        OrderEntity orderEntity=this.modelMapper.map(orderRequestDTO,OrderEntity.class);
       
       OrderEntity savedOrder= orderRepo.save(orderEntity);

       OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
    //    order
    }
    
}
