package br.com.zup.fatura.response;

import br.com.zup.fatura.model.enums.StatusAprovacaoRenegociacao;

public class RegociacaoFaturaResponse {

    private StatusAprovacaoRenegociacao resultado;

    @Deprecated
    public RegociacaoFaturaResponse(){}

    public RegociacaoFaturaResponse(StatusAprovacaoRenegociacao resultado) {
        this.resultado = resultado;
    }

    public StatusAprovacaoRenegociacao getResultado() {
        return resultado;
    }

}
