package org.pertisth.bitly.repository;

import org.pertisth.bitly.models.BitlyModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BitlyRepo extends MongoRepository<BitlyModel, String> {
    Optional<BitlyModel> findByOriginalUrl(String originalUrl);
    Optional<BitlyModel> findByShortUrl(String shortUrl);
}
