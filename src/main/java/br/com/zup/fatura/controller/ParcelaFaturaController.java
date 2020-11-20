package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.Parcela;
import br.com.zup.fatura.request.ParcelaRequest;
import br.com.zup.fatura.service.ParcelamentoService;
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
public class ParcelaFaturaController {

    private ParcelamentoService parcelamentoService;
    private EntityManager entityManager;

    public ParcelaFaturaController(EntityManager entityManager, ParcelamentoService parcelamentoService) {
        this.entityManager = entityManager;
        this.parcelamentoService = parcelamentoService; //1
    }

    @PostMapping("/{idCartao}/faturas/{idFatura}/parcela")
    @Transactional
    public ResponseEntity parcelarFatura(@PathVariable("idCartao")UUID idCartao,
                                         @PathVariable("idFatura") UUID idFatura,
                                         @RequestBody @Valid ParcelaRequest request,
                                         UriComponentsBuilder builder){ //2

        Optional<Fatura> possivelFatura = Optional
                .ofNullable(entityManager.find(Fatura.class,
                        idFatura)); //3

        if (possivelFatura.isEmpty()) //4
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Fatura fatura = possivelFatura.get();

        if (!fatura.faturaPertenceCartao(idCartao) || !fatura.faturaPertenceMesCorrente()) //5
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        //refatorar para receber apenas a quantidade
        //o valor será feito de acordo com o valor da fatura
        Parcela parcela = request.toParcela(fatura); //6
        entityManager.persist(parcela);

        parcela.atualizaStatusParcelamento(parcelamentoService
                .solicitarParcelamento(fatura.numeroCartao(), request, idFatura)); //7?

        entityManager.merge(parcela);

        return ResponseEntity.created(builder.path("/api/cartoes/{id}/faturas" +
                "/{id}/parcelas/{id}").buildAndExpand(fatura.getCartao().getId(),
                fatura.getId(), parcela.getId())
                .toUri()).build();
    }

}
