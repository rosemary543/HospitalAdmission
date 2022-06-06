package com.promedicus.AdmissionsApp.unittest.service;

import com.promedicus.AdmissionsApp.Entity.Patient;
import com.promedicus.AdmissionsApp.Exception.DataNotFoundException;
import com.promedicus.AdmissionsApp.Repository.PatientRepository;
import com.promedicus.AdmissionsApp.Service.PatientService;
import com.promedicus.AdmissionsApp.Utils.AppConstants;
import com.promedicus.AdmissionsApp.Utils.PatientSex;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class PatientServiceTest {

    @InjectMocks
    private PatientService service;

    @Mock
    private PatientRepository repository;

    @Test
    void whenPatientNotExist_thenGetPatient_returnDataNotFoundException(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> service.getPatient(1L));
    }

    @Test
    void whenPatientNotExist_thenDeletePatient_returnDataNotFoundException(){
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(DataNotFoundException.class, () -> service.deletePatient(1L));
    }
    @Test
    public void createPatient_invalidRequestParams_thenNotsave() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setSex(String.valueOf(PatientSex.FEMALE));
        patient.setSourceSystem(AppConstants.SOURCE_SYSTEM_INTERNAL);
        patient.setDateOfBirth(Date.valueOf(LocalDate.now().plusDays(6)));
        patient.setDateOfAdmission(Date.valueOf(LocalDate.now()));

        var response = repository.save(patient);
        assertNull(response);
    }
}
