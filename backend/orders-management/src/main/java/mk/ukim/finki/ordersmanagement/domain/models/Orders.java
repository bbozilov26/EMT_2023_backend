package mk.ukim.finki.ordersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.models.enums.OrderStatus;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;
import mk.ukim.finki.usersmanagement.domain.models.Users;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "mm_orders", schema = "metamodels")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders extends AbstractEntity<OrderId> {

    private OffsetDateTime dateCreated;
    private OffsetDateTime dateModified;
    private OffsetDateTime dateClosed;
    private Double totalPrice;
    private String orderId; //TODO: UUID random generated string number, ex. AliExpress, Amazon
    private String trackingNumber; //TODO: UUID random generated string, ex. AliExpress, Amazon
    private String description;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToOne
    private Users user;
}
