package ua.ithillel.lms.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.ithillel.lms.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
