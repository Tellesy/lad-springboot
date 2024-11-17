package ly.kaizen.lad.controller;

import ly.kaizen.lad.dto.*;
import ly.kaizen.lad.model.Customer;
import ly.kaizen.lad.service.CustomerService;
import ly.kaizen.lad.service.NADService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/individuals")
public class IndividualsController {

    private final NADService nadService;
    private final CustomerService customerService;

    public IndividualsController(NADService nadService, CustomerService customerService) {
        this.nadService = nadService;
        this.customerService = customerService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<NADResponseDTO> enrollIndividual(@RequestBody EnrollIndividualRequestDTO request) {
        NADResponseDTO response = nadService.enrollIndividual(request);

        if ("success".equalsIgnoreCase(response.getStatus())) {
            Customer customer = new Customer();
            customer.setName(request.getFirstName() + " " + request.getLastName());
            customer.setNid(request.getNid());
            customerService.createCustomer(customer);
        }

        return ResponseEntity.status(response.getStatus().equals("success") ? 201 : 400).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<NADResponseDTO> updateIndividual(@RequestBody UpdateIndividualRequestDTO request) {
        NADResponseDTO response = nadService.updateIndividual(request);

        if ("success".equalsIgnoreCase(response.getStatus())) {
            customerService.getCustomerByNid(request.getNid()).ifPresent(customer -> {
                customer.setName(request.getFirstName() + " " + request.getLastName());
                customerService.updateCustomer(customer.getId(), customer);
            });
        }

        return ResponseEntity.status(response.getStatus().equals("success") ? 200 : 400).body(response);
    }

    @GetMapping("/lookup")
    public ResponseEntity<NADResponseDTO> lookupIndividual(
            @RequestParam String schema,
            @RequestParam String id
    ) {
        NADResponseDTO response = nadService.lookupIndividual(schema, id);
        return ResponseEntity.ok(response);
    }
}
