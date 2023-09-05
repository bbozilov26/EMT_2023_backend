package mk.ukim.finki.ordersmanagement.domain.dtos;

import lombok.Data;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderedProductId;
import mk.ukim.finki.ordersmanagement.domain.models.ids.ShoppingCartId;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;

import java.time.OffsetDateTime;
import java.util.List;

@Data
public class ShoppingCartDTO {
    private final ShoppingCartId id;
    private final OffsetDateTime dateCreated;
    private final OffsetDateTime dateModified;
    private final Double totalPrice;
    private final List<OrderedProductId> orderedProductIds;
    private final UserId userId;
}
