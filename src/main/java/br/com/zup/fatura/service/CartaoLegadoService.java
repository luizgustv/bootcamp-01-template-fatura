package br.com.zup.fatura.service;

import br.com.zup.fatura.integration.IntegracaoCartao;
import br.com.zup.fatura.response.LimiteCartaoResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@Service
public class CartaoLegadoService {

    @Autowired
    private IntegracaoCartao integracaoCartao;

    public BigDecimal buscarCartao(String idCartao){
        try{
            LimiteCartaoResponse request = integracaoCartao.buscarCartaoById(idCartao);
            return request.getLimite();
        }catch (FeignException ex){
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Houve um problema durante a" +
                    "tentativa de comunicação com o servidor");
        }
    }

}
