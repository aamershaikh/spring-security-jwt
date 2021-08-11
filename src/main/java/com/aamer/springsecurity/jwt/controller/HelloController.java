package com.aamer.springsecurity.jwt.controller;

import com.aamer.springsecurity.jwt.model.AuthenticationRequest;
import com.aamer.springsecurity.jwt.model.AuthenticationResponse;
import com.aamer.springsecurity.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @RequestMapping(value = "/api/hello")
    public String getMessage(){
        return "Hello JWT!";
    }


    /*
    * Refer the HowJwtWorks section for the diagram
    *
    * */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        // 1. authenticate using AuthenticationManager the username and password
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch(BadCredentialsException bce)
        {
            // 2. throw exception if username and password are not found
            throw new Exception("Incorrect username and password", bce);
        }

        // 3. load the user if found in Userdetails
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        // 4. generate the token for this user
        final String jwt = jwtUtil.generateToken(userDetails);

        // 5. send as a response the token that is generated
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
