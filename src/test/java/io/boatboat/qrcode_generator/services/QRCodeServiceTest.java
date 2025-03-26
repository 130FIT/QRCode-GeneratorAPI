package io.boatboat.qrcode_generator.services;


import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Test Class for QRCodeService class
 */
@SpringBootTest
@Slf4j
public class QRCodeServiceTest {

    private final QRCodeService qrCodeService;

    @Autowired
    public QRCodeServiceTest(QRCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }


    @Test
    @DisplayName("Test case for generateBase64QRCode method")
    public void testGenerateBase64QRCode() throws IOException, WriterException {
        String inputData = "https://www.google.com";
        int size = 250;
        String base64QRCode = qrCodeService.generateBase64QRCode(inputData, size);
        log.info("Base64 QR Code : {}", base64QRCode);
        assertTrue(base64QRCode.startsWith("data:image/png;base64,"));
    }

    @Test
    @DisplayName("Test case for generateQRCode return byte_array method with size 250")
    public void testGenerateQRCode() throws IOException, WriterException {
        String inputData = "https://www.google.com";
        int size = 250;
        byte[] qrCode = qrCodeService.generateQRCode(inputData, size);
        log.info("QR Code byte array : {}", qrCode);
        assertTrue(qrCode.length > 0);
    }
}
