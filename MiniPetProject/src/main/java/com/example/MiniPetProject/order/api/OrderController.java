package com.example.MiniPetProject.order.api;

import com.example.MiniPetProject.order.service.OrderService;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("")
    public ResponseEntity<List<OrderResponseDto>> getAll(){
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsResponseDto> getOneById(@PathVariable int id){
        return new ResponseEntity<>(orderService.getOrderById(id) , HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderDetailsResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto){
        return new ResponseEntity<>(orderService.createOrder(orderRequestDto), HttpStatus.CREATED);
    }
}
