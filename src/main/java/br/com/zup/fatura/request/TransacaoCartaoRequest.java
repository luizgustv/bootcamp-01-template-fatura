package br.com.zup.fatura.request;

import br.com.zup.fatura.model.Estabelecimento;
import br.com.zup.fatura.model.TransacaoCartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoCartaoRequest {

    private String id;
    private BigDecimal valor;
    private Estabelecimento estabelecimento;
    private CartaoRequest cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public TransacaoCartaoRequest(){}

    public TransacaoCartaoRequest(String id, BigDecimal valor,
                                  Estabelecimento estabelecimento, CartaoRequest cartao,
                                  LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoRequest getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public TransacaoCartao toTransacaoCartao(){
        return new TransacaoCartao(this.id, this.valor, this.estabelecimento, this.efetivadaEm);
    }

}
