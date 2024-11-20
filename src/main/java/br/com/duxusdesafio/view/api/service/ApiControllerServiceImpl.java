package br.com.duxusdesafio.view.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiControllerServiceImpl implements ApiControllerService {

    /**
     * Cria um response com os dados obtidos
     *
     * @param dados
     * @param statusRequest
     * @return Response para o controller
     */
    @Override
    public ResponseEntity<?> gerarResponse(Object dados, HttpStatus statusRequest) {
        return new ResponseEntity<>(dados, statusRequest);
    }

    @Override
    public ResponseEntity<?> ok(Object dados) {
        return ResponseEntity.ok(dados);
    }

    @Override
    public ResponseEntity<?> okVazio() {
        return ResponseEntity.noContent().build();
    }
}
