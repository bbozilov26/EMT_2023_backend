package mk.ukim.finki.ordersmanagement.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.enums.OrderStatus;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "mm_order")
@Data
@AllArgsConstructor
public class Order extends AbstractEntity<OrderId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private OffsetDateTime dateClosed;
    private Double totalPrice;
    private String orderId;
    private String trackingNumber;
    private String carrier;
    private LocalDate ETA;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ur_user_id")
    private User user;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<OrderOrderedProduct> orderOrderedProducts;

    public Order() {
        super(OrderId.randomId(OrderId.class));
    }
}
