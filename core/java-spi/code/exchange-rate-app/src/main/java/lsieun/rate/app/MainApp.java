package lsieun.rate.app;


import java.time.LocalDate;
import java.util.List;

import lsieun.rate.api.Quote;
import lsieun.rate.spi.ExchangeRateProvider;
import lsieun.rate.util.ExchangeRate;

public class MainApp {
    public static void main(String[] args) {
        List<ExchangeRateProvider> providers = ExchangeRate.providers();
        System.out.println(providers.size());

        ExchangeRateProvider provider = ExchangeRate.provider();
        System.out.println("Retreiving USD quotes from provider :" + provider);

        List<Quote> quotes = provider.create().getQuotes("USD", LocalDate.now());
        System.out.println(String.format("%14s%12s|%12s", "","Ask", "Bid"));
        System.out.println("----------------------------------------");
        for (Quote quote : quotes) {
            System.out.println("USD --> " + quote.getCurrency() + " : " + String.format("%12f|%12f", quote.getAsk(), quote.getBid()));
        }

    }
}
