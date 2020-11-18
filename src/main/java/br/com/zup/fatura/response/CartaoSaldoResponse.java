package br.com.zup.fatura.response;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.TransacaoCartao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartaoSaldoResponse {

    private String numeroCartao;
    private BigDecimal limite;
    private BigDecimal totalFatura;
    private BigDecimal saldoDisponível;
    private List<TransacaoCartao> transacoes;

    public CartaoSaldoResponse(Fatura fatura, BigDecimal limite) {
        this.numeroCartao = fatura.numeroCartao();
        this.limite = limite;
        this.transacoes = fatura.getTransacoes();
        this.totalFatura = fatura.calcularFatura();
        saldoDisponível = calcularSaldoDisponivel();
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public List<TransacaoCartao> getTransacoes() {
        return transacoes;
    }

    public BigDecimal getTotalFatura() {
        return totalFatura;
    }

    public BigDecimal getLimite() {
        return limite;
    }

    public BigDecimal getSaldoDisponível() {
        return saldoDisponível;
    }

    public BigDecimal calcularSaldoDisponivel(){
        return this.limite.subtract(totalFatura);
    }

}
