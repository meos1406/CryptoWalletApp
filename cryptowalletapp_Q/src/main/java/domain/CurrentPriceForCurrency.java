package domain;

import exceptions.GetCurrentPriceException;

import java.math.BigDecimal;

public interface CurrentPriceForCurrency {
    BigDecimal getCurrentPrice(CryptoCurrency cryptoCurrency) throws GetCurrentPriceException;
}
