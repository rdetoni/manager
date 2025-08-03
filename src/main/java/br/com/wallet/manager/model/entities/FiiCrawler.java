package br.com.wallet.manager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FiiCrawler {
    private String code;
    private String price;
    private String lastDividend;

    @JsonProperty("pVp")
    private String pVp;
}
