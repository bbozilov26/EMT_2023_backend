package mk.ukim.finki.ordersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.Users;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mm_shopping_carts", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCarts extends AbstractEntity<ShoppingCartId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Double totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private Users user;
}
