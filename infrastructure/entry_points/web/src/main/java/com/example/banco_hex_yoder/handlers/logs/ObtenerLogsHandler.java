package com.example.banco_hex_yoder.handlers.logs;



import com.example.banco_hex_yoder.din.request.DinRequest;
import com.example.banco_hex_yoder.din.response.DinResponse;
import com.example.banco_hex_yoder.dtos.logs.LogFilterDTO;
import com.example.banco_hex_yoder.logs_repository.data.entidades.LogDocument;
import com.example.banco_hex_yoder.logs_repository.data.repositorios.LogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObtenerLogsHandler {

    private final LogRepository logRepository;

    public ObtenerLogsHandler(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public DinResponse<List<LogDocument>> obtenerLogs(DinRequest<LogFilterDTO> request) {
        // Obtén todos los logs de la base de datos
        List<LogDocument> logs = logRepository.findAll();

        // Filtrar por tipoOperacion si se especifica
        if (request.getDinBody() != null && request.getDinBody().getTipoOperacion() != null) {
            String tipoOperacion = request.getDinBody().getTipoOperacion();
            logs = logs.stream()
                    .filter(log -> tipoOperacion.equals(log.getTipoOperacion()))
                    .collect(Collectors.toList());
        }

        // Retornar la respuesta con los logs filtrados
        return new DinResponse<>(logs);
    }
}
