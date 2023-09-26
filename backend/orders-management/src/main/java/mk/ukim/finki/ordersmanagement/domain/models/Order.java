package mk.ukim.finki.ordersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.enums.OrderStatus;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.User;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "mm_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbstractEntity<OrderId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private OffsetDateTime dateClosed;
    private Double totalPrice;
    private String orderId;
    private String trackingNumber;
    private String description;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ur_user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<OrderedProduct> orderedProducts;
}
