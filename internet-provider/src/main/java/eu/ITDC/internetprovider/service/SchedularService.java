package eu.ITDC.internetprovider.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class SchedularService {

    private static final Logger LOG = LoggerFactory.getLogger(SchedularService.class);

    @Scheduled(fixedDelay = 10000)
    public void timer(){
        LOG.info("Timer {}", Instant.now());
    }

}
