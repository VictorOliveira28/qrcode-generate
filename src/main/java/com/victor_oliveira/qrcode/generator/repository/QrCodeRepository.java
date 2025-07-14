package com.victor_oliveira.qrcode.generator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.victor_oliveira.qrcode.generator.entities.QrCodeEntity;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCodeEntity, Long> {
}
