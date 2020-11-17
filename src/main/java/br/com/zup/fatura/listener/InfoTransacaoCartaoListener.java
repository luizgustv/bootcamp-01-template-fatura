package br.com.zup.fatura.listener;


import br.com.zup.fatura.model.Cartao;
import br.com.zup.fatura.model.Fatura;
import br.com.zup.fatura.model.TransacaoCartao;
import br.com.zup.fatura.request.TransacaoCartaoRequest;
import br.com.zup.fatura.service.CartaoService;
import br.com.zup.fatura.service.FaturaService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.UUID;

@Component
public class InfoTransacaoCartaoListener {

    private EntityManager entityManager;
    private CartaoService cartaoService; //1
    private FaturaService faturaService; //2

    public InfoTransacaoCartaoListener(EntityManager entityManager,
                                       CartaoService cartaoService, FaturaService faturaService) {
        this.entityManager = entityManager;
        this.cartaoService = cartaoService;
        this.faturaService = faturaService;
    }

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    @Transactional
    public void ouvir(TransacaoCartaoRequest request) {

        Integer mes = request.getEfetivadaEm().getMonthValue();
        Integer ano = request.getEfetivadaEm().getYear();

        TransacaoCartao novaTransacaoCartao = request.toTransacaoCartao();
        entityManager.persist(novaTransacaoCartao);

        Cartao cartao = cartaoService.buscarCartao(request.getCartao()); //3

        Fatura fatura = faturaService.buscarFaturaByFatura(new Fatura(ano, mes, cartao)); //4
        fatura.adicionarTrasacaoNaFatura(novaTransacaoCartao);
        entityManager.merge(fatura);

    }

}
