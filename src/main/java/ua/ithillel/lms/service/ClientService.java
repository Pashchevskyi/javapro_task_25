package ua.ithillel.lms.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.ithillel.lms.dto.AddressDto;
import ua.ithillel.lms.dto.ClientDto;
import ua.ithillel.lms.dto.ClientInfoDto;
import ua.ithillel.lms.dto.ClientOrdersIdsDto;
import ua.ithillel.lms.exception.ClientNotFoundException;
import ua.ithillel.lms.model.Address;
import ua.ithillel.lms.model.Client;
import ua.ithillel.lms.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class ClientService {

  private final ClientRepository clientRepository;
  private final ObjectMapper objectMapper;

  /**
   * Adds client to database
   *
   * @param clientDto DTO of adding Client entity
   * @return DTO of added client entity
   */
  public ClientDto add(ClientDto clientDto) {
    clientDto.getAddress().setClient(clientDto);
    Client client = objectMapper.convertValue(clientDto, Client.class);
    Client savedClient = clientRepository.save(client);
    clientDto.setId(savedClient.getId());
    return objectMapper.convertValue(savedClient, ClientDto.class);
  }

  /**
   * Changes address of client
   *
   * @param clientDto  DTO of existing client entity
   * @param addressDto DTO of adding address entity
   * @return DTO of Client entity with updated Address entity
   */
  public ClientDto changeAddress(ClientDto clientDto, AddressDto addressDto) {
    Client client = objectMapper.convertValue(clientDto, Client.class);
    client.setAddress(objectMapper.convertValue(addressDto, Address.class));
    Client savedClient = clientRepository.save(client);
    clientDto.setId(savedClient.getId());
    return objectMapper.convertValue(savedClient, ClientDto.class);
  }

  /**
   * Gets orders of Client
   *
   * @param id - id of Client you want to get orders of
   * @return Client with id of the one and searching orders
   * @throws ClientNotFoundException if Client has not been found in database
   */
  public ClientDto getClientOrders(long id) throws ClientNotFoundException {
    Client client = clientRepository.findById(id)
        .orElseThrow(() -> new ClientNotFoundException(id));
    return objectMapper.convertValue(client, ClientDto.class);
  }

  /**
   * Gets info about Client
   *
   * @param clientId - ID of searching client
   * @return ClientInfoDto - object, which contains information about Client
   * @throws ClientNotFoundException if Client has not been found in database
   */
  public ClientInfoDto getClientInfo(long clientId) throws ClientNotFoundException {
    Client client = clientRepository.findById(clientId)
        .orElseThrow(() -> new ClientNotFoundException(clientId));
    return objectMapper.convertValue(client, ClientInfoDto.class);
  }

  /**
   * Gets IDs of client`s orders
   *
   * @param clientId - ID of searching Client
   * @return ClientOrdersIdsDto - object, which contains client data with IDs of client`s orders
   * @throws ClientNotFoundException if Client has not been found in database
   */
  public ClientOrdersIdsDto getClientOrdersIds(long clientId) throws ClientNotFoundException {
    Client client = clientRepository.findById(clientId)
        .orElseThrow(() -> new ClientNotFoundException(clientId));
    List<Long> clientOrdersIds = new ArrayList<>();
    client.getOrdersHistory().forEach(order -> clientOrdersIds.add(order.getId()));
    ClientOrdersIdsDto clientOrdersIdsDto = objectMapper.convertValue(client,
        ClientOrdersIdsDto.class);
    clientOrdersIdsDto.setOrdersIds(clientOrdersIds);
    return clientOrdersIdsDto;
  }

  /**
   * Gets Client object by name
   *
   * @param name name of Client
   * @return ClientDto - object, which contains client data
   */
  public ClientDto getClientByName(String name) {
    return objectMapper.convertValue(clientRepository.findByName(name), ClientDto.class);
  }
}
