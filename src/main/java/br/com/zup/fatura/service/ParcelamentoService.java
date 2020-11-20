package br.com.zup.fatura.service;

import br.com.zup.fatura.integration.IntegracaoCartao;
import br.com.zup.fatura.model.enums.StatusAprovacaoParcelamento;
import br.com.zup.fatura.request.ParcelaClientRequest;
import br.com.zup.fatura.request.ParcelaRequest;
import feign.FeignException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ParcelamentoService {

    IntegracaoCartao integracaoCartao; //1

    public ParcelamentoService(IntegracaoCartao integracaoCartao) {
        this.integracaoCartao = integracaoCartao;
    }

    public StatusAprovacaoParcelamento solicitarParcelamento(String numeroCartao, ParcelaRequest request, UUID idFatura){ //2 //3
        try { //4
            ParcelaClientRequest parcelaClientRequest =
                    new ParcelaClientRequest(idFatura, request); //5

            return integracaoCartao.solicitarParcelamento(numeroCartao,parcelaClientRequest)
                    .getResultado();
        }catch (FeignException ex){ //6
            return StatusAprovacaoParcelamento.NEGADO;
        }
    }

}
