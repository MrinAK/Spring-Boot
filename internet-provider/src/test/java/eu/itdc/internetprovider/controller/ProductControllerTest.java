package eu.itdc.internetprovider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.itdc.internetprovider.persistence.entity.User;
import eu.itdc.internetprovider.service.AuthenticationFacade;
import eu.itdc.internetprovider.service.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationFacade authenticationFacade;


    @Test
    @WithMockUser(roles = "ADMIN")
    void create() throws Exception {

//        Given
        when(authenticationFacade.getAuthentication()).thenReturn(new User("","","",new HashSet<>()));
        ProductDTO productDTO = new ProductDTO();
        productDTO.setBandwidth(100);
        productDTO.setFee(BigDecimal.valueOf(10d));
        productDTO.setName("SILVER");


//        When
        mockMvc.perform(post("/api/v1/products/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDTO)))
                .andExpect(status().isCreated());

//        Then
    }

}