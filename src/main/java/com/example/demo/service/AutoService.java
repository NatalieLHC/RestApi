package com.example.demo.service;

import com.example.demo.entity.Auto;
import com.example.demo.entity.AutoSearchParams;

import java.util.List;

public interface AutoService {
    public List <Auto> getAll(AutoSearchParams searchParams);

    public Auto getById(int id);

    public Auto add(Auto auto);

    public Auto update(int id, Auto auto);

    public  void delete(int id);
}



