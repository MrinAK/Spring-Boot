package eu.itdc.internetprovider.controller;

import eu.itdc.internetprovider.service.UserService;
import eu.itdc.internetprovider.service.dto.UserDTO;
import eu.itdc.internetprovider.service.dto.UserUpdateRolesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDTO> updateById(@PathVariable("userId") Long userId, @RequestBody UserUpdateRolesDTO userUpdateRolesDTO) {
        userService.updateById(userId, userUpdateRolesDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
