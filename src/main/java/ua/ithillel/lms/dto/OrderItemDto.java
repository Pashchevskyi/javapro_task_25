package ua.ithillel.lms.dto;

import lombok.Data;

@Data
public class OrderItemDto {

  long id;

  OrderDto order;

  ProductDto product;

  int count;

  @Override
  public String toString() {
    return "OrderItemDto{" +
        "\"id\":" + id +
        ", \"order\":{" + order + "}" +
        ", \"product\":{" + product + "}" +
        ", \"count\":" + count +
        '}';
  }
}
