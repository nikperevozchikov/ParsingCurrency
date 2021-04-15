package ru.quickresto.ParsingValutes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.quickresto.ParsingValutes.service.CurrencyService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    @Value("${limit}")
    private int limit;

    private CurrencyService currencyService;

    @Autowired
    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/topFiveCurrencyChanges")
    public ResponseEntity<List<String>> getTopFiveCurrencyChanges() throws IOException {
        return currencyService.getTopFiveChanges(limit);
    }
}
