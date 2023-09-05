package mk.ukim.finki.ordersmanagement.domain.converters;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderDTO;
import mk.ukim.finki.ordersmanagement.domain.models.Order;
import mk.ukim.finki.sharedkernel.utils.NullableUtils;
import mk.ukim.finki.usersmanagement.domain.converters.UserConverter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final UserConverter userConverter;

    public OrderDTO toOrderDTO(Order order){
        return new OrderDTO(
                order.getId(),
                order.getDateCreated(),
                order.getDateModified(),
                order.getDateClosed(),
                order.getTotalPrice(),
                order.getOrderId(),
                order.getTrackingNumber(),
                order.getDescription(),
                order.getOrderStatus(),
                NullableUtils.getIfNotNull(order.getUser(), userConverter::toUserDTO)
        );
    }

    public List<OrderDTO> toOrderDTOList(List<Order> orders){
        return orders.stream()
                .map(this::toOrderDTO)
                .collect(Collectors.toList());
    }
}
