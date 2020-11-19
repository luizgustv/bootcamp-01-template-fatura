package br.com.zup.fatura.request;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.RenegociacaoFatura;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

public class RenegociacaoFaturaRequest {

    @Min(1)
    private int quantidade;
    @Min(1)
    private BigDecimal valor;

    @Deprecated
    public RenegociacaoFaturaRequest(){}

    public RenegociacaoFaturaRequest(@Min(1) int quantidade, @Min(1) BigDecimal valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public RenegociacaoFatura toRenegociacao(Fatura fatura) {
        return new RenegociacaoFatura(fatura, this.quantidade, this.valor);
    }

}
