package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Sys-admin")
@Getter
@Setter


public class Sys_admin extends User{
    
}
