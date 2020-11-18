package br.com.zup.fatura.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private int quantidade;
    private int valorParcela;
    @ManyToOne
    private Fatura fatura;
    private BigDecimal totalFatura;

    @Deprecated
    public Parcela(){}

    public Parcela(int quantidade, int valorParcela, Fatura fatura) {
        this.quantidade = quantidade;
        this.valorParcela = valorParcela;
        this.fatura = fatura;
        this.totalFatura = fatura.calcularFatura();
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Parcela{" +
                " fatura=" + fatura +
                ", totalFatura=" + totalFatura +
                ", quantidade=" + quantidade +
                ", valorParcela=" + valorParcela +
                '}';
    }

}
