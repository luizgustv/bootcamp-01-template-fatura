package br.com.zup.fatura.controller;

import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.response.FaturaResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.UUID;

@RestController
@RequestMapping("/api/cartoes/faturas")
public class FaturaConsultaController {

    private EntityManager entityManager;

    public FaturaConsultaController(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/{id}/{mes}/{ano}")
    public ResponseEntity consultarFaturaPorId(@PathVariable("id") UUID idCartao,
                                               @PathVariable("mes") Integer mes,
                                               @PathVariable("ano") Integer ano){

        TypedQuery<Fatura> typedQuery = entityManager
                .createQuery("select f from Fatura f where f.cartao.id =: idCartao and f.mes =: mes and f.ano =: ano ",
                        Fatura.class);
        typedQuery.setParameter("idCartao", idCartao);
        typedQuery.setParameter("mes", mes);
        typedQuery.setParameter("ano", ano);

        if (typedQuery.getResultList().isEmpty()) //1
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        Fatura fatura = typedQuery.getResultList().get(0);

        FaturaResponse response = new FaturaResponse(fatura);

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
