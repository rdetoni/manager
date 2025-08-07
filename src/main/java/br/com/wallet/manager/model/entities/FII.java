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
import java.time.LocalDateTime;

@Entity(name = "fiis")
@Getter
@Setter
@Builder
public class FII {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Double quantity;

    @Column(name = "dividend_yield", nullable = false)
    private Double dividendYield;

    @Column(name = "dividend_yield_on_cost", nullable = false)
    private Double dividendYieldOnCost;

    @Column(name = "p_vp", nullable = false)
    private Double pVp;

    @Column(name = "p_vp_on_cost", nullable = false)
    private Double pVpOnCost;

    @Column(name = "last_dividend", nullable = false)
    private Double lastDividend;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
