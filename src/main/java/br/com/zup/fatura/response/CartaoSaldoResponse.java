package br.com.zup.fatura.response;

import br.com.zup.fatura.model.Fatura;

import java.math.BigDecimal;

public class CartaoSaldoResponse {

    private String numeroCartao;
    private BigDecimal limite;
    private BigDecimal totalFatura;
    private BigDecimal saldoDisponível;

    public CartaoSaldoResponse(Fatura fatura, BigDecimal limite) {
        this.numeroCartao = fatura.numeroCartao();
        this.limite = limite;
        totalFatura = fatura.calcularFatura();
        saldoDisponível = calcularSaldoDisponivel();
    }


    public String getNumeroCartao() {
        return numeroCartao;
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
