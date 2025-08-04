package br.com.wallet.manager.model.repositories;

import br.com.wallet.manager.model.entities.FII;
import org.springframework.data.repository.CrudRepository;

public interface FIIRepository extends CrudRepository<FII, Long> {
    public boolean existsByTicker(String ticker);
}
