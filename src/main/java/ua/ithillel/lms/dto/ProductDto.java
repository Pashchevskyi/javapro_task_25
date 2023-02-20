package ua.ithillel.lms.dto;

import lombok.Data;

@Data
public class ProductDto {

  long id;
  String name;
  String description;
  double price;

  @Override
  public String toString() {
    return "ProductDto{" +
        "\"id\":" + id +
        ", \"name\":'" + name + '\'' +
        ", \"description\":'" + description + '\'' +
        ", \"price\":" + price +
        '}';
  }
}
