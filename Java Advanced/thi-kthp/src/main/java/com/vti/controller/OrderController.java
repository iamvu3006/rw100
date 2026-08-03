package com.vti.controller;

import com.vti.dto.OrderDTO;
import com.vti.form.OrderCreateForm;
import com.vti.form.OrderFilterForm;
import com.vti.form.OrderUpdateForm;
import com.vti.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private IOrderService orderService;

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> findAll(OrderFilterForm form, Pageable pageable) {
        return new ResponseEntity<>(orderService.findAll(form, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable Integer id) {
        return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderCreateForm form, Principal principal) {
        String username = (principal != null) ? principal.getName() : null;
        OrderDTO createdOrder = orderService.createOrder(form, username);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer id, @Valid @RequestBody OrderUpdateForm form) {
        OrderDTO updatedOrder = orderService.updateOrder(id, form);
        return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<OrderDTO> payOrder(@PathVariable Integer id) {
        OrderDTO paidOrder = orderService.payOrder(id);
        return new ResponseEntity<>(paidOrder, HttpStatus.OK);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@PathVariable Integer id) {
        OrderDTO cancelledOrder = orderService.cancelOrder(id);
        return new ResponseEntity<>(cancelledOrder, HttpStatus.OK);
    }
}
