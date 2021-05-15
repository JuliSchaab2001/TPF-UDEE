package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.MeterTypeExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeterTypeNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterTypeNotFoundException;
import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Repositories.MeterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterTypeService {

    private MeterTypeRepository meterTypeRepository;

    @Autowired
    public MeterTypeService(MeterTypeRepository meterTypeRepository) {
        this.meterTypeRepository = meterTypeRepository;
    }

    public List<MeterType> getAll() throws MeterTypeNoContentException {
        List<MeterType> meterTypeList = meterTypeRepository.findAll();

        if(meterTypeList.isEmpty()) {
            throw new MeterTypeNoContentException();
        }
        return meterTypeList;
    }

    public void add(MeterType meterType) throws MeterTypeExistException {
        boolean flag = false;
        for(MeterType var : this.meterTypeRepository.findAll()){
            if(var.getBrand().equals(meterType.getBrand()) && var.getModel().equals(meterType)){
                flag = true;
            }
        }
        if(flag){
            throw new MeterTypeExistException();
        }else{
            meterTypeRepository.save(meterType);
        }
    }

    public MeterType getById(Integer id) throws MeterTypeNotFoundException {
        return meterTypeRepository.findById(id).orElseThrow(()-> new MeterTypeNotFoundException());
    }
}
