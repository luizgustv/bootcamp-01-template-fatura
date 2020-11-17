package br.com.zup.fatura.integration;

import br.com.zup.fatura.response.LimiteCartaoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url ="${host.cartao}", name = "integracaoCartao")
public interface IntegracaoCartao {

    @GetMapping("/api/cartoes/{id}")
    LimiteCartaoResponse buscarCartaoById(@PathVariable("id") String numeroCartao);

}
