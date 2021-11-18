package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Certificate;
import com.exdriving.school.domain.Client;
import com.exdriving.school.repos.CertificateRepository;
import com.exdriving.school.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CertificateServiceIml implements CertificateService {
    @Autowired
    CertificateRepository certificateRepository;

    @Override
    public void addCertificate(Client client, Integer certificateNumber, Integer certificateMark) {
        // создаем новый обект сертификата
        Certificate certificate = new Certificate();
        // устанавливаем, что он относится к этому клиенту
        certificate.setClient(client);
        // устанавливаем сегдоняшную дату
        certificate.setDate(new Date());
        // устанавливаем оценку
        certificate.setMark(certificateMark);
        // устанавливаем номер
        certificate.setNumber(certificateNumber);
        // сохраняем в базе данных
        certificateRepository.saveAndFlush(certificate);
    }
}
