package ly.kaizen.lad.service.impl;

import ly.kaizen.lad.model.Customer;
import ly.kaizen.lad.repository.CustomerRepository;
import ly.kaizen.lad.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> getCustomerByNid(String nid) {
        return customerRepository.findByNid(nid);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return customerRepository.findById(id)
                .map(existingCustomer -> {
                    existingCustomer.setName(customer.getName());
                    existingCustomer.setEncryptedPin(customer.getEncryptedPin());
                    existingCustomer.setAccounts(customer.getAccounts());
                    return customerRepository.save(existingCustomer);
                })
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    @Override
    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
