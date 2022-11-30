package com.example.demo.repository;

import com.example.demo.entity.Auto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoReposit extends JpaRepository<Auto, Integer> {

    @Query("select a from Auto a where a.deleted=false ")
    List<Auto> findActive();

    List<Auto>findByAutoMark(String mark);


}
