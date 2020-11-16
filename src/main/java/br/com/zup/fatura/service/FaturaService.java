package br.com.zup.fatura.service;

import br.com.zup.fatura.model.Fatura;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
public class FaturaService {

    private EntityManager entityManager;

    public FaturaService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Fatura buscarFatura(Fatura fatura){ //3

        TypedQuery<Fatura> query = entityManager
                .createQuery("select f from Fatura f where f.cartao.numeroCartao =:numero and mes =:mes and ano =:ano",
                        Fatura.class);

        query.setParameter("numero", fatura.numeroCartao());
        query.setParameter("mes", fatura.getMes());
        query.setParameter("ano", fatura.getAno());

        if (query.getResultList().isEmpty()){ //4
            entityManager.persist(fatura);
            return fatura;
        }
        return query.getResultList().get(0);
    }

}
