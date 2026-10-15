package com.eclinique.repository;

import com.eclinique.model.PaiementEmploye;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaiementEmployeRepository extends JpaRepository<PaiementEmploye, Long> {
    List<PaiementEmploye> findAllByOrderByDatePaiementDesc();
    @org.springframework.data.jpa.repository.EntityGraph(attributePaths = {"employe", "effectuePar"})
    org.springframework.data.domain.Page<PaiementEmploye> findAllBy(org.springframework.data.domain.Pageable pageable);
    List<PaiementEmploye> findByDatePaiementGreaterThanEqualAndDatePaiementLessThan(java.time.LocalDateTime debut, java.time.LocalDateTime fin);
    List<PaiementEmploye> findByEmployeIdOrderByDatePaiementDesc(Long employeId);
}