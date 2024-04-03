package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SystemAdmin")
@Getter
@Setter

public class SystemAdmin extends User{
    
}
