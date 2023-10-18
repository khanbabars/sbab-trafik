package com.sbab.dev.integration;


import com.sbab.dev.config.AppSettings;
import com.sbab.dev.domain.ApiPort;
import com.sbab.dev.domain.dto.JourneyPattern;
import com.sbab.dev.domain.dto.Line;
import com.sbab.dev.domain.dto.StopPoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class ApiAdapter implements ApiPort {


    private final AppSettings appSettings;

    @Autowired
    private WebClient webClient;

    @Autowired
    private RestTemplate appRestClient;


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
        log.info(journeyPattern.getResponseData().getResult().get(0).getJourneyPatternPointNumber());

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




    @Override
    public ResponseEntity<List<Line.Result>> getLineHttp() {
        log.info("Contacting trafiklab api for 'line' models..");

        ResponseEntity<List<Line.Result>> lineResponse =
                appRestClient.exchange("https://api.sl.se/api2/LineData.json?key=c975f920eca443a9b66d8ad027e006c1&model=line&DefaultTransportModeCode=BUS",
                        HttpMethod.GET,
                        requestEntityWithCompressionHeaders(),
                        new ParameterizedTypeReference<>() {
                                });

        return lineResponse;
    }


    private HttpEntity<?> requestEntityWithCompressionHeaders() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add(HttpHeaders.ACCEPT_ENCODING, "gzip");
        return new HttpEntity<>(requestHeaders);
    }


}


