package com.example.backend.entity;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends User {

    private Date dob;
    private USER_GENDER gender = USER_GENDER.OTHER;
    private String address;

    public Patient(Long id, String username, String password, USER_TYPE type, String email, String phone, String name,
            Date dob, USER_GENDER gender, String address) {
        super(id, username, password, type, email, phone, name);
        this.dob = dob;
        this.gender = gender;
        this.address = address;
    }

}
