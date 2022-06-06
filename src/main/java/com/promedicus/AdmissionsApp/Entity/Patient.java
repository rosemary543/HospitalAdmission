package com.promedicus.AdmissionsApp.Entity;

import com.promedicus.AdmissionsApp.Utils.PatientCategory;
import com.promedicus.AdmissionsApp.Utils.PatientSex;
import com.promedicus.AdmissionsApp.Utils.ValueOfEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;


@Entity
@Setter
@Getter
@ToString
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Valid
    @NotNull(message = "Date of Birth cannot be empty")
    @PastOrPresent(message = "Date of birth cannot be a future date")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @CreationTimestamp
    private Date dateOfAdmission ;

    @NotNull
    @ValueOfEnum(enumClass = PatientSex.class, message = "Invalid entry for patient sex. Please enter the valid type :FEMALE, MALE, INTERSEX, UNKNOWN")
    private String  sex;

    @NotNull
    @ValueOfEnum(enumClass = PatientCategory.class, message = "Invalid entry for Patient Category. Please enter the valid type: NORMAL, INPATIENT, EMERGENCY, OUTPATIENT")
    private String category;

    @NotNull(message="Please provide sourceSystem code as INTERNAL or hospital code if external application")
    private String sourceSystem;
}
