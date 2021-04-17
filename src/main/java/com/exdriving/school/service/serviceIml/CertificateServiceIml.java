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
        Certificate certificate = new Certificate();
        certificate.setClient(client);
        certificate.setDate(new Date());
        certificate.setMark(certificateMark);
        certificate.setNumber(certificateNumber);
        certificateRepository.saveAndFlush(certificate);
    }
}
