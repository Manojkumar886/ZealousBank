package account_transaction.ZealousBank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface accountrepository extends JpaRepository<accountentity, Long> {

}
