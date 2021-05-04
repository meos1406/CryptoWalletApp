package ui;

import at.itkolleg.sample.WalletApp;
import domain.CryptoCurrency;
import domain.Wallet;
import exceptions.InsufficientBalanceException;
import exceptions.InvalidFeeException;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.util.Optional;

public class MainController extends BaseControllerState {

    @FXML
    private Button btnClose;

    @FXML
    private ComboBox cmbWalletCurrency;

    @FXML
    private Label lblBankaccountBalance;

    @FXML
    private TableView<Wallet> tableView;

    public void initialize() {
        this.cmbWalletCurrency.getItems().addAll(CryptoCurrency.getCodes());
        this.lblBankaccountBalance.textProperty().setValue(getBankAccount().getBalance().toString());

        TableColumn<Wallet, String> symbol = new TableColumn<>("SYMBOL");
        symbol.setCellValueFactory(new PropertyValueFactory<>("cryptoCurrency"));

        TableColumn<Wallet, String> currencyName = new TableColumn<>("CURRENCY NAME");
        symbol.setCellValueFactory(new PropertyValueFactory<>("currencyName"));

        TableColumn<Wallet, String> name = new TableColumn<>("WALLET NAME");
        symbol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Wallet, String> amount = new TableColumn<>("AMOUNT");
        symbol.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tableView.getColumns().clear();
        tableView.getColumns().add(name);
        tableView.getColumns().add(symbol);
        tableView.getColumns().add(currencyName);
        tableView.getColumns().add(amount);

        tableView.getItems().setAll(getWalletList().getWalletsAsObservableList());
    }

    public void deposit() {
        TextInputDialog dialog = new TextInputDialog("Insert amount to deposit ...");
        dialog.setTitle("Deposit to bankaccount");
        dialog.setHeaderText("How much money do you want to deposit?");
        dialog.setContentText("Amount: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                BigDecimal amount = new BigDecimal(result.get());
                this.getBankAccount().deposit(amount);
                this.lblBankaccountBalance.textProperty().set(this.getBankAccount().getBalance().toString());
            } catch (NumberFormatException numberFormatException) {
                WalletApp.showErrorDialog("Please insert a number!");
            }
        }
    }

    public void withdraw() {
        TextInputDialog dialog = new TextInputDialog("Insert amount to withdraw ...");
        dialog.setTitle("Withdraw to bankaccount");
        dialog.setHeaderText("How much money do you want to withdraw?");
        dialog.setContentText("Amount: ");
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                BigDecimal amount = new BigDecimal(result.get());
                this.getBankAccount().withdraw(amount);
                this.lblBankaccountBalance.textProperty().set(this.getBankAccount().getBalance().toString());
            } catch (NumberFormatException numberFormatException) {
                WalletApp.showErrorDialog("Please insert a number!");
            } catch (InsufficientBalanceException insufficientBalanceException) {
                WalletApp.showErrorDialog(insufficientBalanceException.getMessage());
            }
        }
    }

    public void openWallet() {
        System.out.println("OPEN WALLET");
    }

    public void newWallet() throws InvalidFeeException {
        Object selectedItem = this.cmbWalletCurrency.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            WalletApp.showErrorDialog("Choose currency!");
            return;
        }
        CryptoCurrency selectedCryptoCurrency = CryptoCurrency.valueOf(this.cmbWalletCurrency.getSelectionModel().getSelectedItem().toString());
        this.getWalletList().addWallet((new Wallet("My" + selectedCryptoCurrency + "Wallet", selectedCryptoCurrency, new BigDecimal("1"))));
        tableView.getItems().setAll(this.getWalletList().getWalletsAsObservableList());
    }
}
