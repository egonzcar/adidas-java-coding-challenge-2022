package com.adidas.backend.emailservice.infrastructure.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailEvent {

    private String email;

}
