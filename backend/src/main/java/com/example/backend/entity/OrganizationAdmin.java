package com.example.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
   @JoinColumn(name="Org_ID",referencedColumnName = "id")
   private Organization orgid;
}

