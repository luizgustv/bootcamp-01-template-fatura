package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.request.AlterarDataRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes")
public class FaturaAtualizarDataController {

    private EntityManager entityManager;

    public FaturaAtualizarDataController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @PutMapping("/{id}/faturas/alterarData")
    @Transactional
    public ResponseEntity alterarDataVencimento(@PathVariable("id")UUID idCartao,
                                                @RequestBody @Valid AlterarDataRequest request){

       TypedQuery<Fatura> typedQuery =
               entityManager.createQuery("select f from Fatura f " +
                       "where f.cartao.id =: idCartao and f.mes =: mes", Fatura.class);
       typedQuery.setParameter("idCartao", idCartao);
       typedQuery.setParameter("mes", LocalDate.now().getMonthValue());

       if(typedQuery.getResultList().isEmpty()){
           return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

       Fatura fatura = typedQuery.getResultList().get(0);
       fatura.alterarDiaVencimento(request.getDataVencimento());

       entityManager.merge(fatura);

       return new ResponseEntity(HttpStatus.OK);
    }

}
