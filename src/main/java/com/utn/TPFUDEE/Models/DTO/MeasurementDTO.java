package com.utn.TPFUDEE.Models.DTO;

import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
public class MeasurementDTO {
    private String password;
    private String serialNumber;
    private String date;
    private Integer kw;
}
