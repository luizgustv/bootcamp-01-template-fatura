package br.com.zup.fatura.response;

import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.TransacaoCartao;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FaturaResponse {

    private Integer ano;
    private Integer mes;
    private Cartao cartao;
    private List<TransacaoCartao> transacoes;
    private BigDecimal totalFatura;

    public FaturaResponse(Fatura fatura) {
        this.mes = fatura.getMes();
        this.ano = fatura.getAno();
        this.cartao = fatura.getCartao();
        this.transacoes = fatura.getTransacoes();
        this.totalFatura = fatura.calcularFatura();
    }

    public Integer getAno() {
        return ano;
    }

    public Integer getMes() {
        return mes;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public List<TransacaoCartao> getTransacoes() {
        return transacoes;
    }

    public BigDecimal getTotalFatura() {
        return totalFatura;
    }


}
