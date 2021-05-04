package ui;

import at.itkolleg.sample.WalletApp;
import domain.CurrentPriceForCurrency;
import domain.Transaction;
import domain.Wallet;
import exceptions.GetCurrentPriceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WalletController {

    @FXML
    private Button btnBackToMain;

    @FXML
    private Label lblId, lblName, lblCurrency, lblAmount, lblFee, lblValue;

    @FXML
    private TableView<Transaction> tblTransactions;

    private Wallet wallet;

    public void initialize() {
        this.wallet = (Wallet) GlobalContext.getGlobalContext().getStateFor(WalletApp.GLOBAL_SELECTED_WALLET);

        refreshAllGuiValues();

        btnBackToMain.setOnAction((ActionEvent e) -> {
            WalletApp.switchScene("main.fxml", "at.itkolleg.sample.main");
        });
    }

    private CurrentPriceForCurrency getCurrentPriceStrategy() {
        return (CurrentPriceForCurrency) GlobalContext.getGlobalContext().getStateFor(WalletApp.GLOBAL_CURRENT_CURRENCY_PRICES);
    }

    public void refreshAllGuiValues() {
        this.lblId.textProperty().setValue(this.wallet.getId().toString());
        this.lblName.textProperty().setValue(this.wallet.getName());
        this.lblCurrency.textProperty().setValue(this.wallet.getCryptoCurrency().getCode());
        this.lblAmount.textProperty().setValue(this.wallet.getAmount().toString());
        this.lblFee.textProperty().setValue(this.wallet.getFeeInPercent().toString());

        try {
            BigDecimal currentPrice = this.getCurrentPriceStrategy().getCurrentPrice(wallet.getCryptoCurrency());
            BigDecimal currentValue = currentPrice.multiply((wallet.getAmount().setScale(6, RoundingMode.HALF_UP)));
            this.lblValue.textProperty().set(currentValue.toString());
        } catch (GetCurrentPriceException e) {
            WalletApp.showErrorDialog(e.getMessage());
            this.lblValue.textProperty().set("CURRENT PRICES UNAVAILABLE!");
            e.printStackTrace();
        }
    }

    public void buy() {
        System.out.println("KAUFEN");
    }

    public void sell() {
        System.out.println("VERKAUFEN");
    }
}
