package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Tariff;
import com.utn.TPFUDEE.Repositories.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
public class TariffService {

    private TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public List<Tariff> getAll(){
        return tariffRepository.findAll();
    }

    public void add(Tariff tariff){
        tariffRepository.save(tariff);
    }

    public Tariff getById(Integer id){
        return tariffRepository.findById(id).orElseThrow( () -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }
}
