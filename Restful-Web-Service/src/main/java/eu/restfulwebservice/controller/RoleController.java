package eu.restfulwebservice.controller;

import eu.restfulwebservice.service.RoleService;
import eu.restfulwebservice.service.dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/roles")
@RestController
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/")
    public ResponseEntity<Void> create(@RequestBody @Validated RoleDTO roleDTO) {
        roleService.create(roleDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<RoleDTO> getAll(){
        return roleService.findAll();
    }

    @GetMapping("/{roleId}")
    public RoleDTO findById(@PathVariable("roleId") Long roleId) {
        return roleService.findById(roleId);
    }


    @PutMapping("/{roleId}")
    public ResponseEntity<Object> updateById(@PathVariable("roleId") Long roleId, @RequestBody @Validated RoleDTO roleDTO){
        return ResponseEntity.ok(roleService.updateById(roleId, roleDTO));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteById(@PathVariable("roleId") Long roleId) {
        roleService.deleteById(roleId);
        return new ResponseEntity<>(HttpStatus.GONE);
    }
}
