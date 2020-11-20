package br.com.zup.fatura.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class LimiteCartaoRequest {

    @NotNull
    @Positive
    private BigDecimal limite;

    @Deprecated
    public LimiteCartaoRequest(){}

    public LimiteCartaoRequest(@NotNull @Positive BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getLimite() {
        return limite;
    }

}
