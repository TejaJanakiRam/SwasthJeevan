package com.example.backend.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import org.springframework.stereotype.Service;

@Service
public class PatientService {
    // HashMap
    public final Map<String,  Queue<Long>> patientqueue = new HashMap<>();

    public void addtoqueue(String spec_code, Long patient_id){
        patientqueue.computeIfAbsent(spec_code, k -> new LinkedList<>()).offer(patient_id);
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

    
}
