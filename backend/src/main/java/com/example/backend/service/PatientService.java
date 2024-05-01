package com.example.backend.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.backend.entity.Patient;
import com.example.backend.repository.PatientRepository;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    // HashMap
    public final Map<String,  Queue<Long>> patientqueue = new HashMap<>();
    public final Map<Long, Long> sechdule = new HashMap<>();
    public void addtoqueue(String spec_code, Long patient_id){
        Queue<Long> queue = patientqueue.computeIfAbsent(spec_code, k -> new LinkedList<>());
        if(!queue.contains(patient_id)){
           queue.offer(patient_id);
        }
    }

    public Long removeFromQueue(String spec_code, Long patient_id) {
        Queue<Long> queue = patientqueue.get(spec_code);
        if (queue != null) {
            // Remove the specific doctor ID from the queue
            queue.remove(patient_id);
            return patient_id;
        } else {
            return null; // Queue doesn't exist for the specified speciality
        }
    }
    public Queue<Long> getQueue(String spec_code){
        return patientqueue.getOrDefault(spec_code, new LinkedList<>());
    } 

    public Long getTopPatient(String spec_code){
        if(patientqueue.get(spec_code)!=null)
            return patientqueue.get(spec_code).peek();
        return null;
    }

    public void assigntoPatient(Long patient_id, Long doctorId){
        sechdule.put(patient_id,doctorId);
    }

    public Long isdoctorassigned(Long patient_id){
        return sechdule.get(patient_id);
    }

    public Patient getPatientByUsername(String username){
        return(patientRepository.findByUsername(username));
    }

    public Patient updatePatient(Patient patient,Map<String, Object>newData){
        newData.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Patient.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, patient, value);
        });
        patientRepository.save(patient);
        return patient;
    }
    
    public void deletePatientByID(Long patient_id){
        patientRepository.deleteById(patient_id);
    }
}
