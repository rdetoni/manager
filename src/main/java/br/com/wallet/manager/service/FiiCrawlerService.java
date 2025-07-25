package br.com.wallet.manager.service;

import br.com.wallet.manager.model.entities.FiiCrawler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class FiiCrawlerService {
    private final RestTemplate restTemplate;

    @Value("${services.fii.crawler.url}")
    private String baseUrl;

    public FiiCrawlerService() {
        this.restTemplate = new RestTemplate();
    }

    public FiiCrawler getFiiData(String code) {
        log.info("Fetching FII data for code: {}", code);
        String url = String.format("%s/getFii?code=%s", baseUrl, code);
        return restTemplate.getForObject(url, FiiCrawler.class);
    }
}
