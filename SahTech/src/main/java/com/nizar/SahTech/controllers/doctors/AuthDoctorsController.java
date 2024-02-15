package com.nizar.SahTech.controllers.doctors;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.nizar.SahTech.dto.doctors.AuthDoctorsDTO;
import com.nizar.SahTech.dto.users.AuthenticationResponse;
import com.nizar.SahTech.services.doctors.DoctorDetailsServiceImpl;
import com.nizar.SahTech.util.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

public class AuthDoctorsController {


     @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private DoctorDetailsServiceImpl doctorDetailsServiceImpl;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthDoctorsDTO authenticationDoctorDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDoctorDTO.getEmail(), authenticationDoctorDTO.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
            return null;
        }

        final UserDetails doctorDetails = doctorDetailsServiceImpl.loadUserByUsername(authenticationDoctorDTO.getEmail());

        final String jwt = jwtUtil.generateToken(doctorDetails.getUsername());

        return new AuthenticationResponse(jwt);

    }
}
