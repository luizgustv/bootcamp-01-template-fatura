package br.com.zup.fatura.request;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.Parcela;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class ParcelaRequest {

    @Min(1)
    private @NotNull int quantidade;
    @Min(1)
    private @NotNull BigDecimal valor;

    @Deprecated
    public ParcelaRequest(){}

    public ParcelaRequest(@Min(1) @NotNull int quantidade, @Min(1) @NotNull BigDecimal valor) {
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Parcela toParcela(Fatura fatura) {
        return new Parcela(quantidade, valor, fatura);
    }

}
