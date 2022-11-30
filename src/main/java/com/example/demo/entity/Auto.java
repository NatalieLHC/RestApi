package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "autos")

public class Auto {
    @Id
    private Integer vehicleId;

    private String govNumber;

    private  String autoMark;

    private  String autoModel;

    private LocalDate regDate;

    private String color;
    private Boolean deleted;
}
