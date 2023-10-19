package mk.ukim.finki.ordersmanagement.services.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.ordersmanagement.domain.dtos.OrderCreationDTO;
import mk.ukim.finki.ordersmanagement.domain.exceptions.InsufficientCreditException;
import mk.ukim.finki.ordersmanagement.domain.models.Order;
import mk.ukim.finki.ordersmanagement.domain.models.OrderOrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.OrderedProduct;
import mk.ukim.finki.ordersmanagement.domain.models.ids.OrderId;
import mk.ukim.finki.ordersmanagement.domain.repositories.OrderOrderedProductRepository;
import mk.ukim.finki.ordersmanagement.domain.repositories.OrderRepository;
import mk.ukim.finki.usersmanagement.domain.models.User;
import mk.ukim.finki.usersmanagement.domain.models.ids.UserId;
import mk.ukim.finki.usersmanagement.domain.repositories.UserRepository;
import mk.ukim.finki.usersmanagement.services.impl.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderedProductService orderedProductService;
    private final OrderOrderedProductRepository orderOrderedProductRepository;

    private static final String ORDER_ID_PREFIX = "ORD";
    private static final String TRACKING_NUMBER_PREFIX = "TRK";
    private final UserRepository userRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Optional<Order> findById(OrderId id){
        return orderRepository.findById(id);
    }

    public List<Order> findAllByUser(UserId userId){
        return orderRepository.findAllByUserId(userId);
    }

    public Order create(OrderCreationDTO orderCreationDTO) throws ParseException {
        Order order = new Order();

        User user = userService.findById(orderCreationDTO.getUserId()).get();
        if(user.getCreditBalance() < orderCreationDTO.getTotalPrice()){
            throw new InsufficientCreditException();
        }
        double creditBalance = user.getCreditBalance();
        double totalPrice = orderCreationDTO.getTotalPrice();
        double difference = creditBalance - totalPrice;
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        String formattedDifferenceString = decimalFormat.format(difference);
        Double formattedDifference = decimalFormat.parse(formattedDifferenceString).doubleValue();
        user.setCreditBalance(formattedDifference);
        user = userService.save(user);

        OffsetDateTime dateCreated = OffsetDateTime.now();
        order.setDateCreated(dateCreated);
        order.setOrderId(generateOrderID());
        order.setTrackingNumber(generateTrackingNumber());
        order.setTotalPrice(orderCreationDTO.getTotalPrice());
        order.setUser(user);
        order.setCarrier(orderCreationDTO.getCarrier());

        Integer daysToAdd = orderCreationDTO.getEta();
        LocalDate dateCreatedLD = dateCreated.toLocalDate();
        LocalDate ETA = dateCreatedLD.plusDays(daysToAdd);
        order.setETA(ETA);

        orderRepository.save(order);

        List<OrderedProduct> orderedProducts = orderCreationDTO.getOrderedProductIds()
                .stream()
                .map(orderedProductId ->
                        orderedProductService
                                .findByIdAndUserId(orderedProductId, orderCreationDTO.getUserId()))
                .collect(Collectors.toList());
        orderedProducts = orderedProductService.completelyRemoveProductsFromShoppingCart(orderedProducts);
        List<OrderOrderedProduct> savedOrderOrderedProducts = setOrderedProductsForOrder(orderedProducts, order);
        order.setOrderOrderedProducts(savedOrderOrderedProducts);

        return fillProperties(order, orderCreationDTO);
    }

    public Order edit(OrderId id, OrderCreationDTO orderCreationDTO){
        return fillProperties(findById(id).get(), orderCreationDTO);
    }

    public Order fillProperties(Order order, OrderCreationDTO orderCreationDTO){
        order.setDateModified(OffsetDateTime.now());
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

    public List<OrderOrderedProduct> setOrderedProductsForOrder(List<OrderedProduct> orderedProducts, Order order){
        return orderedProducts
                .stream()
                .map(orderedProduct -> {
                    OrderOrderedProduct orderOrderedProduct = new OrderOrderedProduct();
                    orderOrderedProduct.setOrderedProduct(orderedProduct);
                    orderOrderedProduct.setOrder(order);
                    return orderOrderedProductRepository.save(orderOrderedProduct);
                })
                .collect(Collectors.toList());
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
