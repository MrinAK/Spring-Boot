package eu.internetprovider.controller;

import eu.internetprovider.service.ContractService;
import eu.internetprovider.service.dto.ContractDTO;
import eu.internetprovider.service.dto.ContractInformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/api/v1/contracts")
@RestController
public class ContractController {

    private final ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public List<ContractInformationDTO> getAll(){
       return contractService.findAll();
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<Void> create( @RequestBody ContractDTO contractDTO){
        contractService.create(contractDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{contractId}")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ContractDTO findById (@PathVariable("contractId") Long contractId){
        return (ContractDTO) contractService.findAll();
    }


}
