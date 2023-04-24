package com.carinsurance.client.exception;

public class ClientNotFoundException  extends  RuntimeException{
    public ClientNotFoundException(Long id){
        super(String.format("Client with id %d not found", id));
    }
}
