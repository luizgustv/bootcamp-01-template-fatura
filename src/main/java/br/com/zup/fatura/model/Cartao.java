package br.com.zup.fatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Cartao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private String numeroCartao;
    private String email;

    @Deprecated
    public Cartao(){}

    public Cartao(String numeroCartao, String email) {
        this.numeroCartao = numeroCartao;
        this.email = email;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + numeroCartao +
                ", email='" + email + '\'' +
                '}';
    }

}
