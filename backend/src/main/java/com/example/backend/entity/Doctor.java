package com.example.backend.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Doctor")
@Getter
@Setter
public class Doctor extends User{
    // private Long PatientId;
    @OneToOne
   @JoinColumn(name="Org_ID",referencedColumnName = "id")
   private Organization orgid;

   @OneToOne
   @JoinColumn(name="Spec_ID",referencedColumnName = "id")
   private Speciality specid;
   
    private Date dob;
    private USER_GENDER gender = USER_GENDER.OTHER;
    private String address;
}