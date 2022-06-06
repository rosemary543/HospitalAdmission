package com.promedicus.AdmissionsApp.unittest.controller;

import com.promedicus.AdmissionsApp.Controller.PatientController;
import com.promedicus.AdmissionsApp.Entity.Patient;
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
import org.springframework.http.HttpStatus;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class PatientControllerTest {

    @InjectMocks
    private PatientController controller;

    @Mock
    private PatientService service;

    @Test
    public void givenPatientExists_whenGetPatient_thenReturn200() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setSex(String.valueOf(PatientSex.FEMALE));
        patient.setSourceSystem(AppConstants.SOURCE_SYSTEM_INTERNAL);
        patient.setDateOfBirth(Date.valueOf(LocalDate.now().minusDays(6)));
        patient.setDateOfAdmission(Date.valueOf(LocalDate.now()));

        when(service.getPatient(1L)).thenReturn(Optional.of(patient));
        var response = controller.getPatientById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void givenPatientExists_whenDeletePatient_thenReturn204() {
        var response = controller.deletePatientById(1L);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }



}
