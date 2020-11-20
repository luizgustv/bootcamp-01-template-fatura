package br.com.zup.fatura.integration;

import br.com.zup.fatura.model.enums.StatusAprovacaoParcelamento;
import br.com.zup.fatura.request.AlterarDataRequest;
import br.com.zup.fatura.request.ParcelaClientRequest;
import br.com.zup.fatura.request.ParcelaRequest;
import br.com.zup.fatura.request.RenegociacaoFaturaClientRequest;
import br.com.zup.fatura.response.LimiteCartaoResponse;
import br.com.zup.fatura.response.ParcelaClientReponse;
import br.com.zup.fatura.response.RegociacaoFaturaResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(url ="${host.cartao}", name = "integracaoCartao")
public interface IntegracaoCartao {

    @GetMapping("/api/cartoes/{id}")
    LimiteCartaoResponse buscarCartaoById(@PathVariable("id") String numeroCartao);

    @PostMapping("/api/cartoes/{id}/parcelas")
    ParcelaClientReponse solicitarParcelamento(@PathVariable("id") String numeroCartao,
                                               @RequestBody ParcelaClientRequest request);

    @PostMapping("/api/cartoes/{id}/renegociacoes")
    RegociacaoFaturaResponse solicitarRenegociacao(@PathVariable("id") String numeroCartao,
                                                   @RequestBody RenegociacaoFaturaClientRequest request);

    @PostMapping("api/cartoes/{id}/vencimentos")
    ResponseEntity socilitarAlteracaoDataFatura(@PathVariable("id") String numeroCartao,
                                                @RequestBody AlterarDataRequest request);

}
