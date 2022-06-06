package com.promedicus.AdmissionsApp.Service;

import com.promedicus.AdmissionsApp.Entity.Patient;
import com.promedicus.AdmissionsApp.Exception.DataNotFoundException;
import com.promedicus.AdmissionsApp.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.promedicus.AdmissionsApp.Utils.AppConstants.SOURCE_SYSTEM_INTERNAL;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatient(Long patientId) {
        Optional<Patient> patient = patientRepository.findById(patientId);
        if (patient.isEmpty())
            throw new DataNotFoundException("Patient with id " + patientId + " does not exist");
        return patient;
    }

    public Patient createPatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public Patient updatePatient(Long id, Patient patientData) {
        Optional<Patient> _patient = patientRepository.findById(id);
        Patient patient = null;
        if (_patient.isEmpty())
            throw new DataNotFoundException("Patient with id " + id + " does not exist");
        else {
            patient = _patient.get();
            patient.setSex(patientData.getSex());
            patient.setName(patientData.getName());
            patient.setDateOfBirth(patientData.getDateOfBirth());
            patient.setDateOfAdmission(patientData.getDateOfAdmission());
            patient.setId(patient.getId());
            patient.setSourceSystem(patient.getSourceSystem());
            if (patient.getSourceSystem() != null && patient.getSourceSystem().equals(SOURCE_SYSTEM_INTERNAL)){
                patient.setCategory(patientData.getCategory());
            }else{
                patient.setCategory(patient.getCategory());
            }
            patientRepository.save(patient);
        }
        return patient;
    }

    public void deletePatient(Long patientId) {
        if (getPatient(patientId).isPresent()) {
            patientRepository.deleteById(patientId);
        }
    }
}
