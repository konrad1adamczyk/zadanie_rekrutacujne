package com.devskiller.audit.service;

import com.devskiller.audit.consumer.ProductOrderEvent;
import com.devskiller.audit.model.ProductOrder;
import com.devskiller.audit.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ProductOrderEventProcessor {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    public void processEvent(ProductOrderEvent event) {
        if (event == null) {
            throw new IllegalArgumentException("ProductOrderEvent cannot be null");
        }

        ProductOrder order = new ProductOrder(null, event.userLogin(), event.productId(), event.creationTime());
        productOrderRepository.save(order);
    }
}
