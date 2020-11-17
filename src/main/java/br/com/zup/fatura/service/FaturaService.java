package br.com.zup.fatura.service;

import br.com.zup.fatura.model.Fatura;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class FaturaService {

    private EntityManager entityManager;

    public FaturaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Fatura buscarFaturaByFatura(Fatura fatura){ //3

        /*TypedQuery<Fatura> query = entityManager
                .createQuery("select f from Fatura f where " +
                                "f.cartao.numeroCartao =:numero and mes =:mes and ano =:ano",
                        Fatura.class);*/

        TypedQuery<Fatura> typedQuery = entityManager
                .createNamedQuery("buscarFaturaPorNumCartao", Fatura.class);
        typedQuery.setParameter("numero", fatura.numeroCartao());
        typedQuery.setParameter("mes", fatura.getMes());
        typedQuery.setParameter("ano", fatura.getAno());

        if (typedQuery.getResultList().isEmpty()){ //4
            entityManager.persist(fatura);
            return fatura;
        }
        return typedQuery.getResultList().get(0);
    }


}
