package ru.quickresto.ParsingValutes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyMap {
    @JsonProperty("Valute")
    private Map<String, Currency> currencyMap;

    public Map<String, Currency> getCurrencyMap() {
        return currencyMap;
    }

}
