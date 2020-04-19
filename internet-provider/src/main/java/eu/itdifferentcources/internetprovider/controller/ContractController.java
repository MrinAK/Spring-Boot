package eu.itdifferentcources.internetprovider.controller;

import eu.itdifferentcources.internetprovider.service.ContractService;
import eu.itdifferentcources.internetprovider.service.dto.ContractDTO;
import eu.itdifferentcources.internetprovider.service.dto.ContractInformationDTO;
import eu.itdifferentcources.internetprovider.service.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contracts")
public class ContractController {

    private final ContractService contractService;



    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping("/")
    public List<ContractInformationDTO> getAll(){
       return contractService.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Void> create( @RequestBody ContractDTO contractDTO){
        contractService.create(contractDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{contractId}")
    public ContractDTO findById (@PathVariable("contractId") Long contractId){
        return (ContractDTO) contractService.findAll();
    }
}
