package com.example.backend.entity;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "OrganizationAdmin")
@Getter
@Setter
public class OrganizationAdmin extends User{
//    private Long orgid;
    
   @OneToOne
   @JoinColumn(name="org_ID",referencedColumnName = "id")
   private Organization orgid;
}

