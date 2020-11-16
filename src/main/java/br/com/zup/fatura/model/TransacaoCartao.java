package br.com.zup.fatura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class TransacaoCartao {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @JsonProperty("id")
    private String numeroTransacao;
    private BigDecimal valor;
    private Estabelecimento estabelecimento;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoCartao(){}

    public TransacaoCartao(String numeroTransacao, BigDecimal valor,
                           Estabelecimento estabelecimento, LocalDateTime efetivadaEm) {
        this.numeroTransacao = numeroTransacao;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.efetivadaEm = efetivadaEm;
    }

    public String getNumeroTransacao() {
        return numeroTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    @Override
    public String toString() {
        return "TransacaoCartao{" +
                "id='" + numeroTransacao + '\'' +
                ", valor=" + valor +
                ", estabelecimento=" + estabelecimento +
                ", efetivadaEm=" + efetivadaEm +
                '}';
    }

}
