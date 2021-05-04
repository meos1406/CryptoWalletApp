package domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class WalletList implements Serializable {
    private final HashMap<CryptoCurrency, Wallet> wallets;

    public WalletList() {
        this.wallets = new HashMap<>();
    }

    public void addWallet(Wallet wallet) {
        if (wallet != null && !this.wallets.containsKey(wallet.getCryptoCurrency())) {
            this.wallets.put(wallet.getCryptoCurrency(), wallet);
        }
    }

    public Wallet getWallet(CryptoCurrency cryptoCurrency) {
        return this.wallets.get(cryptoCurrency);
    }

    public List<Wallet> getWalletsAsObservableList() {
        return wallets.values().stream().collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "WalletList{" +
                "wallets=" + wallets +
                '}';
    }
}
