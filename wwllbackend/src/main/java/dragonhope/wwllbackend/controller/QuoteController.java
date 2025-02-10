package dragonhope.wwllbackend.controller;

import dragonhope.wwllbackend.common.Result;
import dragonhope.wwllbackend.dto.QuoteRequestDTO;
import dragonhope.wwllbackend.dto.QuoteResponseDTO;
import dragonhope.wwllbackend.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @PostMapping
    public Result<QuoteResponseDTO> calculateQuote(@RequestBody QuoteRequestDTO request) {
        return Result.success(quoteService.calculateQuote(request));
    }
    @PostMapping("/calculate-without-saving")
    public Result<QuoteResponseDTO> calculateQuoteWithoutSaving(@RequestBody QuoteRequestDTO request) {
        return Result.success(quoteService.calculateQuoteWithoutSaving(request));
    }
} 