package org.pertisth.bitly.services;


import org.pertisth.bitly.dto.UrlCheckResponse;


public interface BitlyService {


    String shortenUrl(String originalUrl);

    String getOriginalUrl(String shortUrl);

    String generateShortId();

    UrlCheckResponse checkIfUrlAlreadyShortened(String originalUrl);

}
