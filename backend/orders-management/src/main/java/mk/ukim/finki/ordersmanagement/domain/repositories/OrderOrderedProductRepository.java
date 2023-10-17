package mk.ukim.finki.ordersmanagement.domain.repositories;

import mk.ukim.finki.ordersmanagement.domain.models.OrderOrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderOrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderOrderedProductRepository extends JpaRepository<OrderOrderedProduct, OrderOrderedProductId> {

    @Query("select oop from OrderOrderedProduct oop where oop.order.id = :id")
    List<OrderOrderedProduct> findAllByOrderId(OrderId id);


}
