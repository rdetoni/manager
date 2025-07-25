package br.com.wallet.manager.service;

import br.com.wallet.manager.model.entities.FinnHubQuote;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FinnhubService {

    private final RestTemplate restTemplate;

    @Value("${services.finhub.api.key}")
    private String apiKey;

    @Value("${services.finnhub.url}")
    private String baseUrl;

    public FinnhubService() {
        this.restTemplate = new RestTemplate();
    }

    @Cacheable("quotes")
    public FinnHubQuote getQuote(String symbol) {
        log.info("Getting latest price for {}", symbol);
        String url = String.format("%s/quote?symbol=%s&token=%s", baseUrl, symbol, apiKey);
        return restTemplate.getForObject(url, FinnHubQuote.class);
    }
}
