package org.pertisth.bitly.services;


public interface BitlyService {


    String shortenUrl(String originalUrl);

    String getOriginalUrl(String shortUrl);

    String generateShortId();

}
