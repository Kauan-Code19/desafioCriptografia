package com.desafios.cripftografia.controllers;

import com.desafios.cripftografia.dtos.AuthenticationDto;
import com.desafios.cripftografia.dtos.LoginResponseDto;
import com.desafios.cripftografia.dtos.RegisterDto;
import com.desafios.cripftografia.entities.client.ClientEntity;
import com.desafios.cripftografia.repositories.ClientRepository;
import com.desafios.cripftografia.security.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(),
                authenticationDto.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((ClientEntity) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        if (this.clientRepository.findByLogin(registerDto.login()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());
        ClientEntity client = new ClientEntity(registerDto.login(), encryptedPassword, registerDto.level());

        this.clientRepository.save(client);

        return ResponseEntity.ok().build();
    }
}
