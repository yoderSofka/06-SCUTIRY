package com.example.banco_hex_yoder.rest.jwt;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.loginDTO.AuthenticationResponseDTO;
import com.example.banco_hex_yoder.dtos.loginDTO.LoginRequestDTO; // Asegúrate de que esta importación sea correcta
import com.example.banco_hex_yoder.security_jwt.JwtUtil;
import com.example.banco_hex_yoder.security_jwt.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "auth", description = "Operaciones relacionadas con auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Operation(summary = "login", description = "genera un jwt")
    @PostMapping("/login")
    public DinResponse<AuthenticationResponseDTO> login(@RequestBody DinRequest<LoginRequestDTO> request) {

        String username = request.getDinBody().getUsername();
        String password = request.getDinBody().getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = jwtUtil.generarToken(userDetailsService.loadUserByUsername(username));

            AuthenticationResponseDTO responseBody = new AuthenticationResponseDTO(token);
            DinResponse<AuthenticationResponseDTO> response = new DinResponse<>();
            response.setDinBody(responseBody);
            return response;
        } catch (Exception e) {

            System.out.println("Error en la autenticación: " + e.getMessage());
            throw e;
        }
    }
}