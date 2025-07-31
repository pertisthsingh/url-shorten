package org.pertisth.bitly.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UrlCheckResponse {
    private boolean exists;
    private String shortUrl;
}
