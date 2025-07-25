package br.com.wallet.manager.controller.requests;

import br.com.wallet.manager.model.enums.AssetType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class AssetCreateRequest {
    @NotNull
    private String ticker;

    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private BigDecimal paidAmount;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    private AssetType type;
}
