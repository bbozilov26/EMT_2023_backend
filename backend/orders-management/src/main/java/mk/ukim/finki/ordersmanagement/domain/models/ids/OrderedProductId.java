package mk.ukim.finki.ordersmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class OrderedProductId extends DomainObjectId {

    public OrderedProductId() {
        super(OrderedProductId.randomId(OrderedProductId.class).getId());
    }

    public OrderedProductId(@NonNull String uuid) {
        super(uuid);
    }

    public static OrderedProductId of(String uuid) {
        return new OrderedProductId(uuid);
    }
}
