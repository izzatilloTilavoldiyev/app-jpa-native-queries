package uz.pdp.appjpareletionships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appjpareletionships.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
