package com.philippo.algafood.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
@Schema(name = "Problem")
public class Problem {

    @Schema(example = "400")
    private Integer status;

    @Schema(example = "2025-03-10T21:11:11.9022454982")
    private OffsetDateTime timestamp;

    @Schema(example = "https://algafood.com.br/invalid-data")
    private String type;

    @Schema(example = "Invalid data")
    private String title;

    @Schema(example = "One or more fields are invalid")
    private String detail;

    @Schema(example = "One or more fields are invalid")
    private String userMessage;

    @Schema(example = "List of objects or fields that generated the error")
    private List<Object> objects;

    @Getter
    @Builder
    @Schema(name = "ProblemObject")
    public static class Object{

        @Schema(name = "Price")
        private String name;

        @Schema(name = "The price is invalid")
        private String userMessage;
    }
}
