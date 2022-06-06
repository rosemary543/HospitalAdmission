package com.promedicus.AdmissionsApp.Controller;

import com.promedicus.AdmissionsApp.Entity.Patient;
import com.promedicus.AdmissionsApp.Service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping("/api")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        return new ResponseEntity<>(patientService.getAllPatients(), HttpStatus.OK);
    }

    @GetMapping("/patients/{id}")
    public ResponseEntity<Optional<Patient>> getPatientById(@PathVariable("id") Long patientId) {
        return new ResponseEntity<>(patientService.getPatient(patientId), HttpStatus.OK);
    }

    @PostMapping("/patients")
    public ResponseEntity<Patient> createPatient(@Validated @RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.createPatient(patient), HttpStatus.CREATED);
    }

    @PutMapping("/patients/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id,@Validated @RequestBody Patient patient) {
        return new ResponseEntity<>(patientService.updatePatient(id,patient), HttpStatus.OK);
    }

    @DeleteMapping("/patients/{id}")
    public ResponseEntity<HttpStatus> deletePatientById(@PathVariable("id") Long patientId) {
       patientService.deletePatient(patientId);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

