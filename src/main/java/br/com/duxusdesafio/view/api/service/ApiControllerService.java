package br.com.duxusdesafio.view.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ApiControllerService {

    ResponseEntity<?> gerarResponse(Object dados, HttpStatus statusRequest);
    ResponseEntity<?> ok(Object dados);
    ResponseEntity<?> okVazio();
}
