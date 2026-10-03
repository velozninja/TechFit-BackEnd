package com.TechFit.TechFit.service;

import io.github.bucket4j.Bucket;
import org.apache.catalina.util.RateLimiter;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public Bucket getBucket(String key, int capacity, int refillTokens, int duration) {
        return buckets.computeIfAbsent(key, k -> createBucket(capacity, refillTokens, duration) );

    }
    private Bucket createBucket(int capacity, int refillTokens, int duration){
        return Bucket.builder().addLimit(limit -> limit
                .capacity(capacity)
                .refillGreedy(refillTokens, Duration.ofMinutes(duration))

        ).build();
    }
}
