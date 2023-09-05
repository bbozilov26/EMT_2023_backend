package mk.ukim.finki.ordersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.User;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "mm_shopping_carts", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCart extends AbstractEntity<ShoppingCartId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private Double totalPrice;

//    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private List<OrderedProduct> orderedProducts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private User user;
}
