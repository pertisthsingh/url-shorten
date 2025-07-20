package org.pertisth.bitly.controllers;


import lombok.RequiredArgsConstructor;
import org.pertisth.bitly.models.ShortenUrlRequest;
import org.pertisth.bitly.models.ShortenUrlResponse;
import org.pertisth.bitly.services.BitlyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
public class BitlyController {

    private final BitlyService bitlyService;

    BitlyController(BitlyService bitlyService) {
        this.bitlyService = bitlyService;
    }

    @PostMapping("/shorten")
    public ResponseEntity<?> shorten(@RequestBody ShortenUrlRequest reqBody) {
        String originalUrl = reqBody.getUrl();

        if (originalUrl == null || originalUrl.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Original URL is required"));
        }
        String normalizedUrl = originalUrl.matches("^https?://.*") ? originalUrl : "http://" + originalUrl;
        String shortUrl = bitlyService.shortenUrl(normalizedUrl);
        ShortenUrlResponse response = new ShortenUrlResponse(shortUrl);
        return ResponseEntity.ok(response);
    }

    @GetMapping("{shorturl}")
    public RedirectView getShortUrl(@PathVariable("shorturl") String shorturl) {
        String originalUrl = bitlyService.getOriginalUrl(shorturl);

        if (originalUrl.isEmpty()) {
            return new RedirectView("/404");
        }
        return new RedirectView(originalUrl);
    }
}
