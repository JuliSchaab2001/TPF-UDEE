package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Models.Projections.ClientProjection;
import com.utn.TPFUDEE.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAll(Pageable pageable){
        Page<Client> clientList =  clientRepository.findAll(pageable);
        if(clientList.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Empty Client List");
        }
        return clientList;
    }

    public Client add(Client client){
        if((clientRepository.findById(client.getDni())).orElse(null) == null){
            return clientRepository.save(client);
        }else{
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client Already Exist");
        }
    }

    public Client getById(Integer id){
        return clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client Not Found"));
    }

    public Integer deleteById(Integer id){
        this.getById(id);
        clientRepository.deleteById(id);
        return id;
    }

    public List<ClientProjection> getTopTenMostConsumers(LocalDate from, LocalDate to) {
        return clientRepository.getTopTenMostConsumers(from.atTime(LocalTime.MIN),to.atTime(LocalTime.MAX));
    }
}
