package br.com.wallet.manager.domain.components;

import br.com.wallet.manager.domain.interfaces.AssetCreationStrategy;
import br.com.wallet.manager.model.enums.AssetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
public class AssetStrategyFactory {
    private final Map<AssetType, AssetCreationStrategy> assetStrategies = new EnumMap<>(AssetType.class);

    @Autowired
    public AssetStrategyFactory (BrStockStrategy brStockStrategy,
                                 FiiStrategy fiiStrategy,
                                 UsStockStrategy usStockStrategy) {
        this.assetStrategies.put(AssetType.BR_STOCK, brStockStrategy);
        this.assetStrategies.put(AssetType.FII, fiiStrategy);
        this.assetStrategies.put(AssetType.US_STOCK, usStockStrategy);
    }

    public AssetCreationStrategy getStrategy(AssetType assetType) {
        return this.assetStrategies.get(assetType);
    }
}
