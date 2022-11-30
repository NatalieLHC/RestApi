package com.example.demo.service;

import com.example.demo.entity.Auto;
import com.example.demo.entity.AutoSearchParams;
import com.example.demo.exceptions.InvalidParameterException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.AutoReposit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoServiceImp implements AutoService {
    private static int id = 0;
    private List<Auto> db = new ArrayList<>();

    private final AutoReposit autoReposit;

    public AutoServiceImp(AutoReposit autoReposit) {
        this.autoReposit = autoReposit;
    }

    public List <Auto> getAll(AutoSearchParams autoSearchParams){

        String govNumber = autoSearchParams.getGovNumber();
        String autoMark = autoSearchParams.getAutoMark();
        String autoModel = autoSearchParams.getAutoModel();
        LocalDate regDate = autoSearchParams.getRegDate();
        String color = autoSearchParams.getColor();
        var stream =db.stream().filter(auto -> !auto.getDeleted());
        if (govNumber!=null && !govNumber.isEmpty()) {
           stream= stream.filter(auto -> auto.getGovNumber().equals(govNumber));
        }
        if (autoMark !=null && !autoMark.isEmpty()){
            stream= stream.filter(auto -> auto.getAutoMark().equals(autoMark));
        }
        if (autoModel !=null && !autoModel.isEmpty()){
            stream= stream.filter(auto -> auto.getAutoModel().equals(autoModel));
        }
        if (color !=null && !color.isEmpty()){
            stream= stream.filter(auto -> auto.getColor().equals(color));
        }
        if (regDate !=null){
            stream= stream.filter(auto -> auto.getRegDate().isEqual(regDate));
        }
        return stream.toList();
    }



    public Auto getById(int id){
        if (id <1){
            throw new InvalidParameterException("Id must be positive");
        }
        var optional = db.stream().filter(auto1 -> auto1.getVehicleId() == id).findFirst();
        if (optional.isEmpty()) {
            throw new NotFoundException("Customer not found");
        }
        return optional.get();
    }

    public Auto add(Auto auto){
        auto.setVehicleId(++id);
        auto.setDeleted(false);
        db.add(auto);
        return auto;
    }

    public Auto update(int id, Auto auto){
        var foundAuto = getById(id);
        foundAuto.setGovNumber(auto.getGovNumber());
        foundAuto.setAutoMark(auto.getAutoMark());
        foundAuto.setAutoModel(auto.getAutoModel());
        foundAuto.setColor(auto.getColor());
        foundAuto.setRegDate(auto.getRegDate());
        return auto;
    }

    public  void delete(int id){
        var foundAuto = getById(id);
        foundAuto.setDeleted(true);
    }


}
