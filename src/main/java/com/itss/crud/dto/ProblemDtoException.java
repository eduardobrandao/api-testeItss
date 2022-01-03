package com.itss.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDtoException  {

    private Integer status;
    private String type;
    private String title;
    private String detail;


}
