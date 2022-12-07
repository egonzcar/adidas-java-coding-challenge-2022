package com.adidas.backend.prioritysaleservice.infrastructure.queue;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailEvent {

    private final String email;

}
