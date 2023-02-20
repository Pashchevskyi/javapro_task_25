package ua.ithillel.lms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.lms.dto.OrderDto;
import ua.ithillel.lms.model.Order;
import ua.ithillel.lms.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final ObjectMapper objectMapper;

  /**
   * Adds an Order to database
   *
   * @param orderDto DTO of adding Order
   * @return DTO of added Order
   */
  public OrderDto add(OrderDto orderDto) {
    Order order = objectMapper.convertValue(orderDto, Order.class);
    Order savedOrder = orderRepository.save(order);
    orderDto.setId(savedOrder.getId());
    return objectMapper.convertValue(savedOrder, OrderDto.class);
  }
}
