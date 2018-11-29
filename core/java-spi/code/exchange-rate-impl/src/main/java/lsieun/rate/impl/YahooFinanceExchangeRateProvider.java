package lsieun.rate.impl;


import lsieun.rate.api.QuoteManager;
import lsieun.rate.spi.ExchangeRateProvider;

public class YahooFinanceExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public QuoteManager create() {
        return new YahooQuoteManagerImpl();
    }
}
