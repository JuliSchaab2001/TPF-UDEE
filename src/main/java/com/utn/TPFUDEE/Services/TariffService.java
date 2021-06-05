package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class TariffService {

    private TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public Page<Tariff> getAll(Pageable pageable)  {
        Page<Tariff> tariffList = tariffRepository.findAll(pageable);

        if(tariffList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty Tariff List");
        }
        return tariffList;
    }

    public Tariff add(Tariff tariff){

        if((tariffRepository.findByType(tariff.getType()))!= null){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Tariff already Exist");
        } else{
            return tariffRepository.save(tariff);
        }
    }

    public Tariff getById(Integer id) {
        return tariffRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tariff Not Found"));
    }

    public void deleteById(Integer id){
        if(this.getById(id)!=null)
            tariffRepository.deleteById(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Delete Not Found For Delete");
    }

    public Tariff update(Tariff tariff){
        if(this.getById(tariff.getTariffId()) != null)
            return tariffRepository.save(tariff);
        return null;
    }
}
