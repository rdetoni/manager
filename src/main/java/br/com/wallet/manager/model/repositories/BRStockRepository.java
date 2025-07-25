package br.com.wallet.manager.model.repositories;

import br.com.wallet.manager.model.entities.BRStock;
import org.springframework.data.repository.CrudRepository;

public interface BRStockRepository extends CrudRepository<BRStock, Long> { }
