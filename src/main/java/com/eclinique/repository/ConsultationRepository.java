package com.eclinique.repository;

import com.eclinique.model.Consultation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    @EntityGraph(attributePaths = {"patient", "patient.organisme", "medecin", "prescriptions", "prescriptions.medicament"})
    List<Consultation> findAllByOrderByDateConsultationDesc();

    @EntityGraph(attributePaths = {"patient", "patient.organisme", "medecin", "prescriptions", "prescriptions.medicament"})
    List<Consultation> findByPatientIdOrderByDateConsultationDesc(Long patientId);

    @Override
    @EntityGraph(attributePaths = {"patient", "patient.organisme", "medecin", "prescriptions", "prescriptions.medicament"})
    java.util.Optional<Consultation> findById(Long id);
    List<Consultation> findByMedecinId(Long medecinId);

    // Pagination en deux temps : les identifiants de la page, puis ces consultations avec leurs relations
    // (un graphe avec collection sur une requête paginée ferait paginer Hibernate en mémoire)
    @Query("select c.id from Consultation c")
    Page<Long> findPageIds(Pageable pageable);

    @EntityGraph(attributePaths = {"patient", "patient.organisme", "medecin", "prescriptions", "prescriptions.medicament"})
    List<Consultation> findByIdIn(List<Long> ids);
}
