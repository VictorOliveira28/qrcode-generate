package com.victor_oliveira.qrcode.generator.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.victor_oliveira.qrcode.generator.dtos.QrCodeDTO;
import com.victor_oliveira.qrcode.generator.entities.QrCodeEntity;
import com.victor_oliveira.qrcode.generator.repository.QrCodeRepository;

@Service
public class QrCodeService {	
		
	private QrCodeRepository repository;	
		
	public QrCodeService(QrCodeRepository repository) {
		this.repository = repository;
	}
	
	
    public byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", output);

        return output.toByteArray();
    }
    
    public QrCodeDTO saveQRCode(String text, int width, int height) throws WriterException, IOException {
    	byte[] qrImage = generateQRCode(text, width, height);
    	
    	QrCodeEntity entity = new QrCodeEntity();
    	
    	entity.setText(text);
    	entity.setImageData(qrImage);
    	
    	QrCodeEntity savedEntity = repository.save(entity);
    	
    	return copyToDTO(savedEntity);
    }
    
    public Optional<QrCodeDTO> findById(Long id){
    	return repository.findById(id).map(this::copyToDTO);
    }
    
    private QrCodeDTO copyToDTO(QrCodeEntity entity) {
    	return new QrCodeDTO(entity.getId(), entity.getText(), entity.getImageData());
    }
    

}
