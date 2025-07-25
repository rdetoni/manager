package br.com.wallet.manager.controller.requests;

import br.com.wallet.manager.model.enums.BondType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BondCreateRequest {
    @NotNull
    @Size(min = 1, max = 100)
    private String name;

    @NotNull
    private BondType type;

    @NotNull
    @Positive
    private Double interest;

    @NotNull
    @Positive
    private Double amount;
}
