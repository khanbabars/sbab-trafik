package com.sbab.dev.domain.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Line {



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
        @JsonProperty("LineDesignation")
        private String lineDesignation;
        @JsonProperty("DefaultTransportMode")
        private String defaultTransportMode;
        @JsonProperty("DefaultTransportModeCode")
        private String defaultTransportModeCode;
        @JsonProperty("LastModifiedUtcDateTime")
        private String lastModifiedUtcDateTime;
        @JsonProperty("ExistsFromDate")
        private String existsFromDate;


    }


}


