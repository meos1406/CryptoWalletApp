package at.itkolleg.sample;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

import domain.BankAccount;
import domain.CryptoCurrency;
import domain.Transaction;
import exceptions.InsufficientBalanceException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane root = FXMLLoader.load(Main.class.getResource("main.fxml"),
                ResourceBundle.getBundle("at.itkolleg.sample.main"));

        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println("Hallo Welt");
        BankAccount ba = new BankAccount();
        ba.deposit(new BigDecimal("100"));
        System.out.println(ba);

        try {
            ba.withdraw(new BigDecimal("50"));
            ba.withdraw(new BigDecimal("51"));
            System.out.println(ba);
        } catch (InsufficientBalanceException insufficientBalanceException) {
            //System.out.println("Zu wenig Geld am Konto!");
            System.out.println(insufficientBalanceException.getMessage());
        }

        CryptoCurrency crypto = CryptoCurrency.BTC;
        System.out.println(crypto.getCurrencyName());
        System.out.println(crypto.getCode());
        System.out.println(CryptoCurrency.valueOfCode("BTC").getCurrencyName());

        Transaction transaction = new Transaction(
                CryptoCurrency.ETH,
                new BigDecimal("1.23"),
                new BigDecimal("1567.8")
        );

        System.out.println(transaction);

        launch(args);
    }
}
