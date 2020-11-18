package br.com.zup.fatura.request;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.Parcela;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ParcelaRequest {

    @Min(1)
    private @NotNull int quantidade;
    @Min(1)
    private @NotNull int valorParcela;

    @Deprecated
    public ParcelaRequest(){}

    public ParcelaRequest(@Min(1) @NotNull int quantidade, @Min(1) @NotNull int valorParcela) {
        this.quantidade = quantidade;
        this.valorParcela = valorParcela;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public int getValorParcela() {
        return valorParcela;
    }

    public Parcela toParcela(Fatura fatura) {
        return new Parcela(quantidade, valorParcela, fatura);
    }


}
