package io.boatboat.qrcode_generator.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class QRCodeService {

    public byte[] generateQRCode(String data, int size) throws IOException, WriterException {
        log.info("Generating QR code byte_array for data: {} , size: {}", data, size);
        return generateQRCodeAsByteArray(data, size);
    }

    public String generateBase64QRCode(String data, int size) throws IOException, WriterException {
        log.info("Generating QR code base64 for data: {} , size :{} ", data, size);
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(generateQRCodeAsByteArray(data, size));
    }

    private byte[] generateQRCodeAsByteArray(String data, int size) throws WriterException, IOException {
        /* Create the ByteMatrix for the QR-Code that encodes the given String */
        HashMap<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hintMap.put(EncodeHintType.MARGIN, 1);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
//        hintMap.put(EncodeHintType.QR_VERSION, 24);
        log.debug("Create the ByteMatrix for the QR-Code that encodes the given String with ErrorCorrectionLevel.L");
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size, hintMap);

        /* Make the BufferedImage that are to hold the QRCode */
        int matrixSize = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixSize, matrixSize, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();


        /* Paint and save the image using the ByteMatrix */
        log.debug("Paint and save the image using the ByteMatrix");
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixSize, matrixSize);
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        log.debug("Convert the image to byte array and return it");

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(image, "png", baos);

        log.debug("Image converted to byte array");
        return baos.toByteArray();
    }
}
