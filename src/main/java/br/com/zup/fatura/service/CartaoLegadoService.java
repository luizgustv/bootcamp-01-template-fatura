package br.com.zup.fatura.service;

import br.com.zup.fatura.integration.IntegracaoCartao;
import br.com.zup.fatura.model.enums.StatusAprovacaoParcelamento;
import br.com.zup.fatura.request.ParcelaClientRequest;
import br.com.zup.fatura.request.ParcelaRequest;
import br.com.zup.fatura.response.LimiteCartaoResponse;
import br.com.zup.fatura.response.ParcelaClientReponse;
import feign.Feign;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@Service
public class CartaoLegadoService {

    @Autowired
    private IntegracaoCartao integracaoCartao;

    public BigDecimal buscarCartao(String numeroCartao){
        try{
            LimiteCartaoResponse response = integracaoCartao.buscarCartaoById(numeroCartao);
            return response.getLimite();
        }catch (FeignException ex){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Houve um problema durante a" +
                    "tentativa de comunicação com o servidor");
        }
    }

    public StatusAprovacaoParcelamento solicitarParcelamento(String numeroCartao, ParcelaRequest request, UUID idFatura){

        try {

            ParcelaClientRequest parcelaClientRequest =
                    new ParcelaClientRequest(idFatura, request);

            ParcelaClientReponse response =
                    integracaoCartao.solicitarParcelamento(numeroCartao,parcelaClientRequest);

            return response.getResultado();

        }catch (FeignException ex){
            return StatusAprovacaoParcelamento.NEGADO;
        }
    }

}
