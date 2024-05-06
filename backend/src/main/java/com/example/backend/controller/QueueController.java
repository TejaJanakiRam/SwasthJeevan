package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class QueueController {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/api/queue/find_doc/spec")
    public ResponseEntity<List<Long>> doctorsBySpeciality(@RequestParam("spec_code") String specCode) throws Exception {
        Speciality speciality = specialityRepository.findBySpecialityCode(specCode);
        List<Doctor> doctorsl = doctorRepository.findBySpeciality(speciality);
        List<Long> doctoridlist = new ArrayList<>();
        for (int i = 0; i < doctorsl.size(); i++) {
            doctoridlist.add(i, doctorsl.get(i).getId());
        }
        return ResponseEntity.ok(doctoridlist);
    }

    @GetMapping("/api/queue/find_doc/id")
    public ResponseEntity<Doctor> doctorsbyId(@RequestParam("id") Long id) throws Exception {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor = optionalDoctor.get();
        return ResponseEntity.ok(doctor);
    }

    // Get the list of doctor IDs for the specified spec_code
    @GetMapping("/api/queue/get_doc")
    public ResponseEntity<Queue<Long>> getdoctorqueue(@RequestParam("spec_code") String specCode) throws Exception {
        return ResponseEntity.ok(doctorService.getQueue(specCode));
    }

    // Add the doctor for a particular spec
    @GetMapping("api/queue/add_doc")
    public ResponseEntity<Queue<Long>> add_doctor_to_queue(@RequestParam("spec_code") String specCode,
            @RequestParam("doctor_id") Long doctorId) throws Exception {
        doctorService.addtoqueue(specCode, doctorId);
        return ResponseEntity.ok(doctorService.getQueue(specCode));
    }

    // Delete the doc for a particular spec_code
    @GetMapping("/api/queue/delete_doc")
    public ResponseEntity<Queue<Long>> removedoctor(@RequestParam("spec_code") String specCode,
            @RequestParam("doctor_id") Long doctorId) throws Exception {
        doctorService.removeFromQueue(specCode, doctorId);
        return ResponseEntity.ok(doctorService.getQueue(specCode));
    }

    // Add the patient to the queue for a particular spec_code
    @PostMapping("/api/queue/add_patient")
    public ResponseEntity<Queue<Long>> add_patient_to_queue(@RequestBody Map<String, Object> requestBody)
            throws Exception {
        String specCode = (String) requestBody.get("spec_code");
        Long patientId = ((Integer) requestBody.get("patient_id")).longValue();
        patientService.addtoqueue(specCode, patientId);
        return ResponseEntity.ok(patientService.getQueue(specCode));
    }

    // Delete the patient from the queue for a particular spec_code
    @PostMapping("/api/queue/delete_patient")
    public ResponseEntity<Queue<Long>> remove_patient_to_queue(@RequestParam("spec_code") String specCode,
            @RequestParam("patient_id") Long patientId) throws Exception {
        patientService.removeFromQueue(specCode, patientId);
        return ResponseEntity.ok(patientService.getQueue(specCode));
    }

    // Get the list of all the patients for the particular spec_code
    @GetMapping("/api/queue/get_patient")
    public ResponseEntity<Queue<Long>> getpatientqueue(@RequestParam("spec_code") String specCode) throws Exception {
        return ResponseEntity.ok(patientService.getQueue(specCode));
    }

    // Assign the top patient to doctor and store it in map in patient service
    @GetMapping("/api/queue/assign_patient")
    public ResponseEntity<Long> getPateintForDoc(@RequestParam("spec_code") String specCode,
            @RequestParam("doctor_id") Long doc_id) throws Exception {
        Long patient_id = patientService.getTopPatient(specCode);
        // Long doc_id = doctorService.gettopdoc(specCode);
        if (patient_id != null && doc_id != null) {
            // return ResponseEntity.ok("paitient mateched : " + patient_id + " doctor
            // matched " + doc_id );
            patientService.assigntoPatient(patient_id, doc_id);
            patientService.removeFromQueue(specCode, patient_id);
            return ResponseEntity.ok(patient_id);
        }
        return null;
    }

    @GetMapping("/api/queue/see_patient")
    public ResponseEntity<Long> seePateintForDoc(@RequestParam("spec_code") String specCode,
            @RequestParam("doctor_id") Long doc_id) throws Exception {
        Long patient_id = patientService.getTopPatient(specCode);
        if (patient_id != null && doc_id != null) {
            return ResponseEntity.ok(patient_id);
        }
        return null;
    }

    // Tell the patient it's assigned doctor and if not null remove both pateint and
    // doctor from the queue;

    // So basically what will happen is that first doctor will get notified of the
    // patient assigned.
    // Now the how the doctor knows how the patient is assigned?? for that see the
    // above api /assign_patient
    // In that we take the top pateint and assign to that doctor , now I have made a
    // map in patient service which
    // stores the patient id as key and doctor id as value , what is means is that
    // which patient has doctor assigned
    // and in the above call I am addeing that entry to the patient map.

    // Now when we call the down api /assign_doctor we check in the map if their is
    // some doctor id for that particuale patient
    // if yes we remove both from their respective queue and retrurn back the doctor
    // id to the patient

    @GetMapping("/api/queue/assign_doctor")
    public ResponseEntity<String> getDocForPatient(@RequestParam("spec_code") String specCode,
            @RequestParam("patient_id") Long patientId) throws Exception {
        Long doc_id = patientService.isdoctorassigned(patientId);
        if (doc_id != null) {
            return ResponseEntity.ok("Patient assigned to doctor " + doc_id + " is " + patientId);
        }
        return null;
    }

    @PostMapping("/api/queue/endconsultation")
    public ResponseEntity<String> endConsultation( 
        @RequestBody Map<String, Object> requestBody) throws Exception {
        System.out.println(requestBody);
        Long patientId = ((Integer) requestBody.get("patient_id")).longValue();
        Long doc_id  = ((Integer) requestBody.get("doctor_id")).longValue();
        Long temp = patientService.isdoctorassigned(patientId);
        if(temp!=null && temp == doc_id) {
            patientService.removefrommap(patientId);
            return ResponseEntity.ok("Patiend removed from map");
        }
        return null;
    }

}
