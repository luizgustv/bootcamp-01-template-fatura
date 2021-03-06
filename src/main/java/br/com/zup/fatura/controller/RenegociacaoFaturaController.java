package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.RenegociacaoFatura;
import br.com.zup.fatura.request.RenegociacaoFaturaRequest;
import br.com.zup.fatura.service.RenegociacaoFaturaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes")
public class RenegociacaoFaturaController {

    private RenegociacaoFaturaService renegociacaoFaturaService;
    private EntityManager entityManager;

    public RenegociacaoFaturaController(EntityManager entityManager,
                                        RenegociacaoFaturaService renegociacaoFaturaService) {
        this.entityManager = entityManager;
        this.renegociacaoFaturaService = renegociacaoFaturaService;
    }

    @PostMapping("/{idCartao}/faturas/{idFatura}/renegociacoes")
    @Transactional
    public ResponseEntity renegociarFatura(@PathVariable("idCartao") UUID idCartao,
                                           @PathVariable("idFatura") UUID idFatura,
                                           @RequestBody @Valid RenegociacaoFaturaRequest request,
                                           UriComponentsBuilder builder){

        Optional<Fatura> possivelFatura = Optional
                .ofNullable(entityManager.find(Fatura.class, idFatura));
        if (possivelFatura.isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        Fatura fatura = possivelFatura.get();

        if (!fatura.faturaPertenceCartao(idCartao) || !fatura.faturaPertenceMesCorrente()) //4
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        RenegociacaoFatura novaRenegociacaoFatura = request.toRenegociacao(fatura); //5
        entityManager.persist(novaRenegociacaoFatura);

        novaRenegociacaoFatura.atualizarStatusRenegociacao(renegociacaoFaturaService
                .solicitarRenegociacao(fatura.numeroCartao(),
                request, idFatura)); //6

        entityManager.merge(novaRenegociacaoFatura);

        return ResponseEntity.created(builder.path("/api/cartoes/{id}/" +
                "faturas/{id}/renegociacoes/{id}").buildAndExpand(possivelFatura.get().numeroCartao(),
                possivelFatura.get().getId(), novaRenegociacaoFatura.getId()).toUri()).build();
    }

}
