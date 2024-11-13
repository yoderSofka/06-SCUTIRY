package com.example.banco_hex_yoder.rest.jwt;

import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.loginDTO.AuthenticationResponseDTO;
import com.example.banco_hex_yoder.dtos.loginDTO.LoginRequestDTO;
import com.example.banco_hex_yoder.security_jwt.JwtUtil;
import com.example.banco_hex_yoder.security_jwt.UserDetailsServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

        DinResponse<AuthenticationResponseDTO> response = new DinResponse<>();
        response.setDinHeader(request.getDinHeader());

        String username = request.getDinBody().getUsername();
        String password = request.getDinBody().getPassword();

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            String token = jwtUtil.generarToken(userDetails);

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(grantedAuthority -> grantedAuthority.getAuthority().replace("ROLE_", ""))
                    .collect(Collectors.toList());

            AuthenticationResponseDTO responseBody = new AuthenticationResponseDTO(token, roles);
            response.setDinBody(responseBody);

            response.setDinError(new DinError("N", "0000", "Login exitoso", "Autenticación realizada correctamente"));

        } catch (Exception e) {
            System.out.println("Error en la autenticación: " + e.getMessage());
            response.setDinError(new DinError("ERROR", "1006", "Error en la autenticación", e.getMessage()));
        }

        return response;
    }
}
