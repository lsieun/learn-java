package lsieun.rate.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

import lsieun.rate.exception.ProviderNotFoundException;
import lsieun.rate.spi.ExchangeRateProvider;

public final class ExchangeRate {

    private static final String DEFAULT_PROVIDER = "lsieun.rate.impl.YahooFinanceExchangeRateProvider";

    // All providers
    public static List<ExchangeRateProvider> providers() {
        List<ExchangeRateProvider> services = new ArrayList<ExchangeRateProvider>();

        ServiceLoader<ExchangeRateProvider> loader = ServiceLoader.load(ExchangeRateProvider.class);
        Iterator<ExchangeRateProvider> it = loader.iterator();
        while(it.hasNext()) {
            ExchangeRateProvider provider = it.next();
            services.add(provider);
        }

        return services;
    }

    // Default provider
    public static ExchangeRateProvider provider() {
        return provider(DEFAULT_PROVIDER);
    }

    // provider by name
    public static ExchangeRateProvider provider(String providerName) {
        ServiceLoader<ExchangeRateProvider> loader = ServiceLoader.load(ExchangeRateProvider.class);
        Iterator<ExchangeRateProvider> it = loader.iterator();
        while(it.hasNext()) {
            ExchangeRateProvider provider = it.next();
            if (providerName.equals(provider.getClass().getName())) {
                return provider;
            }
        }

        throw new ProviderNotFoundException("Exchange Rate provider " + providerName + " not found");
    }

}
