package account_transaction.ZealousBank;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactiondetails")
public class TransactionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_sequence_gen")
  @SequenceGenerator(name = "my_sequence_gen", sequenceName = "my_sequence", allocationSize = 1)
  private Long transactionNumber;
  private String transactionType;
  private Double currentBalance;
  private Double transactionAmount;
  private String transactionHolderNumber;
  @Column(name = "transaction_date")
  private Date transactionDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "accountNumber", nullable = false)
  private AccountEntity account;

}
