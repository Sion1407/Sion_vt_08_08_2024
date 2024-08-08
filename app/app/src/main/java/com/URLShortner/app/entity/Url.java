package com.URLShortner.app.entity;

import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "urls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url", unique = true, nullable = false)
    private String shortUrl;

    @Column(name = "destination_url", nullable = false)
    private String destinationUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at", nullable = false)
    private LocalDateTime expiresAt;

    @Column(name = "access_count", nullable = false)
    private int accessCount;
    public Url(String shortUrl, String destinationUrl, LocalDateTime createdAt, LocalDateTime expiresAt) {
        this.shortUrl = shortUrl;
        this.destinationUrl = destinationUrl;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.accessCount = 0;
    }

}
