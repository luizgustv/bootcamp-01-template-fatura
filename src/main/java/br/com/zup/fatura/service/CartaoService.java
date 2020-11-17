package br.com.zup.fatura.service;

import br.com.zup.fatura.model.Cartao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
public class CartaoService {

    private EntityManager entityManager;

    public CartaoService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public Cartao buscarCartao(Cartao cartao){ //1

        TypedQuery<Cartao> query = entityManager
                .createQuery("select c from Cartao c where c.numeroCartao =:numero",
                        Cartao.class);
        query.setParameter("numero", cartao.getNumeroCartao());

        if (query.getResultList().isEmpty()){ //2
            entityManager.persist(cartao);
            return cartao;
        }
        return query.getResultList().get(0);
    }

}
