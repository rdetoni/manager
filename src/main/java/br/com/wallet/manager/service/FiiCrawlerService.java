package br.com.wallet.manager.service;

import br.com.wallet.manager.domain.exceptions.FiiCrawlerErrorException;
import br.com.wallet.manager.model.entities.FiiCrawler;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
public class FiiCrawlerService {
    private final RestTemplate restTemplate;

    @Value("${services.fii.crawler.url}")
    private String baseUrl;

    public FiiCrawlerService() {
        this.restTemplate = new RestTemplate();
    }

    public FiiCrawler getFiiData(String code) throws FiiCrawlerErrorException{
        log.info("Fetching extra FII info data for code: {}", code);
        String url = String.format("%s/getFii?code=%s", baseUrl, code);
        val response = Optional.ofNullable(restTemplate.getForObject(url, FiiCrawler.class))
                .orElseThrow(() -> new FiiCrawlerErrorException("Error fetching extra data for " + code));
        if(response.getLastDividend() == null || response.getPVp() == null || response.getEquityValue() == null) {
            throw new FiiCrawlerErrorException("FII Crawler could not obtain some data for " + code);
        } else {
            return response;
        }
    }
}
