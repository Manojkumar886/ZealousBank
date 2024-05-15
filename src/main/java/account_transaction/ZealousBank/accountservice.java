package account_transaction.ZealousBank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class accountservice {
    @Autowired
    accountrepository repo;

    public accountentity creation(accountentity accountdetails) {
        return repo.save(accountdetails);
    }

    public List<accountentity> allaccountholders() {
        return repo.findAll();
    }

    public accountentity findbyaccount(Long accountno) {
        return repo.findById(accountno).orElse(new accountentity());
    }

}
