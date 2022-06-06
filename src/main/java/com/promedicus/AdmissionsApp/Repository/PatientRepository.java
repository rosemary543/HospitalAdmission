package com.promedicus.AdmissionsApp.Repository;

import com.promedicus.AdmissionsApp.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {
}
