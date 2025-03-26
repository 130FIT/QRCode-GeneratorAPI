package io.boatboat.qrcode_generator.controllers;

import com.google.zxing.WriterException;
import io.boatboat.qrcode_generator.exceptions.ValidationException;
import io.boatboat.qrcode_generator.services.QRCodeService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/qrcode")
@RequiredArgsConstructor
@Slf4j
public class QRCodeController {

    private final QRCodeService qrCodeService;

    /**
     * Generate QR Code
     * <p>
     * This endpoint generates a QR code and returns it in one of two formats:
     * - byte[]: The raw byte array of the QR code image.
     * - Base64: The QR code image encoded in Base64.
     *
     * @return ResponseEntity<?> containing the QR code in the specified format.
     */
    @GetMapping("generate")
    public ResponseEntity<?> generateQRCode(
            /* set required is false because we have handle error message */
            @NotEmpty(message = "data is required") @RequestParam(name = "data", required = false) String data,
            @RequestParam(name = "type", defaultValue = "BYTE_ARRAY")
            @Pattern(regexp = "byte_array|BYTE_ARRAY|BASE64|base64", message = "Type must be BYTE_ARRAY or BASE64")
            String typeStr,
            @Min(value = 20, message = "size must be greater than 20")
            @RequestParam(name = "size", defaultValue = "250") int size
    ) throws IOException, WriterException {
        log.info("API Generating QR code for type: {} , data: {} , size : {}", typeStr, data, size);
        String responseType = typeStr.toUpperCase();
        return switch (responseType) {
            case "BYTE_ARRAY" ->
                /* Generate QR code as byte array */
                    ResponseEntity.ok()
                            .contentType(MediaType.IMAGE_PNG)
                            .header("Content-Disposition", "attachment; filename=\"qrcode.png\"")
                            .body(qrCodeService.generateQRCode(data, size));
            case "BASE64" ->
                /* Generate QR code as Base64 */
                    ResponseEntity.ok()
                            .contentType(MediaType.TEXT_PLAIN)
                            .body(qrCodeService.generateBase64QRCode(data, size));
            default -> throw new ValidationException("Invalid type should be BYTE_ARRAY or BASE64");
        };
    }

}
