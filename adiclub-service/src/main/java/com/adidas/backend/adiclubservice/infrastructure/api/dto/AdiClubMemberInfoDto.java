package com.adidas.backend.adiclubservice.infrastructure.api.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class AdiClubMemberInfoDto {

    private String email;
    private Integer points;
    private Instant registrationDate;

}
