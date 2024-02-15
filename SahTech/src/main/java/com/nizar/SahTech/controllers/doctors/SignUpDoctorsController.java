package com.nizar.SahTech.controllers.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nizar.SahTech.dto.doctors.DoctorDTO;
import com.nizar.SahTech.dto.doctors.SignUpDoctorsDTO;
import com.nizar.SahTech.dto.users.SignupDTO;
import com.nizar.SahTech.dto.users.UserDTO;
import com.nizar.SahTech.entities.doctors.Doctor;
import com.nizar.SahTech.services.doctors.DoctorsService;
import com.nizar.SahTech.services.users.auth.userservice;


@RequestMapping("/api/doctor")

public class SignUpDoctorsController {


     @Autowired
    private DoctorsService doctorservice;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signupDoctor(@RequestBody SignUpDoctorsDTO signupDoctorDTO) {
       DoctorDTO  createdDocotr = null;
    try {
        createdDocotr = doctorservice.createDoctor(signupDoctorDTO);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

       if (createdDocotr == null){
           return new ResponseEntity<>("Doctor not created, come again later!", HttpStatus.BAD_REQUEST);
       }
       return new ResponseEntity<>(createdDocotr, HttpStatus.CREATED);
    }

}
