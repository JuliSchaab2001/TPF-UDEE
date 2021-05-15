package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.TariffExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.TariffNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.TariffNotFoundException;
import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {

    private TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public List<Tariff> getAll() throws TariffNoContentException {
        List<Tariff> tariffList = tariffRepository.findAll();

        if(tariffList.isEmpty()) {
            throw new TariffNoContentException();
        }
        return tariffList;
    }

    public void add(Tariff tariff) throws TariffExistException {
        boolean flag = false;
        for(Tariff var : this.tariffRepository.findAll()){
            if(var.getType().equals(tariff.getType())){
                flag = true;
            }
        }
        if(flag){
            throw new TariffExistException();
        }else{
            tariffRepository.save(tariff);
        }
    }

    public Tariff getById(Integer id) throws TariffNotFoundException {
        return tariffRepository.findById(id).orElseThrow(()-> new TariffNotFoundException());
    }
}
