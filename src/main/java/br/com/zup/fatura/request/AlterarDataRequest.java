package br.com.zup.fatura.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AlterarDataRequest {

    @NotNull
    @Min(1) @Max(30)
    private int dia;

    @Deprecated
    public AlterarDataRequest(){}

    public AlterarDataRequest(@NotNull @Min(1) @Max(30) int dia) {
        this.dia = dia;
    }

    public int getDia() {
        return dia;
    }

}
