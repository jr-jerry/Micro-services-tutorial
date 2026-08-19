package com.ducat.order_server.Config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "inventory-service",
    url = "http://localhost:8081",
    path = "inventory"
)
public interface FeignClientInterface {
    @GetMapping
    public String getRequest();
}
