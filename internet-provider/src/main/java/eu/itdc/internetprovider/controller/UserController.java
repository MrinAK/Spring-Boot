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
    public List<UserDTO> getAll() {
        return userService.getAll();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateRoles(@RequestBody UserUpdateRolesDTO userUpdateRolesDTO, @PathVariable("userId") Long userId) {
        userService.updateRolesByUserId(userUpdateRolesDTO, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
