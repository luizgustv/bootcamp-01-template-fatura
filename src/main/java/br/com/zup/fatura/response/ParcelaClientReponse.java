package br.com.zup.fatura.response;

import br.com.zup.fatura.model.enums.StatusAprovacaoParcelamento;
import br.com.zup.fatura.service.ParcelamentoService;

public class ParcelaClientReponse {

    private StatusAprovacaoParcelamento resultado;

    @Deprecated
    public ParcelaClientReponse(){}

    public ParcelaClientReponse(StatusAprovacaoParcelamento resultado) {
        this.resultado = resultado;
    }

    public StatusAprovacaoParcelamento getResultado() {
        return resultado;
    }

}
