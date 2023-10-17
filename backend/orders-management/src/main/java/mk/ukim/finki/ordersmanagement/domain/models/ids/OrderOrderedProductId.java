package mk.ukim.finki.ordersmanagement.domain.models.ids;

import lombok.NonNull;
import mk.ukim.finki.productsmanagement.domain.models.ids.ProductId;
import mk.ukim.finki.sharedkernel.domain.base.DomainObjectId;

public class OrderOrderedProductId extends DomainObjectId {

    public OrderOrderedProductId() {
        super(ProductId.randomId(OrderOrderedProductId.class).getId());
    }

    public OrderOrderedProductId(@NonNull String uuid) {
        super(uuid);
    }

    public static OrderOrderedProductId of(String uuid) {
        return new OrderOrderedProductId(uuid);
    }
}
