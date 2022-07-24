package br.com.customer.repository;

import br.com.customer.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    @Query("SELECT c FROM CustomerEntity c WHERE c.id = ?1")
    CustomerEntity getCustomerById(Long customerId);

    @Query("SELECT c FROM CustomerEntity c WHERE c.cpf = ?1")
    CustomerEntity findByCpf(String customerCpf);
}
