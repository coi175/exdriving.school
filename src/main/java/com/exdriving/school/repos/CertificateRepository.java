package com.exdriving.school.repos;

import com.exdriving.school.domain.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с сертификатами
 */
public interface CertificateRepository extends JpaRepository<Certificate, Integer> {
}
