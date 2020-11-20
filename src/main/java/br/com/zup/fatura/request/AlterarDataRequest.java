package br.com.zup.fatura.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AlterarDataRequest {

    @NotNull
    @Min(1) @Max(30)
    private int dataVencimento;

    @Deprecated
    public AlterarDataRequest(){}

    public int getDataVencimento() {
        return dataVencimento;
    }

}
