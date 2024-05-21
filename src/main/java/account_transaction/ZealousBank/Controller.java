package account_transaction.ZealousBank;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Controller {
    @Autowired
    accountservice service;

    @PostMapping("/accountcreate")
    public String accountcreate(@RequestBody AccountEntity accoundetails) {
        return service.creation(accoundetails).getAccountHoldername() + " has been created successfully";
    }

    @GetMapping("/")
    public List<AccountEntity> alldetails() {
        return service.allaccountholders();
    }

    @GetMapping("/findbyaccount/{accno}")
    public AccountEntity getaccdetail(@PathVariable("accno") long accno) {
        return service.findbyaccount(accno);
    }

    @PutMapping("/updateaccountdetails")
    public String updateaccount(@RequestBody AccountEntity accountdetails) {
        AccountEntity account1 = service.creation(accountdetails);

        return account1.getAccountNumber() + " your account has been updated successfully";
    }

    @DeleteMapping("/deletebyid/{accno}")
    public String deleteaccount(@PathVariable("accno") long accno) {

        return service.deletebyaccountno(accno) + " ..!";
    }

    @GetMapping("/findbyplace/{place}")
    public List<AccountEntity> findbyplace(@PathVariable("place") String place) {
        return service.getallvaluesbyplace(place);
    }

    @GetMapping("/just")
    public void sample() {
        System.out.println("welcome to zealous tech corp");
    }

    @Autowired
    transactionservice tservice;

    @PostMapping("/createtransaction")
    public TransactionEntity transactioncreate(@RequestBody TransactionEntity transactiondetails) {
        if (transactiondetails.getTransactionType().equals("credit")) {
            transactiondetails.setCurrentBalance(
                    transactiondetails.getCurrentBalance() + transactiondetails.getTransactionAmount());
        } else if (transactiondetails.getTransactionType().equals("debit")) {
            if (transactiondetails.getCurrentBalance() >= transactiondetails.getTransactionAmount()) {
                transactiondetails.setCurrentBalance(
                        transactiondetails.getCurrentBalance() - transactiondetails.getTransactionAmount());
            }
        }

        return tservice.createtransaction(transactiondetails);

    }

    @GetMapping("/getalltransaction")
    public List<TransactionEntity> getAllTransactions() {
        return tservice.listalltransaction();
    }

    @GetMapping("/transactionbyid/{id}")
    public ResponseEntity<TransactionEntity> getTransactionById(@PathVariable Long id) {
        Optional<TransactionEntity> transaction = tservice.findonetransaction(id);
        if (transaction.isPresent()) {
            return ResponseEntity.ok(transaction.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a transaction
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        tservice.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }

    // list url by one user

    @GetMapping("/gettransactionbyoneaccount/{accno}")
    public List<TransactionEntity> listdatas(@PathVariable("user") Long accno) {
        AccountEntity account = service.findbyaccount(accno);

        return tservice.gettransactionbyoneuser(account);
    }
}
