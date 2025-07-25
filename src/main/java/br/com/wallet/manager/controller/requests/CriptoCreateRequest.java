package br.com.wallet.manager.controller.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CriptoCreateRequest {
    @NotNull
    private String ticker;

    @NotNull
    private BigDecimal paidAmount;

    @NotNull
    private BigDecimal quantity;

    @NotNull
    private BigDecimal brlValue;
}
