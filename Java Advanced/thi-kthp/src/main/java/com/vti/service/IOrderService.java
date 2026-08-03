package com.vti.service;

import com.vti.dto.OrderDTO;
import com.vti.form.OrderCreateForm;
import com.vti.form.OrderFilterForm;
import com.vti.form.OrderUpdateForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IOrderService {
    Page<OrderDTO> findAll(OrderFilterForm form, Pageable pageable);

    OrderDTO findById(Integer id);

    OrderDTO createOrder(OrderCreateForm form, String currentUsername);

    OrderDTO updateOrder(Integer id, OrderUpdateForm form);

    OrderDTO payOrder(Integer id);

    OrderDTO cancelOrder(Integer id);
}
