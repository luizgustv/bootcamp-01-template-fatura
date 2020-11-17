package br.com.zup.fatura.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class CartaoRequest {

    private String id;
    private String email;

    @Deprecated
    public CartaoRequest(){}

    public CartaoRequest(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

}
