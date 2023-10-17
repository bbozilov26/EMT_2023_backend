package mk.ukim.finki.ordersmanagement.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderOrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.sharedkernel.domain.base.AbstractEntity;

import javax.persistence.*;

@Entity
@Table(name = "mm_order_ordered_product")
@Data
@AllArgsConstructor
public class OrderOrderedProduct extends AbstractEntity<OrderOrderedProductId> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mm_order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mm_ordered_product_id")
    private OrderedProduct orderedProduct;

    public OrderOrderedProduct() {
        super(OrderOrderedProductId.randomId(OrderOrderedProductId.class));
    }
}
