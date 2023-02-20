package ua.ithillel.lms.dto;

import lombok.Data;

@Data
public class AddressDto {

  long id;
  ClientDto client;
  String country;
  String city;
  String street;
  String house;

  @Override
  public String toString() {
    return "AddressDto{" +
        "\"id\":" + id +
        ", \"country\":'" + country + '\'' +
        ", \"city\":'" + city + '\'' +
        ", \"street\":'" + street + '\'' +
        ", \"house\":'" + house + '\'' +
        '}';
  }
}
