package account_transaction.ZealousBank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class accountservice {
    @Autowired
    accountrepository repo;

    public AccountEntity creation(AccountEntity accountdetails) {
        return repo.save(accountdetails);
    }

    public List<AccountEntity> allaccountholders() {
        return repo.findAll();
    }

    public AccountEntity findbyaccount(Long accountno) {
        return repo.findById(accountno).orElse(new AccountEntity());
    }

    public String deletebyaccountno(Long accountno) {
        AccountEntity accountdetail = repo.findById(accountno).orElse(new AccountEntity());

        repo.deleteById(accountno);

        return accountdetail.getAccountHoldername() + " has been deleted successfully";
    }

    public List<AccountEntity> getallvaluesbyplace(String place) {
        return repo.findByAccountHolderplace(place);
    }

}
