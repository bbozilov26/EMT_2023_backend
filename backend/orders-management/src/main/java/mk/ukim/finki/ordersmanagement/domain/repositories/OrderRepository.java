package mk.ukim.finki.ordersmanagement.domain.repositories;

import mk.ukim.finki.ordersmanagement.domain.models.Order;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
