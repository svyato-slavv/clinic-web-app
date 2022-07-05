package com.example.clinicwebapp.service;

import com.example.clinicwebapp.model.Clients;
import com.example.clinicwebapp.model.dto.ClientDto;
import com.example.clinicwebapp.repo.ClientsRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ModelMapper modelMapper;
    private final ClientsRepo clientsRepo;


    @Override
    public Long save(ClientDto clientDto) {
        Clients clients = new Clients();
        clients.setId(clientDto.getId());
        clients.setFirstName(clientDto.getFirstname());
        clients.setLastName(clientDto.getLastname());
        clients.setPatronymic(clientDto.getPatronymic());
        clients.setPassportSeries(clientDto.getPassportSeries());
        clients.setPassportNumber(clientDto.getPassportNumber());
        clients.setSnils(clientDto.getSnils());
        clients.setContactNumber(clientDto.getContactNumber());

        clients = clientsRepo.save(clients);

        return clients.getId();
    }

    @Override
    public void update(ClientDto dto) {
        Clients clients = clientsRepo.findById(dto.getId()).orElseThrow();
        clients.setFirstName(dto.getFirstname());
        clients.setLastName(dto.getLastname());
        clients.setPatronymic(dto.getPatronymic());
        clients.setPassportSeries(dto.getPassportSeries());
        clients.setPassportNumber(dto.getPassportNumber());
        clients.setSnils(dto.getSnils());
        clients.setContactNumber(dto.getContactNumber());

        clientsRepo.save(clients);
    }

    @Override
    public Optional<ClientDto> findById(Long id) {
        return clientsRepo.findById(id).map(this::map);
    }

    @Override
    public boolean existById(Long id) {
        return clientsRepo.existsById(id);
    }

    @Override
    public void remove(ClientDto dto) {
        Clients clients = clientsRepo.findById(dto.getId()).orElseThrow();
        clientsRepo.delete(clients);
    }

    @Override
    public List<ClientDto> findAll() {
        return Streamable.of(clientsRepo.findAll()).map(this::map).toList();
    }

    private ClientDto map(Clients clients) {
        return modelMapper.map(clients, ClientDto.class);
    }

}
