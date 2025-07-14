package com.victor_oliveira.qrcode.generator.controllers;

import com.victor_oliveira.qrcode.generator.services.QrCodeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qrcode")
public class QrCodeController {

    private final QrCodeService qrCodeService;

    public QrCodeController(QrCodeService qrCodeService) {
        this.qrCodeService = qrCodeService;
    }

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> create(@RequestParam("text") String text){
        try{
            byte[] qrImage = qrCodeService.generateQRCode(text, 200, 200);
            return ResponseEntity.ok().body(qrImage);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
