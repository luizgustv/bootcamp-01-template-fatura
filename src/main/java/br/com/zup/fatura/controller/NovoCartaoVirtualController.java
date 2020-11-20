package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.CartaoVirtual;
import br.com.zup.fatura.request.LimiteCartaoRequest;
import br.com.zup.fatura.response.LimiteCartaoResponse;
import br.com.zup.fatura.service.CartaoLegadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes")
public class NovoCartaoVirtualController {

    private CartaoLegadoService cartaoLegadoService; //2
    private EntityManager entityManager;

    public NovoCartaoVirtualController(CartaoLegadoService cartaoLegadoService,
                                       EntityManager entityManager) {
        this.cartaoLegadoService = cartaoLegadoService;
        this.entityManager = entityManager;
    }

    @PostMapping("/{id}/cartoesvirtuais")
    @Transactional
    public ResponseEntity novoCartaoVirtual(@PathVariable("id")UUID idCartao,
                                            @RequestBody @Valid LimiteCartaoRequest request,
                                            UriComponentsBuilder builder){ //2

        Optional<Cartao> possivelCartao = Optional
                .ofNullable(entityManager.find(Cartao.class, idCartao)); //3
        if (possivelCartao.isEmpty())//4
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Cartao cartao = possivelCartao.get();

        BigDecimal limite = cartaoLegadoService
                .buscarCartao(cartao.getNumeroCartao());

        if (request.getLimite().doubleValue() > limite.doubleValue()) //4
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        CartaoVirtual novoCartaoVirtual =
                new CartaoVirtual(cartao, limite); //5

        entityManager.persist(novoCartaoVirtual);

        return ResponseEntity.created(builder.path("/api/cartoes/{id}/cartoesvirtuais/{id}")
        .buildAndExpand(cartao.getId(), novoCartaoVirtual.getId()).toUri()).build();
    }


}
