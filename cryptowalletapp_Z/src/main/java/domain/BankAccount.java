package domain;

import exceptions.InsufficientBalanceException;
import exceptions.InvalidAmountException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount implements Serializable {
    private BigDecimal balance;

    public BankAccount() {
        this.balance = new BigDecimal("0").setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) throws InvalidAmountException {
        if (amount == null) return;
        if (amount.compareTo(BigDecimal.ZERO)<=0) {
            throw new InvalidAmountException();
        }
        this.setBalance(this.balance.add(amount));
    }

    public void withdraw(BigDecimal amount) throws InsufficientBalanceException, InvalidAmountException {
        if (amount == null) return;
        if (amount.compareTo(BigDecimal.ZERO)<=0) {
            throw new InvalidAmountException();
        }
        if (this.balance.subtract(amount).setScale(2, RoundingMode.HALF_UP).compareTo(BigDecimal.ZERO) <= 0) {
            throw new InsufficientBalanceException();
        }
        this.setBalance(this.balance.subtract(amount));
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}
