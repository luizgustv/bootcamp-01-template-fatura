package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.Parcela;
import br.com.zup.fatura.request.ParcelaRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes")
public class ParcelaFaturaController {

    private EntityManager entityManager;

    public ParcelaFaturaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PostMapping("/{idCartao}/faturas/{idFatura}/parcela")
    @Transactional
    public ResponseEntity parcelarFatura(@PathVariable("idCartao")UUID idCartao,
                                         @PathVariable("idFatura") UUID idFatura,
                                         @RequestBody @Valid ParcelaRequest request,
                                         UriComponentsBuilder builder){ //1

        Optional<Fatura> possivelFatura = Optional
                .ofNullable(entityManager.find(Fatura.class, idFatura)); //2

        if (possivelFatura.isEmpty()) //3
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Fatura fatura = possivelFatura.get();

        if (!fatura.faturaPertenceCartao(idCartao) || !fatura.faturaPertenceMesCorrente()) //4
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        //refatorar para receber apenas a quantidade
        //o valor será feito de acordo com o valor da fatura
        Parcela parcela = request.toParcela(fatura); //4
        entityManager.persist(parcela);

        return ResponseEntity.created(builder.path("/api/cartoes/{id}/faturas" +
                "/{id}/parcelas/{id}").buildAndExpand(fatura.getCartao().getId(),
                fatura.getId(), parcela.getId())
                .toUri()).build();
    }

}
