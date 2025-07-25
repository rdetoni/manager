package br.com.wallet.manager.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
@Slf4j
public class CacheEvictor {
    @Autowired
    private CacheManager cacheManager;

    @Scheduled(fixedRate = 1200000) // Evict cache every 20 minutes
    public void evicAllQuotes(){
        Objects.requireNonNull(cacheManager.getCache("quotes")).clear();
        log.info("Cache 'quotes' has been cleared at {}", LocalDateTime.now());
    }
}
