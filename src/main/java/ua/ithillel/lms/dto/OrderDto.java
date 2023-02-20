package ua.ithillel.lms.dto;

import java.util.List;
import lombok.Data;

@Data
public class OrderDto {

  long id;

  ClientDto client;
  List<OrderItemDto> orderItems;

  @Override
  public String toString() {
    StringBuilder orderItemsSB = new StringBuilder();
    for (OrderItemDto orderItemDto : orderItems) {
      orderItemsSB.append(orderItemDto.toString());
    }
    return "OrderDto{" +
        "\"id\":" + id +
        ", \"client\":{" + client + "}" +
        ", \"orderItems\":[" + orderItemsSB + "]" +
        '}';
  }
}
