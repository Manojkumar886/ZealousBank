package account_transaction.ZealousBank;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Account")
public class accountentity {

    @Id
    private Long accountNumber;
    private String accountHoldername;
    private String accountIfsccode;
    private BigDecimal accountBalance;
    @Column(name = "contactno")
    private Long accountHoldercontactno;
    @Column(name = "Place")
    private String accountHolderplace;

}
