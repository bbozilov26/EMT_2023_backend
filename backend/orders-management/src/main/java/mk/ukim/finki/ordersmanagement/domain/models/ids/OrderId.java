package mk.ukim.finki.ordersmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class OrderId extends DomainObjectId {

    public OrderId() {
        super(OrderId.randomId(OrderId.class).getId());
    }

    public OrderId(@NonNull String uuid) {
        super(uuid);
    }

    public static OrderId of(String uuid) {
        return new OrderId(uuid);
    }
}
