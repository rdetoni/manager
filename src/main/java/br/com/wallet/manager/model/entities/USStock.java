package br.com.wallet.manager.model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "american_stocks")
@Getter
@Setter
@Builder
public class USStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ticker", nullable = false)
    private String ticker;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "average_price", nullable = false)
    private BigDecimal averagePrice;

    @Column(name = "last_price", nullable = false)
    private BigDecimal lastPrice;

    @Column(name = "gain_loss", nullable = false)
    private BigDecimal gainLoss;

    @Column(name = "gain_loss_percentage", nullable = false)
    private BigDecimal gainLossPercentage;

    @Column(name = "invested_total", nullable = false)
    private BigDecimal investedTotal;

    @Column(name = "current_total", nullable = false)
    private BigDecimal currentTotal;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
