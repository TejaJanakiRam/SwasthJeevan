package com.example.backend.service;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.backend.entity.Doctor;
import com.example.backend.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;
    // public List<Doctor> getDoctors() throws Exception ;
    public final Map<String,  Queue<Long>> doctorqueues = new HashMap<>();
    public void addtoqueue(String spec_code, Long doctor_id){
        Queue<Long> queue = doctorqueues.computeIfAbsent(spec_code, k -> new LinkedList<>());
        if (!queue.contains(doctor_id)) {
            queue.offer(doctor_id);
        }
           
    }
    public Long removeFromQueue(String spec_code, Long doctorId) {
        Queue<Long> queue = doctorqueues.get(spec_code);
        if (queue != null) {
            // Remove the specific doctor ID from the queue
            queue.remove(doctorId);
            return doctorId;
        } else {
            return null; // Queue doesn't exist for the specified speciality
        }
    }
    public Queue<Long> getQueue(String spec_code){
        return doctorqueues.getOrDefault(spec_code, new LinkedList<>());
    } 
    public Long gettopdoc(String spec_code){
        Queue<Long> queue = doctorqueues.get(spec_code);
        return queue.peek();
    }

    public Doctor getDoctorbyUsername(String username){
        return(doctorRepository.findByUsername(username));
    }

    public List<Doctor> getAllDoctors(){
        return(doctorRepository.findAll());
    }

    public Doctor updateDoctor(Doctor doctor,Map<String, Object>newData){
        newData.forEach((key,value)->{
            Field field = ReflectionUtils.findField(Doctor.class, key);
            field.setAccessible(true);
            ReflectionUtils.setField(field, doctor, value);
        });
        // System.out.println(doctor.getPassword());
        doctorRepository.save(doctor);
        return doctor;
    }

    public void deleteDoctorById(Long doctor_id){
        doctorRepository.deleteById(doctor_id);
    }
}
