package com.exdriving.school.service;

import com.exdriving.school.domain.Client;
/**
 * Сервис, через который контроллер работает с сертификатами в базе данных
 */
public interface CertificateService {
    /**
     * Добавляет новый сертификат в базу данных
     * @param client
     * @param certificateNumber
     * @param certificateMark
     */
    public void addCertificate(Client client, Integer certificateNumber, Integer certificateMark);
}
