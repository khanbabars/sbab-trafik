package com.sbab.dev.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;



@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StopPoint {

    @JsonProperty("StatusCode")
    private int statusCode;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("ExecutionTime")
    private int executionTime;

    @JsonProperty("ResponseData")
    private ResponseData responseData;


    @Data
    public static class ResponseData {

        @JsonProperty("Version")
        private String version;

        @JsonProperty("Type")
        private String type;

        @JsonProperty("Result")
        private List<Result> result;
    }

    @Data
    public static class Result {

        @JsonProperty("StopPointNumber")
        private String stopPointNumber;

        @JsonProperty("StopPointName")
        private String stopPointName;

        @JsonProperty("StopAreaNumber")
        private String stopAreaNumber;

        @JsonProperty("LocationNorthingCoordinate")
        private String locationNorthingCoordinate;

        @JsonProperty("LocationEastingCoordinate")
        private String locationEastingCoordinate;

        @JsonProperty("ZoneShortName")
        private String zoneShortName;

        @JsonProperty("StopAreaTypeCode")
        private String stopAreaTypeCode;

        @JsonProperty("LastModifiedUtcDateTime")
        private String lastModifiedUtcDateTime;

        @JsonProperty("ExistsFromDate")
        private String existsFromDate;


    }

}



/*
     {
        "StopPointNumber": "10001",
        "StopPointName": "Stadshagsplan",
        "StopAreaNumber": "10001",
        "LocationNorthingCoordinate": "59.3373571967995",
        "LocationEastingCoordinate": "18.0214674159693",
        "ZoneShortName": "A",
        "StopAreaTypeCode": "BUSTERM",
        "LastModifiedUtcDateTime": "2022-10-28 00:00:00.000",
        "ExistsFromDate": "2022-10-28 00:00:00.000"
      },
 */