package com.foodtruck.qr_food_truck_ordering.controller;

import com.foodtruck.qr_food_truck_ordering.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr")
public class QRCodeController {

    @Autowired // Inject the QRCodeService
    private QRCodeService qrCodeService;

    @GetMapping("generate")
    public ResponseEntity<byte[]> generateQRCode() {
        try {
            String staticUrl = "http://localhost:8080"; // Static URL
            byte[] qrCode = qrCodeService.generateQRCode(staticUrl, 200, 200); // Generate QR code

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            return new ResponseEntity<>(qrCode, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
