package com.foodtruck.qr_food_truck_ordering.controller;

import com.foodtruck.qr_food_truck_ordering.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qr")
public class QRCodeController {

    @Autowired // Inject the QRCodeService
    private QRCodeService qrCodeService;

    // POST request to generate the QR code
    @PostMapping("/generate")
    public ResponseEntity<byte[]> generateQRCode(@RequestBody String url) {
        try {
            byte[] qrCodeImage = qrCodeService.generateQRCode(url, 300, 300); // Generate the QR code with 300x300 size
            return ResponseEntity.ok()
                    .header("Content-Type", "image/png")
                    .body(qrCodeImage); // Return the image as the response body
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build(); // In case of error
        }
    }
}
