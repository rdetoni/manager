package br.com.wallet.manager.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class FinnHubQuote {
    private double c;   // Current price
    private double h;   // High price of the day
    private double l;   // Low price of the day
    private double o;   // Open price of the day
    private double pc;  // Previous close price
    private long t;     // Timestamp
}
