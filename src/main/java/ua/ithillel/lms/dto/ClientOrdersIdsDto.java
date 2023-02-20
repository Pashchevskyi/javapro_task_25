package ua.ithillel.lms.dto;

import java.util.List;
import lombok.Data;

@Data
public class ClientOrdersIdsDto {

  long id;
  String name;
  String email;
  String phone;

  AddressDto address;

  List<Long> ordersIds;

  @Override
  public String toString() {
    StringBuilder ordersIDsSB = new StringBuilder();
    for (Long orderId : ordersIds) {
      ordersIDsSB.append(orderId);
    }
    return "ClientOrdersIdsDto{" +
        "\"id\":" + id +
        ", \"name\":'" + name + '\'' +
        ", \"email\":'" + email + '\'' +
        ", \"phone\":'" + phone + '\'' +
        ", \"address\":{" + address + "}" +
        ", \"ordersIDs\":[" + ordersIDsSB + "]" +
        '}';
  }
}
