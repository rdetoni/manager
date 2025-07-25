package br.com.wallet.manager.domain.interfaces;

import br.com.wallet.manager.controller.requests.AssetCreateRequest;

public interface AssetCreationStrategy {
    public void create(AssetCreateRequest request);
}
