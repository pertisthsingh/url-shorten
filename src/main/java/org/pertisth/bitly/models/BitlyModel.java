package org.pertisth.bitly.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection= "urls")
public class BitlyModel {
    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String shortUrl;

    private String originalUrl;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime lastAccessed;

    @Builder.Default
    private long clickCount = 0;

    public void incrementClickCount() {
        this.clickCount++;
    }
}
