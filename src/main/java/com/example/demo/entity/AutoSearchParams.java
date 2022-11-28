package com.example.demo.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AutoSearchParams {
    private String govNumber;
    private  String autoMark;
    private  String autoModel;
    private String color;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate regDate;

}
