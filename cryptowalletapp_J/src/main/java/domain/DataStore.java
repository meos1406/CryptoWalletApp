package domain;

import exceptions.RetrieveDataException;
import exceptions.SaveDataException;

public interface DataStore {
    void saveBankAccount(BankAccount bankAccount) throws SaveDataException;

    void saveWalletList(WalletList walletList) throws SaveDataException;

    BankAccount retrieveBankAccount() throws RetrieveDataException;

    WalletList retrieveWalletList() throws RetrieveDataException;
}
