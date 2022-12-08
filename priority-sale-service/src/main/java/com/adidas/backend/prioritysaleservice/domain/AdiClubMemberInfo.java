package com.adidas.backend.prioritysaleservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

@Getter
@AllArgsConstructor
@ToString
public class AdiClubMemberInfo {

    private String email;
    private Integer points;
    private Instant registrationDate;

}
