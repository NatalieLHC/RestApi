package com.example.demo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "autoIdGenerator", sequenceName = "autotransport_vehicle_id_seq",allocationSize = 1)
@Table(name = "autotransport")

public class Auto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autoIdGenerator")
    @Column(name = "vehicle_id")
    private Integer vehicleId;
    @Column(name = "gov_number")
    private String govNumber;
    @Column(name = "auto_mark")
    private  String autoMark;
    @Column(name = "auto_model")
    private  String autoModel;
    @Column(name = "reg_date")
    private LocalDate regDate;

    private String color;
    private Boolean deleted;
}
