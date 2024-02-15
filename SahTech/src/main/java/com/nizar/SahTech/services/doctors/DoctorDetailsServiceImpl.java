package com.nizar.SahTech.services.doctors;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.nizar.SahTech.entities.doctors.Doctor;
import com.nizar.SahTech.repositories.doctors.DoctorsRepository;

@Service
public class DoctorDetailsServiceImpl implements UserDetailsService  {
    
    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Write Logic to get the user from the DB
        Doctor doctor = doctorsRepository.findFirstByEmail(email);
        if(doctor == null){
            throw new UsernameNotFoundException("User not found",null);
        }
        return  new org.springframework.security.core.userdetails.User(doctor.getEmail(), doctor.getPassword(), new ArrayList<>());
    }
}
