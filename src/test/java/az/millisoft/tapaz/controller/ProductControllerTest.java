package az.millisoft.tapaz.controller;

import az.millisoft.tapaz.dto.CategoryRequest;
import az.millisoft.tapaz.dto.ProductRequest;
import az.millisoft.tapaz.entity.Product;
import az.millisoft.tapaz.exception.ProductNotFound;
import az.millisoft.tapaz.security.JwtService;
import az.millisoft.tapaz.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class,
excludeAutoConfiguration = SecurityAutoConfiguration.class)
class ProductControllerTest {

    private final static String MAIN_URL = "/api/product";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean// Teqlid etmek
    private ProductService productService;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;


    @Test
    void getAll() throws Exception {



        mockMvc
                .perform(get(MAIN_URL+"?page=" + 0 + "&count="+ 10))
                .andExpect(status().isOk());

    }


    @Test
    void getById() throws Exception {

        final Integer ID = 100;

        Product product = Product.builder()
                .id(ID)
                .name("name")
                .description("description")
                .addedDate(LocalDate.now())
                .price(10.0)
                .build();

        when(productService.getById(ID)).thenReturn(product);

        String stringProduct =
                objectMapper.writeValueAsString(product);

        mockMvc
                .perform(get(MAIN_URL + "/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().string(stringProduct));


    }

    @Test
    void getById_SHOULD_RETURN_NOT_FOUND() throws Exception {

        final Integer ID = 100;

        doThrow(ProductNotFound.class).when(productService).getById(ID);

        mockMvc
                .perform(get(MAIN_URL + "/" + ID))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetAll() {
    }

    @Test
    void add_SHOULD_RETURN_CREATED() throws Exception {
        ProductRequest productRequest =
                new ProductRequest(
                        "NAME",
                        "DESCRIPTION",
                        10.0,
                        new CategoryRequest(1));

        String productRequestAsString = objectMapper.writeValueAsString(productRequest);

        mockMvc
                .perform(
                        post(MAIN_URL)
                                .contentType("application/json")
                                .content(productRequestAsString))
                .andExpect(status().isCreated());
    }

    @Test
    void add_SHOULD_RETURN_BAD_REQUEST() throws Exception {

        ProductRequest productRequest =
                new ProductRequest(
                        "NAME",
                        "DESCRIPTION",
                        10.0,
                        null);

        String productRequestAsString = objectMapper.writeValueAsString(productRequest);

        mockMvc
                .perform(
                        post(MAIN_URL)
                                .contentType("application/json")
                                .content(productRequestAsString))
                .andExpect(status().isBadRequest());
    }

}