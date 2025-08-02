package br.com.wallet.manager.service;

import br.com.wallet.manager.domain.exceptions.FinnHubErrorException;
import br.com.wallet.manager.model.entities.FinnHubQuote;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

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
    public FinnHubQuote getQuote(String symbol) throws FinnHubErrorException{
        log.info("Getting latest price for {}", symbol);
        String url = String.format("%s/quote?symbol=%s&token=%s", baseUrl, symbol, apiKey);
        val response = Optional.ofNullable(restTemplate.getForObject(url, FinnHubQuote.class))
                .orElseThrow(() -> new FinnHubErrorException("Error obtaining FinnHub quote"));
        if(response.getC() == 0){
            throw new FinnHubErrorException("No current value could be obtained for " + symbol);
        } else {
            return response;
        }
    }
}
