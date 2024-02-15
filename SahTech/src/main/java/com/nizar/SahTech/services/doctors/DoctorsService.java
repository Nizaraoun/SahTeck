package com.nizar.SahTech.services.doctors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.nizar.SahTech.dto.doctors.DoctorDTO;
import com.nizar.SahTech.dto.doctors.SignUpDoctorsDTO;
import com.nizar.SahTech.repositories.doctors.DoctorsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorsService {

   private final DoctorsRepository doctorsRepository;
   
   @Value("${app.client.url}")
   private String appClientUrl;

   public DoctorDTO createDoctor(SignUpDoctorsDTO signUpDoctorsDTO)  throws Exception 
    { 
       
       if (doctorsRepository.findFirstByEmail(signUpDoctorsDTO.getEmail()) == null) {
        try {
           return doctorsRepository.createDoctor(signUpDoctorsDTO);
        } catch (Exception e) {
           throw new Exception("Doctor not created, come again later!");}
   }

    return null;}

}

