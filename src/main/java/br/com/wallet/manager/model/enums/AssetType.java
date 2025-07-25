package br.com.wallet.manager.model.enums;

public enum AssetType {
    BR_STOCK("Brazilian Stock"),
    US_STOCK("US Stock"),
    FII("Real State Investment Trust");

    private final String description;

    AssetType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
