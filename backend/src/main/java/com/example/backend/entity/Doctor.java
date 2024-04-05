package com.example.backend.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Doctor")
@Getter
@Setter
public class Doctor extends User{
    
   @ManyToOne
   @JoinColumn(name="org_Id", referencedColumnName = "id")
   private Organization organization;

   @ManyToOne
   @JoinColumn(name="spec_Id", referencedColumnName = "id")
   private Speciality speciality;
   
    private USER_GENDER gender = USER_GENDER.OTHER;

    private String registrationNum;
}