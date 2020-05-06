package eu.ITDC.internetprovider.service;

import eu.ITDC.internetprovider.persistence.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationFacade {

    public User getAuthentication() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
