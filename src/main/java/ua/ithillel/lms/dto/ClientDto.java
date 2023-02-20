package ua.ithillel.lms.dto;

import java.util.List;
import lombok.Data;

@Data
public class ClientDto {

  long id;
  String name;
  String email;
  String phone;

  AddressDto address;

  List<OrderDto> ordersHistory;

  @Override
  public String toString() {
    StringBuilder ordersHistorySB = new StringBuilder();
    for (OrderDto orderDto : ordersHistory) {
      ordersHistorySB.append(orderDto);
    }
    return "\"ClientDto\":{" +
        "\"id\":" + id +
        ", \"name\":'" + name + '\'' +
        ", \"email\":'" + email + '\'' +
        ", \"phone\":'" + phone + '\'' +
        ", \"address\":{" + address + "}" +
        ", \"ordersHistory\":[" + ordersHistorySB + "]" +
        '}';
  }
}
