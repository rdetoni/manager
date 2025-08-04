package br.com.wallet.manager.domain.components;

import br.com.wallet.manager.controller.requests.AssetCreateRequest;
import br.com.wallet.manager.domain.exceptions.BrapiErrorException;
import br.com.wallet.manager.domain.exceptions.CreateAssetException;
import br.com.wallet.manager.domain.exceptions.FiiCrawlerErrorException;
import br.com.wallet.manager.domain.interfaces.AssetCreationStrategy;
import br.com.wallet.manager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FiiStrategy implements AssetCreationStrategy {
    private WalletService walletService;

    @Autowired
    public FiiStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    public void create(AssetCreateRequest request) throws BrapiErrorException, FiiCrawlerErrorException, CreateAssetException {
        this.walletService.createFii(request);
    }
}
