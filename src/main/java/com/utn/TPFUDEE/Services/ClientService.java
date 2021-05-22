package com.utn.TPFUDEE.Services;

import com.utn.TPFUDEE.Exceptions.Exist.ClientExistException;
import com.utn.TPFUDEE.Exceptions.NoContent.ClientNoContentException;
import com.utn.TPFUDEE.Exceptions.NotFound.ClientNotFoundException;
import com.utn.TPFUDEE.Models.Client;
import com.utn.TPFUDEE.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Page<Client> getAll(Pageable pageable)throws ClientNoContentException{
        Page<Client> clientList =  clientRepository.findAll(pageable);
        if(clientList.isEmpty()){
            throw new ClientNoContentException();
        }
        return clientList;
    }

    public void add(Client client) throws ClientExistException{
        boolean flag = false;
        for(Client var : this.clientRepository.findAll()){
            if(client.getDni().equals(var.getDni())){
                flag = true;
            }
        }
        if(flag){
            throw new ClientExistException();
        }else{
            clientRepository.save(client);
        }
    }

    public Client getById(Integer id) throws ClientNotFoundException {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException());
    }

    public void deleteById(Integer id) throws ClientNotFoundException{
        this.getById(id);
        clientRepository.deleteById(id);
    }


}
