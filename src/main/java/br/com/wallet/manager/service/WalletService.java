package br.com.wallet.manager.service;

import br.com.wallet.manager.controller.requests.AssetCreateRequest;
import br.com.wallet.manager.controller.requests.BondCreateRequest;
import br.com.wallet.manager.controller.requests.CriptoCreateRequest;
import br.com.wallet.manager.model.entities.BRStock;
import br.com.wallet.manager.model.entities.Bonds;
import br.com.wallet.manager.model.entities.Cripto;
import br.com.wallet.manager.model.entities.FII;
import br.com.wallet.manager.model.entities.USStock;
import br.com.wallet.manager.model.repositories.BRStockRepository;
import br.com.wallet.manager.model.repositories.BondsRepository;
import br.com.wallet.manager.model.repositories.CriptoRepository;
import br.com.wallet.manager.model.repositories.FIIRepository;
import br.com.wallet.manager.model.repositories.USStockRepository;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class WalletService {
    private final BondsRepository bondsRepository;
    private final BRStockRepository brStockRepository;
    private final CriptoRepository criptoRepository;
    private final FIIRepository fiiRepository;
    private final USStockRepository usStockRepository;
    private final FinnhubService finnhubService;
    private final FiiCrawlerService fiiCrawlerService;

    public WalletService(BondsRepository bondsRepository,
                        BRStockRepository brStockRepository,
                        CriptoRepository criptoRepository,
                        FIIRepository fiiRepository,
                        USStockRepository usStockRepository,
                        FinnhubService finnhubService,
                        FiiCrawlerService fiiCrawlerService) {
        this.bondsRepository = bondsRepository;
        this.brStockRepository = brStockRepository;
        this.criptoRepository = criptoRepository;
        this.fiiRepository = fiiRepository;
        this.usStockRepository = usStockRepository;
        this.finnhubService = finnhubService;
        this.fiiCrawlerService = fiiCrawlerService;
    }

    public void createBond(BondCreateRequest request) {
        val bond = Bonds.builder()
                .bondType(request.getType())
                .name(request.getName())
                .interest(request.getInterest())
                .currentValue(BigDecimal.valueOf(request.getAmount()))
                .status("ACTIVE")
                .build();

        bondsRepository.save(bond);
    }

    @Transactional
    public void createBrStock(AssetCreateRequest request) {
        val averagePrice = request.getPaidAmount().divide(BigDecimal.valueOf(request.getQuantity()),
                RoundingMode.HALF_UP);
        BigDecimal lastPrice;
        try{
            lastPrice = BigDecimal.valueOf(this.finnhubService.getQuote(request.getTicker()).getC());
        }catch (Exception e) {
            throw new RuntimeException("Failed to fetch quote from Finnhub", e);
        }
        val gainLoss = lastPrice.subtract(averagePrice).multiply(BigDecimal.valueOf(request.getQuantity()));
        val gainLossPercentage = gainLoss.divide(averagePrice, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        val currentTotal = lastPrice.multiply(BigDecimal.valueOf(request.getQuantity()));

        val brStock = BRStock.builder()
                .ticker(request.getTicker())
                .name(request.getName())
                .averagePrice(averagePrice)
                .lastPrice(lastPrice)
                .gainLoss(gainLoss)
                .gainLossPercentage(gainLossPercentage)
                .investedTotal(request.getPaidAmount())
                .currentTotal(currentTotal)
                .quantity(request.getQuantity())
                .build();

        this.brStockRepository.save(brStock);
    }

    @Transactional
    public void createCripto(CriptoCreateRequest request) {
        val averagePrice = request.getPaidAmount().divide(request.getQuantity(), RoundingMode.HALF_UP);
        BigDecimal lastPrice;
        try {
            lastPrice = BigDecimal.valueOf(this.finnhubService.getQuote(request.getTicker()).getC());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch cripto quote from Finnhub", e);
        }
        val gainLoss = lastPrice.subtract(averagePrice).multiply(request.getQuantity());
        val gainLossPercentage = gainLoss.divide(averagePrice, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        val currentTotal = lastPrice.multiply(request.getQuantity());

        val cripto = Cripto.builder()
                .ticker(request.getTicker())
                .gainLoss(gainLoss)
                .gainLossPercentage(gainLossPercentage)
                .investedTotal(request.getPaidAmount())
                .currentTotal(currentTotal)
                .brlValue(request.getBrlValue())
                .quantity(request.getQuantity())
                .averagePrice(averagePrice)
                .build();

        this.criptoRepository.save(cripto);
    }

    @Transactional
    public void createFii(AssetCreateRequest request) {
        val averagePrice = request.getPaidAmount().divide(BigDecimal.valueOf(request.getQuantity()),
                RoundingMode.HALF_UP);
        BigDecimal lastPrice;
        try {
            lastPrice = BigDecimal.valueOf(this.finnhubService.getQuote(request.getTicker()).getC());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch FII last price", e);
        }
        val gainLoss = lastPrice.subtract(averagePrice).multiply(BigDecimal.valueOf(request.getQuantity()));
        val gainLossPercentage = gainLoss.divide(averagePrice, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        val currentTotal = lastPrice.multiply(BigDecimal.valueOf(request.getQuantity()));
        double dividendYield;
        double pVp;
        try {
            val fiiCrawlerResponse = this.fiiCrawlerService.getFiiData(request.getTicker());
            dividendYield = Double.parseDouble(fiiCrawlerResponse.getLastDividend());
            pVp = Double.parseDouble(fiiCrawlerResponse.getPVp());
        }catch (Exception e) {
            throw new RuntimeException("Failed to fetch FII dividend yield", e);
        }
        val dividendYieldOnCost = dividendYield * 100 / averagePrice.doubleValue();
        val pVpOnCost = pVp * 100 / averagePrice.doubleValue();

        val fii = FII.builder()
                .ticker(request.getTicker())
                .name(request.getName())
                .averagePrice(averagePrice)
                .lastPrice(lastPrice)
                .gainLoss(gainLoss)
                .gainLossPercentage(gainLossPercentage)
                .investedTotal(request.getPaidAmount())
                .currentTotal(currentTotal)
                .quantity(request.getQuantity())
                .dividendYield(dividendYield)
                .dividendYieldOnCost(dividendYieldOnCost)
                .pVp(pVp)
                .pVpOnCost(pVpOnCost)
                .build();

        this.fiiRepository.save(fii);
    }

    public void createUsStock(AssetCreateRequest request) {
        val averagePrice = request.getPaidAmount().divide(BigDecimal.valueOf(request.getQuantity()),
                RoundingMode.HALF_UP);
        BigDecimal lastPrice;
        try {
            lastPrice = BigDecimal.valueOf(this.finnhubService.getQuote(request.getTicker()).getC());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch US stock quote from Finnhub", e);
        }
        val gainLoss = lastPrice.subtract(averagePrice).multiply(BigDecimal.valueOf(request.getQuantity()));
        val gainLossPercentage = gainLoss.divide(averagePrice, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
        val currentTotal = lastPrice.multiply(BigDecimal.valueOf(request.getQuantity()));

        val usStock = USStock.builder()
                .ticker(request.getTicker())
                .name(request.getName())
                .averagePrice(averagePrice)
                .lastPrice(lastPrice)
                .gainLoss(gainLoss)
                .gainLossPercentage(gainLossPercentage)
                .investedTotal(request.getPaidAmount())
                .currentTotal(currentTotal)
                .quantity(request.getQuantity())
                .build();

        this.usStockRepository.save(usStock);
    }
}
