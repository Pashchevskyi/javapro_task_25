package ua.ithillel.lms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.lms.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

  Client findByName(String name);
}
