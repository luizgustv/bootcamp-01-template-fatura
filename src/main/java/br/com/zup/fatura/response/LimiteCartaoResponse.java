package br.com.zup.fatura.response;

import java.math.BigDecimal;

public class LimiteCartaoResponse {

    private BigDecimal limite;

    @Deprecated
    public LimiteCartaoResponse(){}

    public LimiteCartaoResponse(BigDecimal limite) {
        this.limite = limite;
    }

    public BigDecimal getLimite() {
        return limite;
    }


}
