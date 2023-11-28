package com.carinsurance.client;

import com.carinsurance.client.dto.ClientRequestDto;
import com.carinsurance.client.dto.ClientResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.carinsurance.client.ClientController.Routes.*;


@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @PostMapping(ROOT)
    public ResponseEntity<Client> saveClient(@RequestBody ClientRequestDto clientRequestDto) {
        return new ResponseEntity<>(clientService.saveClient(clientRequestDto), HttpStatus.CREATED);
    }

    @GetMapping(FIND_CLIENT_BY_ID)
    public ResponseEntity<ClientResponseDto> findClientById(@PathVariable Long id) {
        ClientResponseDto clientResponseDto = clientService.findClientById(id);
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDto);
    }

    static final class Routes {
        static final String ROOT = "/clients";
        static final String FIND_CLIENT_BY_ID = ROOT + "/{id}";


    }

}
