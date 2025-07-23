package org.gkh.config;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@Setter
@ConfigurationProperties(prefix = "app")
@EnableCaching
@Log4j2
public class AppConfig {

    private final Long timeToLiveMilliseconds;

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("melbourneWeather");
    }

    @CacheEvict(value = "melbourneWeather", allEntries = true)
    @Scheduled(fixedRateString = "${app.time-to-live-milliseconds}")
    public void clearCache() {
        log.info("clear melbourneWeather cache");
    }
}
