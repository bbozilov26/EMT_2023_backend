package mk.ukim.finki.ordersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderCreationDTO;
import mk.ukim.finki.ordersmanagement.domain.models.Order;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.ordersmanagement.domain.repositories.OrderRepository;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    private static final String ORDER_ID_PREFIX = "ORD";
    private static final String TRACKING_NUMBER_PREFIX = "TRK";

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findById(OrderId id){
        return orderRepository.findById(id);
    }

    public Order create(OrderCreationDTO orderCreationDTO){
        Order order = new Order();
        order.setDateCreated(OffsetDateTime.now());
        order.setOrderId(generateOrderID());
        order.setTrackingNumber(generateTrackingNumber());
        order.setUser(userService.findById(orderCreationDTO.getUserId()).get());

        return fillProperties(order, orderCreationDTO);
    }

    public Order edit(OrderId id, OrderCreationDTO orderCreationDTO){
        return fillProperties(findById(id).get(), orderCreationDTO);
    }

    public Order fillProperties(Order order, OrderCreationDTO orderCreationDTO){
        order.setDateModified(OffsetDateTime.now());
        order.setTotalPrice(orderCreationDTO.getTotalPrice());
        order.setDescription(orderCreationDTO.getDescription());
        order.setOrderStatus(orderCreationDTO.getOrderStatus());

        return orderRepository.save(order);
    }

    public static String generateOrderID() {
        // Generate a random 10-digit number
        Random rand = new Random();
        long orderId = Math.abs(rand.nextLong());

        String orderIdStr = String.format("%010d", orderId);
        return ORDER_ID_PREFIX + orderIdStr;
    }

    public static String generateTrackingNumber() {
        // Generate a random tracking number with a specific format
        Random rand = new Random();
        String trackingNumber = TRACKING_NUMBER_PREFIX;
        long randomTrackingNumber = Math.abs(rand.nextLong());
        String trackingNumberStr = String.format("%010d", randomTrackingNumber);
        trackingNumber += trackingNumberStr + "MK"; // Appending a country code for example

        return trackingNumber;
    }

    public Order cancelOrConfirmOrder(OrderId id, OrderCreationDTO orderCreationDTO){
        Order order = findById(id).get();
        order.setOrderStatus(orderCreationDTO.getOrderStatus());
        order.setDateClosed(OffsetDateTime.now());

        return orderRepository.save(order);
    }

    public void deleteOrderHistory(OrderId id){
        orderRepository.deleteById(id);
    }
}
