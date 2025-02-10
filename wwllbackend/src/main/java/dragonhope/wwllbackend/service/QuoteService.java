package dragonhope.wwllbackend.service;

import dragonhope.wwllbackend.dto.QuoteRequestDTO;
import dragonhope.wwllbackend.dto.QuoteResponseDTO;

public interface QuoteService {
    QuoteResponseDTO calculateQuote(QuoteRequestDTO request); // 计算并保存订单
    QuoteResponseDTO calculateQuoteWithoutSaving(QuoteRequestDTO request); // 仅计算，不保存订单
}