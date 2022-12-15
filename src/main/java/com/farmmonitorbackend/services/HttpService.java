package com.farmmonitorbackend.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HttpService {

    public ResponseEntity<Object> sendResponse(HttpStatus status, Object body) {
        return ResponseEntity.status(status).body(body);
    }

}
