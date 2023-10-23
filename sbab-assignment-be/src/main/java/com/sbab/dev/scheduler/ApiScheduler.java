package com.sbab.dev.scheduler;


import com.sbab.dev.integration.ApiUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiScheduler {


    @Autowired
    private ApiUtils apiUtils;


    @Scheduled(cron = "0 3 * * * *")
    public void cronJobSch() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        log.info("Refreshing api cache  " + strDate);
        apiUtils.getJourneyPattern();
        apiUtils.getLines();
        apiUtils.getStopPoints();
    }
}



