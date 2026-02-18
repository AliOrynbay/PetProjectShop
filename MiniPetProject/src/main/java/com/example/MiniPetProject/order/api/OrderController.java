package com.example.MiniPetProject.order.api;

import com.example.MiniPetProject.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<List<OrderResponseDto>> getAll() {
        return new ResponseEntity<>(orderService.getOrders(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailsResponseDto> getOneById(@PathVariable int id) {
        return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/deactivate")
    public ResponseEntity<OrderDetailsResponseDto> deactivateOrder(@PathVariable int id) {
        orderService.deactivateOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<OrderDetailsResponseDto> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        return new ResponseEntity<>(orderService.createOrder(orderRequestDto), HttpStatus.OK);
    }
}
