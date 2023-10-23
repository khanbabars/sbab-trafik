package com.sbab.dev.domain.dto;



import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class JourneyPattern {

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

        @JsonProperty("LineNumber")
        private String lineNumber;

        @JsonProperty("DirectionCode")
        private String directionCode;

        @JsonProperty("JourneyPatternPointNumber")
        private String journeyPatternPointNumber;

        @JsonProperty("LastModifiedUtcDateTime")
        private String lastModifiedUtcDateTime;

        @JsonProperty("ExistsFromDate")
        private String existsFromDate;


    }

}



/*

   {
        "LineNumber": "1",
        "DirectionCode": "1",
        "JourneyPatternPointNumber": "10008",
        "LastModifiedUtcDateTime": "2022-02-15 00:00:00.000",
        "ExistsFromDate": "2022-02-15 00:00:00.000"

 */