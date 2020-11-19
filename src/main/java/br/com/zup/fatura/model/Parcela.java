package br.com.zup.fatura.model;

import br.com.zup.fatura.model.enums.StatusAprovacaoParcelamento;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Parcela {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private int quantidade;
    private BigDecimal valorParcela;
    @ManyToOne
    private Fatura fatura;
    private BigDecimal totalFatura;
    @Enumerated(EnumType.STRING)
    private StatusAprovacaoParcelamento statusParcelamento;

    @Deprecated
    public Parcela(){}

    public Parcela(int quantidade, BigDecimal valorParcela, Fatura fatura) {
        this.quantidade = quantidade;
        this.valorParcela = valorParcela;
        this.fatura = fatura;
        this.totalFatura = fatura.calcularFatura();
    }

    public UUID getId() {
        return id;
    }

    public void atualizaStatusParcelamento(StatusAprovacaoParcelamento status){
        this.statusParcelamento = status;
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
