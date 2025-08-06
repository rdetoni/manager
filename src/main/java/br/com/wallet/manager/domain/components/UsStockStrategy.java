package br.com.wallet.manager.domain.components;

import br.com.wallet.manager.controller.requests.AssetCreateRequest;
import br.com.wallet.manager.controller.requests.AssetUpdateRequest;
import br.com.wallet.manager.domain.interfaces.AssetCreationStrategy;
import br.com.wallet.manager.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsStockStrategy implements AssetCreationStrategy {
    private WalletService walletService;

    @Autowired
    public UsStockStrategy(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public void create(AssetCreateRequest request) {
        this.walletService.createUsStock(request);
    }

    @Override
    public void update(AssetUpdateRequest request) {}
}
