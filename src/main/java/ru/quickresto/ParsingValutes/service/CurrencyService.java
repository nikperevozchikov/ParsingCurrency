package ru.quickresto.ParsingValutes.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.quickresto.ParsingValutes.model.Currency;
import ru.quickresto.ParsingValutes.model.CurrencyMap;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Value("${url}")
    private String url;

    public CurrencyMap jsonParsing() throws IOException {
        URL newURL = new URL(url);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(newURL, CurrencyMap.class);
    }

    public ResponseEntity<List<String>> getTopFiveChanges(int limit) throws IOException {
        var jsonData = jsonParsing();
        TreeSet<String> set = new TreeSet<>();
        for (Map.Entry<String, Currency> currencyEntry : jsonData.getCurrencyMap().entrySet()) {
            var difference = currencyEntry.getValue().value - currencyEntry.getValue().previous;
            set.add(difference + "------------>" + currencyEntry.getKey());

        }
        NavigableSet<String> newSet = set.descendingSet();
        return ResponseEntity.ok().body(newSet.stream().limit(limit).collect(Collectors.toList()));
    }
}

