package br.com.zup.fatura.model;

import br.com.zup.fatura.model.enums.StatusAprovacaoRenegociacao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class RenegociacaoFatura {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Min(1)
    private int quantidade;
    @Min(1)
    private BigDecimal valor;
    @ManyToOne @Valid
    private Cartao cartao;
    @ManyToOne @Valid
    private Fatura fatura;
    private LocalDate dataPagamento;
    @Enumerated(EnumType.STRING)
    private StatusAprovacaoRenegociacao statusAprovacaoRenegociacao;

    @Deprecated
    public RenegociacaoFatura(){}

    public RenegociacaoFatura(Fatura fatura, int quantidade, BigDecimal valor) {
        this.fatura = fatura;
        this.quantidade = quantidade;
        this.valor = valor;
        this.cartao = fatura.getCartao();
        this.dataPagamento = LocalDate.now().plusDays(120);//variavel de ambiente
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void atualizarStatusRenegociacao(StatusAprovacaoRenegociacao solicitarRenegociacao) {
        this.statusAprovacaoRenegociacao = solicitarRenegociacao;
    }

}
