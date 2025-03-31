package digitalwalletservice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private static final Map<Currency, BigDecimal> exchangeRates = new HashMap<>();

   static {
       // Initialize exchange rates
        exchangeRates.put(Currency.USD,BigDecimal.ONE);
        exchangeRates.put(Currency.EUR,BigDecimal.valueOf(0.92));
        exchangeRates.put(Currency.INR,BigDecimal.valueOf(85.56));
        exchangeRates.put(Currency.YEN,BigDecimal.valueOf(150.06));
   }
   public static BigDecimal convert(BigDecimal amount, Currency sourceCurrency, Currency targetCurrency){
       BigDecimal sourceRate = exchangeRates.get(sourceCurrency);
       BigDecimal targetRate = exchangeRates.get(targetCurrency);
       return amount.multiply(sourceRate).divide(targetRate, RoundingMode.HALF_UP);
   }
}
