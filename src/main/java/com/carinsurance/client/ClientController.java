package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;


    @PostMapping("/add")
    public ResponseEntity<Client> saveClient(@RequestBody ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(clientService.saveClient(clientRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable Long id) {
        ClientResponseDto clientResponseDto = clientService.findClientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDto);
    }
}
