package br.com.zup.fatura.service;

import br.com.zup.fatura.integration.IntegracaoCartao;
import br.com.zup.fatura.request.AlterarDataRequest;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class AlteracaoDataService {

    private IntegracaoCartao integracaoCartao;

    public AlteracaoDataService(IntegracaoCartao integracaoCartao) {
        this.integracaoCartao = integracaoCartao;
    }

    public ResponseEntity solicitarAlteracaoDataVencimento(String numeroCartao,
                                                           AlterarDataRequest  request) {
        try{
            return integracaoCartao.socilitarAlteracaoDataFatura(numeroCartao, request);
        }catch (FeignException ex){
            if (ex.status() == 422)
                throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
                        "A solicitação de alteração de data de vencimento da fatura" +
                                "foi negado");
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Houve um erro ao " +
                    "tentar se conectar com o servidor");
        }
    }

}
