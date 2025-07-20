package org.pertisth.bitly.services.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.pertisth.bitly.models.BitlyModel;
import org.pertisth.bitly.repository.BitlyRepo;
import org.pertisth.bitly.services.BitlyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BitlyServiceImpl implements BitlyService {

    private final BitlyRepo repository;

    public BitlyServiceImpl(BitlyRepo repository) {
        this.repository = repository;
    }

    @Override
    public String shortenUrl(String originalUrl) {

        Optional<BitlyModel> urlMapping = repository.findByOriginalUrl(originalUrl);
        if (urlMapping.isPresent()) {
            return urlMapping.get().getShortUrl();
        } else {
            String shortId = generateShortId();
            BitlyModel newBitly = new BitlyModel();
            newBitly.setOriginalUrl(originalUrl);
            newBitly.setShortUrl(shortId);
            repository.save(newBitly);
            return shortId;
        }
    }

    @Override
    public String getOriginalUrl(String shortUrl) {
        Optional<BitlyModel> urlMapping = repository.findByShortUrl(shortUrl);
        if (urlMapping.isPresent()) {
            BitlyModel bitlyModel = urlMapping.get();
            bitlyModel.incrementClickCount();
            bitlyModel.setLastAccessed(LocalDateTime.now());
            repository.save(bitlyModel);
            return bitlyModel.getOriginalUrl();
        } else {
            throw new IllegalArgumentException("Short URL not found");
        }
    }

    @Override
    public String generateShortId() {
        return RandomStringUtils.randomAlphanumeric(6).toLowerCase();
    }
}
