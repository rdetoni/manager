package br.com.wallet.manager.controller;

import br.com.wallet.manager.controller.requests.AssetCreateRequest;
import br.com.wallet.manager.controller.requests.AssetUpdateRequest;
import br.com.wallet.manager.controller.requests.BondCreateRequest;
import br.com.wallet.manager.controller.requests.CriptoCreateRequest;
import br.com.wallet.manager.domain.components.AssetStrategyFactory;
import br.com.wallet.manager.domain.exceptions.BrapiErrorException;
import br.com.wallet.manager.domain.exceptions.CreateAssetException;
import br.com.wallet.manager.domain.exceptions.FiiCrawlerErrorException;
import br.com.wallet.manager.domain.exceptions.FinnHubErrorException;
import br.com.wallet.manager.domain.exceptions.UpdateAssetException;
import br.com.wallet.manager.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/wallet-manager")
public class WalletController {
    private WalletService walletService;
    private AssetStrategyFactory assetStrategyFactory;

    @Autowired
    public WalletController(WalletService walletService, AssetStrategyFactory assetStrategyFactory) {
        this.walletService = walletService;
        this.assetStrategyFactory = assetStrategyFactory;
    }

    @PostMapping("/bond")
    @ResponseStatus(HttpStatus.CREATED)
    public void createBond(@RequestBody @Valid BondCreateRequest request) {
        this.walletService.createBond(request);
    }

    @PostMapping("/asset")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAsset(@RequestBody @Valid AssetCreateRequest request) throws BrapiErrorException,
                                                                                   FiiCrawlerErrorException,
                                                                                   CreateAssetException {
        this.assetStrategyFactory.getStrategy(request.getType()).create(request);
    }

    @PatchMapping("/asset")
    @ResponseStatus(HttpStatus.OK)
    public void updateAsset(@RequestBody @Valid AssetUpdateRequest request) throws UpdateAssetException,
                                                                                   BrapiErrorException,
                                                                                   FiiCrawlerErrorException {
        this.assetStrategyFactory.getStrategy(request.getType()).update(request);
    }

    @PostMapping("/cripto")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCripto(@RequestBody @Valid CriptoCreateRequest request){
        this.walletService.createCripto(request);
    }
}
