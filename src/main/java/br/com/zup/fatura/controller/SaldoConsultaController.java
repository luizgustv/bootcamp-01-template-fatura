package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.response.CartaoSaldoResponse;
import br.com.zup.fatura.service.CartaoLegadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes")
public class SaldoConsultaController {

    private CartaoLegadoService cartaoLegadoService; //1
    private EntityManager entityManager;

    public SaldoConsultaController(EntityManager entityManager, CartaoLegadoService cartaoLegadoService) {
        this.entityManager = entityManager;
        this.cartaoLegadoService = cartaoLegadoService;
    }

    @GetMapping("/{id}/saldo")
    public ResponseEntity consultarLimite(@PathVariable("id")UUID idCartao){

        Optional<Cartao> possivelCartao = Optional.of(entityManager.find(Cartao.class, idCartao)); //2

        if (possivelCartao.isEmpty()) //3
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        BigDecimal limite = cartaoLegadoService
                .buscarCartao(possivelCartao.get().getNumeroCartao());

        TypedQuery<Fatura> typedQuery = entityManager
                .createNamedQuery("buscarFaturaPorNumCartao", Fatura.class); //4
        typedQuery.setParameter("numero", possivelCartao.get().getNumeroCartao());
        typedQuery.setParameter("mes", LocalDateTime.now().getMonthValue());
        typedQuery.setParameter("ano", LocalDateTime.now().getYear());

        if (typedQuery.getResultList().isEmpty()) //5
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        CartaoSaldoResponse response =
                new CartaoSaldoResponse(typedQuery.getResultList().get(0),
                        limite);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
