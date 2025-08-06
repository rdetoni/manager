package br.com.wallet.manager.controller.requests;

import br.com.wallet.manager.model.enums.AssetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AssetUpdateRequest {
    @NotNull
    private String ticker;

    private Double fees;

    @NotNull
    private BigDecimal paidAmount;

    @NotNull
    @Positive
    private Double quantity;

    @NotNull
    private AssetType type;
}
