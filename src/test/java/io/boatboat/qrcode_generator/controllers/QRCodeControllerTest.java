package io.boatboat.qrcode_generator.controllers;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Slf4j
@AutoConfigureMockMvc
public class QRCodeControllerTest {

    private static final String GENERATE_QRCODE_ENDPOINT = "/api/qrcode/generate";
    private final MockMvc mockMvc;

    @Autowired
    public QRCodeControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    @DisplayName("Test QRCodeController with Invalid Type")
    public void TestQRCodeControllerGenerateQRCode() throws Exception {
        String url = GENERATE_QRCODE_ENDPOINT + "?type=invalid";
        MvcResult result = performMethodGET(encodeUrl(url), 400);
        assertTrue(result.getResponse().getContentAsString().contains("Type must be BYTE_ARRAY or BASE64"));
    }


    private MvcResult performMethodGET(String url) throws Exception {
        return performMethodGET(url, 200);
    }

    private MvcResult performMethodGET(String url, Integer expectedStatus) throws Exception {
        var builder = MockMvcRequestBuilders.get(url)
                .accept("application/json");
        return mockMvc.perform(builder)
                .andExpect(status().is(expectedStatus))
                .andReturn();
    }

    private String encodeUrl(String url) {
        return url.replace(" ", "%20");
    }
}
