package ui;

import at.itkolleg.sample.WalletApp;
import domain.BankAccount;
import domain.WalletList;

public class BaseControllerState {
    private WalletList walletList;
    private BankAccount bankAccount;

    public BaseControllerState() {
        bankAccount = (BankAccount) GlobalContext.getGlobalContext().getStateFor(WalletApp.GLOBAL_BANK_ACCOUNT);
        walletList = (WalletList) GlobalContext.getGlobalContext().getStateFor(WalletApp.GLOBAL_WALLET_LIST);
    }

    public WalletList getWalletList() {
        return walletList;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }
}
