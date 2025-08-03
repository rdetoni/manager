package br.com.wallet.manager.service;

import br.com.wallet.manager.domain.exceptions.BrapiErrorException;
import br.com.wallet.manager.model.entities.BrapiResult;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BrapiService {
    private final RestTemplate restTemplate;

    @Value("${services.brapi.url}")
    private String brapiUrl;

    @Value("${services.brapi.api.key}")
    private String brapiSecret;

    public BrapiService(){this.restTemplate = new RestTemplate();}

    @Cacheable("quotes")
    public BrapiResult.BrapiQuote getQuote(String symbol) throws BrapiErrorException{
       log.info("Get latest info for: {}", symbol);
       String url = String.format("%s%s", brapiUrl, symbol);

       HttpHeaders headers = new HttpHeaders();
       headers.setBearerAuth(brapiSecret);
       headers.setAccept(List.of(MediaType.APPLICATION_JSON));

       HttpEntity<Void> entity = new HttpEntity<>(headers);

       ResponseEntity<BrapiResult> responseEntity = restTemplate
               .exchange(url, HttpMethod.GET, entity, BrapiResult.class);

       val response = Optional.ofNullable(responseEntity.getBody())
               .orElseThrow(() -> new BrapiErrorException("Error obtaining info from Brapi for " + symbol));
       if(response.getResults().isEmpty()){
           throw new BrapiErrorException("No info was returned for " + symbol);
       } else {
           return response.getResults().getFirst();
       }
    }
}
