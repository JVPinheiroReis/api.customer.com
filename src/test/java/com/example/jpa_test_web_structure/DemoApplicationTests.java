package com.example.jpa_test_web_structure;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpa_test_web_structure.domain.CustomerDTO;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;

@SpringBootTest
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvcTester mvc;

    @Test
    void contextLoads() {}

    @Test
    void shouldReturnAllCustomers() {
        MvcTestResult result = mvc
            .get()
            .uri("/customers")
            .accept(MediaType.APPLICATION_JSON)
            .exchange();

        assertThat(result)
            .hasStatusOk()
            .bodyJson()
            .convertTo(InstanceOfAssertFactories.list(CustomerDTO.class))
            .hasSize(5);
    }
}
