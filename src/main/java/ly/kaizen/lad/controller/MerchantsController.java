package ly.kaizen.lad.controller;

import ly.kaizen.lad.dto.*;
import ly.kaizen.lad.model.Merchant;
import ly.kaizen.lad.service.MerchantService;
import ly.kaizen.lad.service.NADService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/merchants")
public class MerchantsController {

    private final NADService nadService;
    private final MerchantService merchantService;

    public MerchantsController(NADService nadService, MerchantService merchantService) {
        this.nadService = nadService;
        this.merchantService = merchantService;
    }

    @PostMapping("/enroll")
    public ResponseEntity<NADResponseDTO> enrollMerchant(@RequestBody EnrollMerchantRequestDTO request) {
        NADResponseDTO response = nadService.enrollMerchant(request);

        if ("success".equalsIgnoreCase(response.getStatus())) {
            Merchant merchant = new Merchant();
            merchant.setName(request.getName());
            merchant.setNid(request.getNid());
            merchant.setTradeLicenseNumber(request.getTradeLicenseNumber());
            merchantService.createMerchant(merchant);
        }

        return ResponseEntity.status(response.getStatus().equals("success") ? 201 : 400).body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<NADResponseDTO> updateMerchant(@RequestBody UpdateMerchantRequestDTO request) {
        NADResponseDTO response = nadService.updateMerchant(request);

        if ("success".equalsIgnoreCase(response.getStatus())) {
            merchantService.getMerchantByNid(request.getNid()).ifPresent(merchant -> {
                merchant.setName(request.getName());
                merchant.setTradeLicenseNumber(request.getTradeLicenseNumber());
                merchantService.updateMerchant(merchant.getId(), merchant);
            });
        }

        return ResponseEntity.status(response.getStatus().equals("success") ? 200 : 400).body(response);
    }

    @GetMapping("/lookup")
    public ResponseEntity<NADResponseDTO> lookupMerchant(
            @RequestParam String schema,
            @RequestParam String id
    ) {
        NADResponseDTO response = nadService.lookupMerchant(schema, id);
        return ResponseEntity.ok(response);
    }
}
