package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.MeterType;
import com.utn.TPFUDEE.Repositories.MeterTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class MeterTypeService {

    private MeterTypeRepository meterTypeRepository;

    @Autowired
    public MeterTypeService(MeterTypeRepository meterTypeRepository) {
        this.meterTypeRepository = meterTypeRepository;
    }

    public Page<MeterType> getAll(Pageable pageable){
        Page<MeterType> meterTypeList = meterTypeRepository.findAll(pageable);

        if(meterTypeList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty MeterType List");
        }
        return meterTypeList;
    }

    public MeterType add(MeterType meterType){
        if(meterTypeRepository.findByModelAndBrand(meterType.getModel(), meterType.getBrand())!= null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "MeterType Already Exist");
        }else{
            return meterTypeRepository.save(meterType);
        }
    }

    public MeterType getById(Integer id){
        return meterTypeRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "MeterType Not Found"));
    }

    //Deberia llamar al repositorio, no a this.getById()
    public Integer deleteById(Integer id){
        this.getById(id);
        meterTypeRepository.deleteById(id);
        return id;
    }
}
