package ly.kaizen.lad.service;

import ly.kaizen.lad.model.Customer;

import java.util.Optional;
import java.util.List;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Optional<Customer> getCustomerById(Long id);

    Optional<Customer> getCustomerByNid(String nid);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);
}
