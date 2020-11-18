package br.com.zup.fatura.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NamedQuery(name = "buscarFaturaPorNumCartao", query = "select f from Fatura f " +
        "where f.cartao.numeroCartao =:numero and mes =:mes and ano =:ano")
@NamedQuery(name = "buscarDezUltimasTransacoes", query = "select f from Fatura f " +
        "join fetch f.transacoes t" +
        " where f.cartao.numeroCartao =:numero and mes =:mes and ano =:ano" +
        " order by t.efetivadaEm desc")
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

    public UUID getId() {
        return id;
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

    public String numeroCartao(){
        return this.cartao.getNumeroCartao();
    }

    public void adicionarTrasacaoNaFatura(TransacaoCartao transacaoCartao){
        this.transacoes.add(transacaoCartao);
    }

    public Boolean faturaPertenceCartao(UUID idCartao){
        return this.cartao.getId().equals(idCartao);
    }

    public Boolean faturaPertenceMesCorrente(){
        return this.mes == LocalDateTime.now().getMonthValue();
    }

    public BigDecimal calcularFatura(){
        return this.transacoes.stream().map(t -> t.getValor()).reduce(
                BigDecimal.ZERO, (s1, s2) -> s1.add(s2));
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
