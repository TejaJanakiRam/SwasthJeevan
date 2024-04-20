package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.entity.Doctor;
import com.example.backend.entity.Speciality;
import com.example.backend.repository.DoctorRepository;
import com.example.backend.repository.PatientRepository;
import com.example.backend.repository.SpecialityRepository;
import com.example.backend.service.DoctorService;
import com.example.backend.service.OrganizationService;
import com.example.backend.service.PatientService;

@RestController
public class QeueuController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecialityRepository specialityRepository ;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService ;

    @Autowired
    private PatientRepository patientRepository ;


    @GetMapping("/api/queue/find_doc/spec")
    public ResponseEntity<List<Long>> doctorsBySpeciality(@RequestParam("spec_code") String specCode) throws Exception {
        Speciality speciality = specialityRepository.findBySpecialityCode(specCode);
        List<Doctor> doctorsl = doctorRepository.findBySpeciality(speciality);
        List<Long> doctoridlist = new ArrayList<>() ;
        for(int i =0 ; i <doctorsl.size() ;i++){
            doctoridlist.add(i,doctorsl.get(i).getId());
        }
        return ResponseEntity.ok(doctoridlist);
    }

    @GetMapping("/api/queue/find_doc/id")
    public ResponseEntity<Doctor> doctorsbyId(@RequestParam("id") Long id) throws Exception{
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor = optionalDoctor.get();
        return ResponseEntity.ok(doctor); 
    }
    @GetMapping("/api/queue/get_doc")
    public ResponseEntity<Queue<Long>> getdoctorqueue(@RequestParam("spec_code") String specCode) throws Exception {
        return ResponseEntity.ok(doctorService.getQueue(specCode));
    }

    @GetMapping("api/queue/add_doc")
    public ResponseEntity<Queue<Long>> add_doctor_to_queue(@RequestParam("spec_code") String specCode, @RequestParam("doctor_id") Long doctorId) throws Exception{
        doctorService.addtoqueue(specCode, doctorId);
        return ResponseEntity.ok(doctorService.getQueue(specCode));
    }


    @GetMapping("/api/queue/delete_doc")
    public ResponseEntity<Queue<Long>> removedoctor(@RequestParam("spec_code") String specCode, @RequestParam("doctor_id") Long doctorId) throws Exception{
        doctorService.removeFromQueue(specCode, doctorId);
        return ResponseEntity.ok(doctorService.getQueue(specCode));
    }

    @GetMapping("/api/queue/add_patient")
    public ResponseEntity<Queue<Long>> add_patient_to_queue(@RequestParam("spec_code") String specCode, @RequestParam("patient_id") Long patientId) throws Exception{
        patientService.addtoqueue(specCode, patientId);
        return ResponseEntity.ok(patientService.getQueue(specCode));
    }

    @GetMapping("/api/queue/delete_patient")
    public ResponseEntity<Queue<Long>> remove_patient_to_queue(@RequestParam("spec_code") String specCode, @RequestParam("patient_id") Long patientId) throws Exception{
        patientService.removeFromQueue(specCode, patientId);
        return ResponseEntity.ok(patientService.getQueue(specCode));
    }

    @GetMapping("/api/queue/get_patient")
    public ResponseEntity<Queue<Long>> getpatientqueue(@RequestParam("spec_code") String specCode) throws Exception {
        return ResponseEntity.ok(patientService.getQueue(specCode));
    }        
}
