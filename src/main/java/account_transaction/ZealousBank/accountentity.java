package account_transaction.ZealousBank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accountdetails")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "my_sequence_gen")
    @SequenceGenerator(name = "my_sequence_gen", sequenceName = "my_sequence", allocationSize = 1)
    private Long accountNumber;
    private String accountHoldername;
    private String accountIfsccode;
    private BigDecimal accountBalance;
    @Column(name = "contactno")
    private Long accountHoldercontactno;
    @Column(name = "Place")
    private String accountHolderplace;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    @JoinTable(name = "Connection", joinColumns = @JoinColumn(name = "accountNumber"), inverseJoinColumns = @JoinColumn(name = "transactionNumber"))
    private List<TransactionEntity> transactionlist = new ArrayList<>();

}
