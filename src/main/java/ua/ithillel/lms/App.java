package ua.ithillel.lms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ua.ithillel.lms.dto.AddressDto;
import ua.ithillel.lms.dto.ClientDto;
import ua.ithillel.lms.dto.OrderDto;
import ua.ithillel.lms.dto.OrderItemDto;
import ua.ithillel.lms.dto.ProductDto;
import ua.ithillel.lms.exception.ClientNotFoundException;
import ua.ithillel.lms.service.ClientService;
import ua.ithillel.lms.service.OrderService;
import ua.ithillel.lms.service.ProductService;

/**
 * Hello world!
 */
@SpringBootApplication
@Slf4j
public class App {

  @Autowired
  private ClientService clientService;

  @Autowired
  private OrderService orderService;

  @Autowired
  private ProductService productService;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @EventListener(ApplicationReadyEvent.class)
  public void run() throws Exception {
    System.out.println("Select options");
    System.out.println("1 - Add client");
    System.out.println("2 - Change address");
    System.out.println("3 - Client`s orders");
    System.out.println("4 - Client`s info");
    System.out.println("5 - IDs of client`s orders");
    System.out.println("6 - Add order");
    System.out.println("7 - Add product");
    Scanner keyboard = new Scanner(System.in);
    int digitPressed = keyboard.nextInt();
    if (digitPressed == 1) {
      addClient();
    }
    if (digitPressed == 2) {
      changeAddress();
    }
    if (digitPressed == 3) {
      getClientOrders();
    }
    if (digitPressed == 4) {
      getClientInfo();
    }
    if (digitPressed == 5) {
      getClientOrdersIds();
    }
    if (digitPressed == 6) {
      addOrder();
    }
    if (digitPressed == 7) {
      addProduct();
    }
  }

  private void addClient() {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Client data");
    System.out.print("Name: ");
    String clientName = keyboard.next();
    System.out.print("E-mail: ");
    String clientEmail = keyboard.next();
    System.out.print("Phone: ");
    String clientPhone = keyboard.next();
    ClientDto clientDto = new ClientDto();
    clientDto.setName(clientName);
    clientDto.setEmail(clientEmail);
    clientDto.setPhone(clientPhone);
    System.out.println("Client address data");
    System.out.print("Country: ");
    String clientCountry = keyboard.next();
    System.out.print("City: ");
    String clientCity = keyboard.next();
    System.out.print("Street: ");
    String clientStreet = keyboard.next();
    System.out.print("House: ");
    String clientHouse = keyboard.next();
    AddressDto addressDto = new AddressDto();
    addressDto.setCountry(clientCountry);
    addressDto.setCity(clientCity);
    addressDto.setStreet(clientStreet);
    addressDto.setHouse(clientHouse);
    addressDto.setClient(clientDto);
    clientDto.setAddress(addressDto);
    clientService.add(clientDto);
    System.out.println("Client ID#" + clientDto.getId() + " info:");
    try {
      System.out.println(clientService.getClientInfo(clientDto.getId()));
    } catch (ClientNotFoundException e) {
      log.error(e.getMessage());
    }
  }

  private void changeAddress() {
    System.out.print("Input name of client you want to change address: ");
    Scanner keyboard = new Scanner(System.in);
    String name = keyboard.next();
    ClientDto clientDto = clientService.getClientByName(name);
    AddressDto addressDto = clientDto.getAddress();
    System.out.println("Current address of client:");
    System.out.println(addressDto);
    System.out.println("Client address data you want to change.");
    System.out.print("Country: ");
    String clientCountry = keyboard.next();
    addressDto.setCountry(clientCountry);
    System.out.print("City: ");
    String clientCity = keyboard.next();
    addressDto.setCity(clientCity);
    System.out.print("Street: ");
    String clientStreet = keyboard.next();
    addressDto.setStreet(clientStreet);
    System.out.print("House: ");
    String clientHouse = keyboard.next();
    addressDto.setHouse(clientHouse);
    addressDto.setClient(clientDto);
    clientDto.setAddress(addressDto);
    clientService.changeAddress(clientDto, addressDto);
    System.out.println("Client address data changed:");
    System.out.println(clientDto.getAddress());
  }

  private void getClientOrders() {
    System.out.print("Input name of client you want to get orders: ");
    Scanner keyboard = new Scanner(System.in);
    String name = keyboard.next();
    ClientDto clientDto = clientService.getClientByName(name);
    try {
      System.out.println(clientService.getClientOrders(clientDto.getId()));
    } catch (ClientNotFoundException e) {
      log.error(e.getMessage());
    }
  }

  private void getClientInfo() {
    System.out.print("Input name of client you want to get info: ");
    Scanner keyboard = new Scanner(System.in);
    String name = keyboard.next();
    ClientDto clientDto = clientService.getClientByName(name);
    try {
      System.out.println(clientService.getClientInfo(clientDto.getId()));
    } catch (ClientNotFoundException e) {
      log.error(e.getMessage());
    }
  }

  private void getClientOrdersIds() {
    System.out.print("Input name of client you want to get orders IDs: ");
    Scanner keyboard = new Scanner(System.in);
    String name = keyboard.next();
    ClientDto clientDto = clientService.getClientByName(name);
    try {
      System.out.println(clientService.getClientOrdersIds(clientDto.getId()));
    } catch (ClientNotFoundException e) {
      log.error(e.getMessage());
    }
  }

  private void addOrder() {
    System.out.print("Input name of client you want to add order for: ");
    Scanner keyboard = new Scanner(System.in);
    String clientName = keyboard.next();
    ClientDto clientDto = clientService.getClientByName(clientName);
    OrderDto orderDto = new OrderDto();
    orderDto.setClient(clientDto);

    System.out.print("Input products count: ");
    int productsCount = keyboard.nextInt();
    List<OrderItemDto> orderItems = new ArrayList<>();
    for (int i = 0; i < productsCount; i++) {
      System.out.println("Product#" + (i + 1) + " data");
      System.out.print("Name: ");
      String productName = keyboard.next();
      System.out.print("Description: ");
      String productDescription = keyboard.next();
      System.out.print("Price: ");
      double productPrice = keyboard.nextDouble();
      ProductDto productDto = new ProductDto();
      productDto.setName(productName);
      productDto.setDescription(productDescription);
      productDto.setPrice(productPrice);
      productService.add(productDto);

      OrderItemDto orderItemDto = new OrderItemDto();
      orderItemDto.setOrder(orderDto);
      orderItemDto.setProduct(productDto);
      orderItemDto.setCount(i + 1);
      orderItems.add(orderItemDto);
    }
    orderDto.setOrderItems(orderItems);
    orderService.add(orderDto);
  }

  private void addProduct() {
    System.out.println("Input product info.");
    Scanner keyboard = new Scanner(System.in);
    System.out.print("Name: ");
    String productName = keyboard.next();
    System.out.print("Description: ");
    String productDescription = keyboard.next();
    System.out.print("Price: ");
    double productPrice = keyboard.nextDouble();
    ProductDto productDto = new ProductDto();
    productDto.setName(productName);
    productDto.setDescription(productDescription);
    productDto.setPrice(productPrice);
    productService.add(productDto);
  }
}
