package domain;

import exceptions.InsufficientBalanceException;

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

    public void deposit(BigDecimal amount) {
        if (amount != null) {
            this.balance = this.balance.add(amount).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public void withdraw(BigDecimal amount) throws InsufficientBalanceException {
        if (amount != null) {
            if (this.balance.subtract(amount).doubleValue() >= 0) {
                this.balance = this.balance.subtract(amount).setScale(2, RoundingMode.HALF_UP);
            } else {
                throw new InsufficientBalanceException();
            }
        }
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "balance=" + balance +
                '}';
    }
}
