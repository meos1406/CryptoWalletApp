package infrastruktur;

import domain.CryptoCurrency;
import domain.CurrentPriceForCurrency;
import exceptions.GetCurrentPriceException;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrentCurrencyPrices implements CurrentPriceForCurrency {

    @Override
    public BigDecimal getCurrentPrice(CryptoCurrency cryptoCurrency) throws GetCurrentPriceException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(
                URI.create("https://api.coingecko.com/api/v3/simple/price?ids="
                        + cryptoCurrency.currencyName
                        + "&vs_currencies=eur"))
                .header("accept", "application/json")
                .build();
        try {
            HttpResponse<String> result = client.send(request, HttpResponse.BodyHandlers.ofString());
            String[] split = result.body().split(":");
            String result2 = split[2].substring(0, split[2].length() - 2);
            return new BigDecimal(result2);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw new GetCurrentPriceException("IOException: " + ioException.getMessage());
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
            throw new GetCurrentPriceException("Call interrupted: " + interruptedException.getMessage());
        } catch (NumberFormatException numberFormatException) {
            throw new GetCurrentPriceException("Conversion of Value not possible: " + numberFormatException.getMessage());
        }
    }
}
