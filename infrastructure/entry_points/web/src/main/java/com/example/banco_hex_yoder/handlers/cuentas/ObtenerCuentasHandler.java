package com.example.banco_hex_yoder.handlers.cuentas;

import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.din.generic.DinError;
import com.example.banco_hex_yoder.dtos.accountDTO.AccountResponseDTO;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.AccountMongoRepository;
import com.example.banco_hex_yoder.mongo_repository.data.repositorios.CustomerMongoRepository;
import com.example.banco_hex_yoder.model.Account;
import com.example.banco_hex_yoder.mongo_repository.data.entidades.ClienteDocument;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ObtenerCuentasHandler {

    private final AccountMongoRepository accountRepository;
    private final CustomerMongoRepository customerRepository;

    public ObtenerCuentasHandler(AccountMongoRepository accountRepository,
                                 CustomerMongoRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    public DinResponse<List<AccountResponseDTO>> obtenerCuentas() {
        List<Account> cuentas = accountRepository.findAll();
        List<AccountResponseDTO> respuesta = cuentas.stream().map(cuenta -> {
            AccountResponseDTO dto = new AccountResponseDTO();
            dto.setId(cuenta.getId());
            dto.setNumber(cuenta.getNumber());
            dto.setAmount(cuenta.getAmount());


            Optional<ClienteDocument> cliente = customerRepository.findById(cuenta.getCustomerId());
            dto.setCustomer(cliente.map(ClienteDocument::getUsername).orElse("Desconocido"));
            dto.setCustomerId(cuenta.getCustomerId());

            dto.setCreatedAt(cuenta.getCreatedAt());
            dto.setDeleted(cuenta.isDeleted());
            return dto;
        }).collect(Collectors.toList());

        DinResponse<List<AccountResponseDTO>> response = new DinResponse<>();
        response.setDinBody(respuesta);
        response.setDinError(new DinError("N", "0000", "Listado de cuentas obtenido exitosamente", "Operación completada sin errores"));
        return response;
    }
}
