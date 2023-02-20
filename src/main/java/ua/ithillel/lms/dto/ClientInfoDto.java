package ua.ithillel.lms.dto;

import lombok.Data;

@Data
public class ClientInfoDto {

  long id;
  String name;
  String email;
  String phone;

  AddressDto address;

  @Override
  public String toString() {
    return "ClientInfoDto{" +
        "\"id\":" + id +
        ", \"name\":'" + name + '\'' +
        ", \"email\":'" + email + '\'' +
        ", \"phone\":'" + phone + '\'' +
        ", \"address\":{" + address + "}" +
        '}';
  }
}
