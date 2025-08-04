package br.com.wallet.manager.domain.interfaces;

import br.com.wallet.manager.controller.requests.AssetCreateRequest;
import br.com.wallet.manager.domain.exceptions.BrapiErrorException;
import br.com.wallet.manager.domain.exceptions.CreateAssetException;
import br.com.wallet.manager.domain.exceptions.FiiCrawlerErrorException;

public interface AssetCreationStrategy {
    public void create(AssetCreateRequest request) throws BrapiErrorException, FiiCrawlerErrorException, CreateAssetException;
}
