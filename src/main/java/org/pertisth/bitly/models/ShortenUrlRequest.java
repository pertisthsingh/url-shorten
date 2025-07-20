package org.pertisth.bitly.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ShortenUrlRequest {
    private String url;
}
