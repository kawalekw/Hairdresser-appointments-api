package com.javaretards.hairdresserapponintments.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AppointmentData {
    private String date;
    private Long serviceId;
    private Integer startsAt;
    private String name;
    private String phone;
}
