package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class Auto {
    private Integer vehicleId;
    private String govNumber;
    private  String autoMark;
    private  String autoModel;
    private LocalDate regDate;
    private String color;
    private Boolean deleted;
}
