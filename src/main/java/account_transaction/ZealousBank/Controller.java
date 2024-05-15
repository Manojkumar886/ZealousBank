package account_transaction.ZealousBank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
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
    public String accountcreate(@RequestBody accountentity accoundetails) {
        return service.creation(accoundetails).getAccountHoldername() + " has been created successfully";
    }

    @GetMapping("/")
    public List<accountentity> alldetails() {
        return service.allaccountholders();
    }

    @GetMapping("/findbyaccount/{accno}")
    public accountentity getaccdetail(@PathVariable("accno") long accno) {
        return service.findbyaccount(accno);
    }

    @PutMapping("/updateaccountdetails")
    public String updateaccount(@RequestBody accountentity accountdetails) {
        accountentity account1 = service.creation(accountdetails);

        return account1.getAccountNumber() + " your account has been updated successfully";
    }

}
