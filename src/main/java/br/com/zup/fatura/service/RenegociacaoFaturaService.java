package br.com.zup.fatura.service;

import br.com.zup.fatura.integration.IntegracaoCartao;
import br.com.zup.fatura.model.enums.StatusAprovacaoRenegociacao;
import br.com.zup.fatura.request.RenegociacaoFaturaClientRequest;
import br.com.zup.fatura.request.RenegociacaoFaturaRequest;
import br.com.zup.fatura.response.RegociacaoFaturaResponse;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RenegociacaoFaturaService {

    private IntegracaoCartao integracaoCartao;

    public RenegociacaoFaturaService(IntegracaoCartao integracaoCartao) {
        this.integracaoCartao = integracaoCartao;
    }

    public StatusAprovacaoRenegociacao solicitarRenegociacao(String numeroCartao,
                                                             RenegociacaoFaturaRequest request,
                                                             UUID idFatura){
        try {
            RenegociacaoFaturaClientRequest clientRequest =
                    new RenegociacaoFaturaClientRequest(idFatura, request);
            return integracaoCartao.solicitarRenegociacao(numeroCartao, clientRequest)
                    .getResultado();
        }catch (FeignException ex){
            return StatusAprovacaoRenegociacao.NEGADO;
        }
    }

}
