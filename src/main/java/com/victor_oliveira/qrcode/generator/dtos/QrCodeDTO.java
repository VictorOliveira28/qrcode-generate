package com.victor_oliveira.qrcode.generator.dtos;

public record QrCodeDTO(Long id, String text, byte[] imageData) {
}
