package internshipProject.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import internshipProject.dao.Entity.Address;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByAddressId(long addressId);

    Address save(Address address);

}


