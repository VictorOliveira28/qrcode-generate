package com.victor_oliveira.qrcode.generator.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.victor_oliveira.qrcode.generator.dtos.QrCodeDTO;
import com.victor_oliveira.qrcode.generator.services.QrCodeService;

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
    
    @PostMapping("/save")
    public ResponseEntity<Map<String, Object>> save(@RequestParam("text") String text){
    	
    	try {
    		QrCodeDTO dto = qrCodeService.saveQRCode(text, 200, 200);
    		
    		Map<String, Object> response = new HashMap<>();
    		response.put("id", dto.id());
    		response.put(text, dto.text());
    		
    		return ResponseEntity.ok(response);
    	} catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    	}
    }
    
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id){  
    	
    	Optional<QrCodeDTO> qrOpt = qrCodeService.findById(id);
    	if(qrOpt.isEmpty()) {
    		
    		return ResponseEntity.notFound().build();
    	}
    	
    	QrCodeDTO dto = qrOpt.get();
    	HttpHeaders headers = new HttpHeaders();
    	
    	headers.setContentType(MediaType.IMAGE_PNG);
    	headers.setContentDisposition(ContentDisposition.attachment()
    			.filename("qrcode_" + dto.id() + ".png")
    			.build());
    	
    	return new ResponseEntity<>(dto.imageData(), headers, HttpStatus.OK);
    	
    }
}
