package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.MeterTypeExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.MeterTypeNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.MeterTypeNotFoundException;
import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Repositories.MeterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class MeterTypeService {

    private MeterTypeRepository meterTypeRepository;

    @Autowired
    public MeterTypeService(MeterTypeRepository meterTypeRepository) {
        this.meterTypeRepository = meterTypeRepository;
    }

    public Page<MeterType> getAll(Pageable pageable) throws MeterTypeNoContentException {
        Page<MeterType> meterTypeList = meterTypeRepository.findAll(pageable);

        if(meterTypeList.isEmpty()) {
            throw new MeterTypeNoContentException();
        }
        return meterTypeList;
    }

    public MeterType add(MeterType meterType) throws MeterTypeExistException {
        boolean flag = false;
        for(MeterType var : this.meterTypeRepository.findAll()){
            if(var.getBrand().equals(meterType.getBrand()) && var.getModel().equals(meterType)){
                flag = true;
            }
        }
        if(flag){
            throw new MeterTypeExistException();
        }else{
            return meterTypeRepository.save(meterType);
        }
    }

    public MeterType getById(Integer id) throws MeterTypeNotFoundException {
        return meterTypeRepository.findById(id).orElseThrow(()-> new MeterTypeNotFoundException());
    }

    public void deleteById(Integer id) throws MeterTypeNotFoundException {
        this.getById(id);
        meterTypeRepository.deleteById(id);
    }
}
