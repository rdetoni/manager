package br.com.wallet.manager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrapiResult {
    private List<BrapiQuote> results;

    @Getter
    @Setter
    public static class BrapiQuote {
        private String symbol;
        private String shortName;
        private String longName;
        private String currency;
        private BigDecimal regularMarketPrice;
        private BigDecimal regularMarketDayHigh;
        private BigDecimal regularMarketDayLow;
        private BigDecimal regularMarketChange;
        private BigDecimal regularMarketChangePercent;
        private LocalDateTime regularMarketTime;
        private Long marketCap;
        private Long regularMarketVolume;
        private String logourl;
    }
}
