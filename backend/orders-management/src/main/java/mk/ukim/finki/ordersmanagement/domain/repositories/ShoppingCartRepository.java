package mk.ukim.finki.ordersmanagement.domain.repositories;

import mk.ukim.finki.ordersmanagement.domain.models.ShoppingCart;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, ShoppingCartId> {

    @Query("select sc from ShoppingCart sc where sc.id = :id and sc.user.id = :userId")
    ShoppingCart findByIdAndUserId(ShoppingCartId id, UserId userId);
}
