package br.com.zup.fatura.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Fatura {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private Integer ano;
    private Integer mes;
    @ManyToOne
    private Cartao cartao;
    @OneToMany
    private List<TransacaoCartao> transacoes = new ArrayList<>();

    @Deprecated
    public Fatura(){}

    public Fatura(Integer ano, Integer mes, Cartao cartao) {
        this.ano = ano;
        this.mes = mes;
        this.cartao = cartao;
    }


    public Integer getAno() {
        return ano;
    }

    public Integer getMes() {
        return mes;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public List<TransacaoCartao> getTransacoes() {
        return transacoes;
    }

    public UUID numeroCartao(){
        return this.cartao.getNumeroCartao();
    }

    public void adicionarTrasacaoNaFatura(TransacaoCartao transacaoCartao){
        this.transacoes.add(transacaoCartao);
    }

    @Override
    public String toString() {
        return "Fatura{" +
                "ano=" + ano +
                ", mes=" + mes +
                ", cartao=" + cartao +
                ", transacoes=" + transacoes +
                '}';
    }

}
