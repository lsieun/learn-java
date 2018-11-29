package lsieun.rate.spi;

import lsieun.rate.api.QuoteManager;

public interface ExchangeRateProvider {
    QuoteManager create();
}
