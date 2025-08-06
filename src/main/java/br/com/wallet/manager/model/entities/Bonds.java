package br.com.wallet.manager.model.entities;

import br.com.wallet.manager.model.enums.BondType;
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

@Entity(name = "bonds")
@Getter
@Setter
@Builder
public class Bonds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bond_type", nullable = false)
    private BondType bondType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "interest")
    private Double interest;

    @Column(name = "current_value", nullable = false)
    private BigDecimal currentValue;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
