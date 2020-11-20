package br.com.zup.fatura.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class CartaoVirtual {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    private UUID numeroCartao = UUID.randomUUID();
    @ManyToOne
    private Cartao cartao;
    private BigDecimal limite;
    private LocalDateTime validadeUso = LocalDateTime.now().plusDays(1);

    @Deprecated
    public CartaoVirtual(){}

    public CartaoVirtual(Cartao cartao, BigDecimal limite) {
        this.cartao = cartao;
        this.limite = limite;
    }

    public UUID getId() {
        return id;
    }

}
