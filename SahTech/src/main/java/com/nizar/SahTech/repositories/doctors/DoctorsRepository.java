package com.nizar.SahTech.repositories.doctors;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.nizar.SahTech.dto.doctors.DoctorDTO;
import com.nizar.SahTech.dto.doctors.SignUpDoctorsDTO;
import com.nizar.SahTech.entities.doctors.Doctor;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor,Long> {
    Doctor findFirstByEmail(String email);



    //Creat doctor in db
     default DoctorDTO createDoctor(SignUpDoctorsDTO signUpDoctorsDTO) {
        Doctor doctor = new Doctor();

        doctor.setName(signUpDoctorsDTO.getName());
        doctor.setEmail(signUpDoctorsDTO.getEmail());
        doctor.setPassword(new BCryptPasswordEncoder().encode(signUpDoctorsDTO.getPassword()));
        Doctor createDoctor = save(doctor);
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setId(createDoctor.getId());
        doctorDTO.setEmail(createDoctor.getEmail());
        doctorDTO.setName(createDoctor.getName());
        return doctorDTO;
    }


    


}
