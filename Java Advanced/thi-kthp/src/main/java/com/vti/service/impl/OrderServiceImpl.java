package com.vti.service.impl;

import com.vti.dto.OrderDTO;
import com.vti.dto.OrderItemDTO;
import com.vti.entity.*;
import com.vti.enums.DishStatus;
import com.vti.enums.OrderStatus;
import com.vti.enums.TableStatus;
import com.vti.exception.BusinessException;
import com.vti.form.OrderCreateForm;
import com.vti.form.OrderFilterForm;
import com.vti.form.OrderItemForm;
import com.vti.form.OrderUpdateForm;
import com.vti.repository.*;
import com.vti.service.IOrderService;
import com.vti.specification.OrderSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private IOrderItemRepository orderItemRepository;

    @Autowired
    private IDiningTableRepository diningTableRepository;

    @Autowired
    private IDishRepository dishRepository;

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Page<OrderDTO> findAll(OrderFilterForm form, Pageable pageable) {
        Page<Order> orderPage = orderRepository.findAll(OrderSpecification.buildWhere(form), pageable);
        return orderPage.map(this::convertToDTO);
    }

    @Override
    public OrderDTO findById(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Đơn order không tồn tại with ID: " + id).build());
        return convertToDTO(order);
    }

    @Override
    @Transactional
    public OrderDTO createOrder(OrderCreateForm form, String currentUsername) {
        DiningTable table = diningTableRepository.findById(form.getTableId())
                .orElseThrow(() -> BusinessException.builder().message("Bàn ăn không tồn tại with ID: " + form.getTableId()).build());

        Account user = null;
        if (currentUsername != null) {
            user = accountRepository.findByUsername(currentUsername);
        }

        // Cập nhật trạng thái bàn sang OCCUPIED khi có khách tạo order
        table.setStatus(TableStatus.OCCUPIED);
        diningTableRepository.save(table);

        Order order = Order.builder()
                .diningTable(table)
                .user(user)
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.ZERO)
                .orderItems(new ArrayList<>())
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemForm itemForm : form.getOrderItems()) {
            Dish dish = dishRepository.findById(itemForm.getDishId())
                    .orElseThrow(() -> BusinessException.builder().message("Món ăn không tồn tại with ID: " + itemForm.getDishId()).build());

            if (dish.getStatus() == DishStatus.UNAVAILABLE) {
                throw BusinessException.builder().message("Món ăn '" + dish.getName() + "' hiện đang tạm hết hàng").build();
            }

            BigDecimal itemTotal = dish.getPrice().multiply(BigDecimal.valueOf(itemForm.getQuantity()));
            total = total.add(itemTotal);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .dish(dish)
                    .quantity(itemForm.getQuantity())
                    .price(dish.getPrice())
                    .build();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);

        return convertToDTO(savedOrder);
    }

    @Override
    @Transactional
    public OrderDTO updateOrder(Integer id, OrderUpdateForm form) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Đơn order không tồn tại with ID: " + id).build());

        if (order.getStatus() != OrderStatus.CREATED) {
            throw BusinessException.builder().message("Chỉ được phép cập nhật đơn order ở trạng thái CREATED").build();
        }

        // Clear previous items
        order.getOrderItems().clear();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemForm itemForm : form.getOrderItems()) {
            Dish dish = dishRepository.findById(itemForm.getDishId())
                    .orElseThrow(() -> BusinessException.builder().message("Món ăn không tồn tại with ID: " + itemForm.getDishId()).build());

            if (dish.getStatus() == DishStatus.UNAVAILABLE) {
                throw BusinessException.builder().message("Món ăn '" + dish.getName() + "' hiện đang tạm hết hàng").build();
            }

            BigDecimal itemTotal = dish.getPrice().multiply(BigDecimal.valueOf(itemForm.getQuantity()));
            total = total.add(itemTotal);

            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .dish(dish)
                    .quantity(itemForm.getQuantity())
                    .price(dish.getPrice())
                    .build();

            order.getOrderItems().add(orderItem);
        }

        order.setTotalAmount(total);
        Order updatedOrder = orderRepository.save(order);

        return convertToDTO(updatedOrder);
    }

    @Override
    @Transactional
    public OrderDTO payOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Đơn order không tồn tại with ID: " + id).build());

        if (order.getStatus() != OrderStatus.CREATED) {
            throw BusinessException.builder().message("Đơn order này đã được thanh toán hoặc bị hủy").build();
        }

        order.setStatus(OrderStatus.PAID);
        order.setPaymentDate(LocalDateTime.now());

        // Cập nhật bàn ăn về trạng thái Trống (EMPTY)
        DiningTable table = order.getDiningTable();
        if (table != null) {
            table.setStatus(TableStatus.EMPTY);
            diningTableRepository.save(table);
        }

        Order paidOrder = orderRepository.save(order);
        return convertToDTO(paidOrder);
    }

    @Override
    @Transactional
    public OrderDTO cancelOrder(Integer id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> BusinessException.builder().message("Đơn order không tồn tại with ID: " + id).build());

        if (order.getStatus() != OrderStatus.CREATED) {
            throw BusinessException.builder().message("Không thể hủy đơn order đã thanh toán hoặc đã bị hủy từ trước").build();
        }

        order.setStatus(OrderStatus.CANCELLED);

        // Cập nhật bàn ăn về trạng thái Trống (EMPTY)
        DiningTable table = order.getDiningTable();
        if (table != null) {
            table.setStatus(TableStatus.EMPTY);
            diningTableRepository.save(table);
        }

        Order cancelledOrder = orderRepository.save(order);
        return convertToDTO(cancelledOrder);
    }

    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> itemDTOs = order.getOrderItems().stream().map(item -> OrderItemDTO.builder()
                .id(item.getId())
                .dishId(item.getDish().getId())
                .dishName(item.getDish().getName())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .subTotal(item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .build()).collect(Collectors.toList());

        return OrderDTO.builder()
                .id(order.getId())
                .tableId(order.getDiningTable() != null ? order.getDiningTable().getId() : null)
                .tableNumber(order.getDiningTable() != null ? order.getDiningTable().getTableNumber() : null)
                .userId(order.getUser() != null ? order.getUser().getId() : null)
                .userName(order.getUser() != null ? order.getUser().getUsername() : null)
                .createdDate(order.getCreatedDate())
                .paymentDate(order.getPaymentDate())
                .status(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .orderItems(itemDTOs)
                .build();
    }
}
