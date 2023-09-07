package com.devskiller.audit.repository;

import com.devskiller.audit.model.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepository extends CrudRepository<ProductOrder, Long> {
}
