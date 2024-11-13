package com.example.banco_hex_yoder.rest.cuentas;

import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.accountDTO.AccountResponseDTO;
import com.example.banco_hex_yoder.handlers.cuentas.ObtenerCuentasHandler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "cuentas", description = "Obtener cuentas")
@RequestMapping("/api/cuentas")
public class AccountController {

    private final ObtenerCuentasHandler obtenerCuentasHandler;

    public AccountController(ObtenerCuentasHandler obtenerCuentasHandler) {
        this.obtenerCuentasHandler = obtenerCuentasHandler;
    }

    @PostMapping
    public DinResponse<List<AccountResponseDTO>> getCuentas() {
        return obtenerCuentasHandler.obtenerCuentas();
    }
}
