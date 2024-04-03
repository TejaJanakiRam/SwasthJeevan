package com.example.backend.entity;

import java.sql.Date;

import javax.print.DocFlavor.STRING;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Patient")
@Getter
@Setter
public class Patient extends User{
    // private Long PatientId;

    private Date dob;
    private USER_GENDER gender = USER_GENDER.OTHER;
    private String address;
}
