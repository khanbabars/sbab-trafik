package com.sbab.dev.integration;



import com.sbab.dev.config.AppSettings;
import com.sbab.dev.domain.ApiPort;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.netty.http.client.HttpClient;

import java.time.Duration;


@Slf4j
@Component
public class ApiAdapter implements ApiPort {



    private final AppSettings appSettings;


    private WebClient webClient;

    public ApiAdapter(AppSettings appSettings) {
        this.appSettings = appSettings;

        log.info("Webclient buffer size {} MB", appSettings.api().webclientDataBufferLimit().toMegabytes());

        HttpClient httpClient = HttpClient.create()
                .responseTimeout(Duration.ofMillis(appSettings.api().timeOutMs()));

        final ExchangeStrategies strategies = ExchangeStrategies.builder().codecs(codecs -> codecs.defaultCodecs()
                .maxInMemorySize((int) appSettings.api().webclientDataBufferLimit().toBytes())).build();

        webClient = WebClient.builder()
                .exchangeStrategies(strategies)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }



    @Override
    public void method() {

        log.info("Running method");
    }




    @Override
    public JourneyPattern getJourneyPattern() {

        String urlJourneyPattern = appSettings.api().journeyPatterns();

        log.info(urlJourneyPattern);

        log.info("Fetching results for the journey pattern model ..");

        JourneyPattern journeyPattern = webClient.get()
                .uri(urlJourneyPattern)
                .header("accept-encoding", "gzip")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<JourneyPattern>() {
                })
                .block();


        return journeyPattern;

    }
    @Override
    public Line getLines() {

        String urlLines = appSettings.api().line();

        log.debug("Fetching results for the lines model ..");

        Line lines = webClient.get()
                .uri(urlLines)
                .header("accept-encoding", "gzip")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Line>() {
                })
                .block();
        return lines;

    }


    @Override
    public StopPoint getStopPoints() {

        String urlStopPoints = appSettings.api().stopPoints();

        log.debug("Fetching results for the stopPoints model ..");

        StopPoint stopPoints = webClient.get()
                .uri(urlStopPoints)
                .header("accept-encoding", "gzip")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<StopPoint>() {
                })
                .block();

        return stopPoints;

    }

    }





