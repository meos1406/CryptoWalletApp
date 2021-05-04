package domain;

import java.io.Serializable;
import java.util.*;

/**
 * Crypto Currences
 *
 * Usage e.g.:
 *      CryptoCurrency.USD.getCurrencyName()
 *      CryptoCurrency.valueOfCode("USD").getCurrencyName()
 *      CryptoCurrency.USD.getCode()
 *
 * Important note:
 *      CryptoCurrency.valueOf("$PAC") returns IllegalArgumentException.
 */
public enum CryptoCurrency implements Serializable {

    ANON("ANON"),
    AQUA("Aquachain"),
    BNB("BinanceCoin"),
    BSD("BitSend"),
    BTC("Bitcoin"),
    BTDX("Bitcloud"),
    BTX("Bitcore"),
    BURST("Burst"),
    CLOAK("CloakCoin"),
    DAI("Dai"),
    BIZZ("BIZZCOIN"),
    DASH("Dash"),
    DEX("DEX"),
    DGB("DigiByte"),
    DOGE("DOGE"),
    ECA("Electra"),
    ETH("Ethereum"),
    FLASH("FLASH"),
    FTO("FuturoCoin"),
    GRS("Groestlcoin"),
    HATCH("Hatch"),
    HBX("HBX"),
    ILC("ILCoin"),
    KMD("Komodo"),
    JOB("Jobchain"),
    LMY("Lunch Money"),
    LEO("LEOcoin"),
    LINDA("Linda"),
    LSK("Lisk"),
    LTC("Litecoin"),
    MAX("Maxcoin"),
    MEC("Megacoin"),
    MKR("Maker"),
    MUSD("MovexUSD"),
    NBT("NuBits"),
    NLG("NLG"),
    NXT("Nxt"),
    POT("PotCoin"),
    REP("Augur"),
    SMART("SmartCash"),
    SPICE("Spice"),
    START("Startcoin"),
    SUM("Sumcoin"),
    SYS("Syscoin"),
    TKN("TokenCard"),
    TRTL("TurtleCoin"),
    TRX("Tron"),
    USDS("StableUSD"),
    USDT("Tether"),
    VIA("Viacoin"),
    VOLTZ("VOLTZ"),
    WDC("WorldCoin"),
    XMR("Monero"),
    XORI("CHFToken"),
    TENT("Tent"),
    XRP("Ripple"),
    XZC("Zcoin"),
    BAY("BitBayCoin"),
    NULS("NULS"),
    MUE("MonetaryUnit"),
    XPM("Primecoin"),
    ZPAE("ZelaaPayAE")
    ;

    private static Map<String, CryptoCurrency> cryptoCurrenciesUpperCase = new HashMap<>();
    private static Map<String, CryptoCurrency> cryptoCurrencies = new LinkedHashMap<>();

    // static initialisation block - runs once when class ist first loaded
    static {
        for (CryptoCurrency cc : CryptoCurrency.values()) {
            cryptoCurrenciesUpperCase.put(cc.code.toUpperCase(), cc);
            cryptoCurrencies.put(cc.code, cc);
        }
        cryptoCurrencies = Collections.unmodifiableMap(cryptoCurrencies);
    }

    public final String code;
    public final String currencyName;

    CryptoCurrency(String currencyName) {
        this(null, currencyName);
    }

    CryptoCurrency(String code, String currencyName) {
        this.currencyName = currencyName;
        this.code = (code == null) ? name() : code;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCode() {
        return code;
    }

    /**
     * Use this method instead of Enum.valueOf.
     */
    public static CryptoCurrency valueOfCode(String code) {
        CryptoCurrency cc = cryptoCurrenciesUpperCase.get(code.toUpperCase());
        if (cc == null) {
            throw new IllegalArgumentException(code + " not found");
        }
        return cc;
    }

    public static Set<String> getCodes() {
        return cryptoCurrencies.keySet();
    }
}
