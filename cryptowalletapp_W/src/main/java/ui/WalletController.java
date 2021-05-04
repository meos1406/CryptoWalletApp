package ui;

import at.itkolleg.sample.WalletApp;
import domain.Transaction;
import domain.Wallet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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

        this.lblName.textProperty().setValue(this.wallet.getName());

        btnBackToMain.setOnAction((ActionEvent e) -> {
            WalletApp.switchScene("main.fxml", "at.itkolleg.sample.main");
        });
    }

    public void buy() {
        System.out.println("KAUFEN");
    }

    public void sell() {
        System.out.println("VERKAUFEN");
    }
}
