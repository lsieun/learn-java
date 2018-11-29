package lsieun.rate.impl;

import java.util.List;

import lsieun.rate.api.Quote;

public class QuoteResponse {
    private List<Quote> result;
    private String error;

    public List<Quote> getResult() {
        return result;
    }

    public void setResult(List<Quote> result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
