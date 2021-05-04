package domain;

import exceptions.InsufficientAmountException;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidAmountException;
import exceptions.InvalidFeeException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Wallet implements Serializable {
    private final UUID id;
    private final String name;
    private final CryptoCurrency cryptoCurrency;
    private BigDecimal amount;
    private final List<Transaction> transactions;
    private BigDecimal feeInPercent;

    public Wallet(String name, CryptoCurrency cryptoCurrency, BigDecimal feeInPercent) throws InvalidFeeException {
        this.id = UUID.randomUUID();
        this.name = name;
        this.cryptoCurrency = cryptoCurrency;
        this.amount = new BigDecimal("0");
        this.transactions = new ArrayList<>();
        this.setNewFee(feeInPercent);
    }

    public void buy(BigDecimal amount, BigDecimal currentPrice, BankAccount bankAccount) throws InvalidAmountException, InsufficientBalanceException {
        if (amount.compareTo(new BigDecimal("0")) <= 0) {
            throw new InvalidAmountException();
        }
        Transaction transaction = new Transaction(this.cryptoCurrency, amount, currentPrice.setScale(6, RoundingMode.HALF_UP));
        bankAccount.withdraw(
                transaction.getTotal().multiply
                        (new BigDecimal("100").add(this.feeInPercent).divide(new BigDecimal("100"))
                        .setScale(6, RoundingMode.HALF_UP)));
        this.transactions.add(transaction);
        this.amount = this.amount.add(transaction.getAmount());
    }

    public void sell(BigDecimal amount, BigDecimal currentPrice, BankAccount bankAccount) throws InsufficientAmountException, InvalidAmountException {
        if (amount.compareTo(new BigDecimal("0")) <= 0) {
            throw new InvalidAmountException();
        }
        BigDecimal reducedAccount = this.amount.subtract(amount);
        if (reducedAccount.compareTo(new BigDecimal("0")) < 0) {
            throw new InsufficientAmountException();
        }
        Transaction transaction = new Transaction(this.cryptoCurrency, amount.negate(), currentPrice.setScale(6, RoundingMode.HALF_UP));
        bankAccount.deposit(
                transaction.getTotal().negate().multiply
                        (new BigDecimal("100").subtract(this.feeInPercent).divide(new BigDecimal("100")))
                        .setScale(6, RoundingMode.HALF_UP));
        this.transactions.add(transaction);
        this.amount = reducedAccount;
    }

    public void setNewFee(BigDecimal fee) throws InvalidFeeException {
        if (fee.compareTo(new BigDecimal("0")) >= 0) {
            this.feeInPercent = fee;
        } else {
            throw new InvalidFeeException();
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CryptoCurrency getCryptoCurrency() {
        return cryptoCurrency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public List<Transaction> getTransactions() {
        return List.copyOf(transactions);
    }

    public BigDecimal getFeeInPercent() {
        return feeInPercent;
    }

    public String getCurrencyName() {
        return this.cryptoCurrency.getCurrencyName();
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cryptoCurrency=" + cryptoCurrency +
                ", amount=" + amount +
                ", transactions=" + transactions +
                ", feeInPercent=" + feeInPercent +
                '}';
    }
}
