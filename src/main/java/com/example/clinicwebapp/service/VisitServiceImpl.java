package com.example.clinicwebapp.service;

import com.example.clinicwebapp.model.Clients;
import com.example.clinicwebapp.model.Doctors;
import com.example.clinicwebapp.model.Visits;
import com.example.clinicwebapp.model.dto.VisitDto;
import com.example.clinicwebapp.repo.ClientsRepo;
import com.example.clinicwebapp.repo.DoctorsRepo;
import com.example.clinicwebapp.repo.VisitsRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitsRepo visitsRepo;
    private final ClientsRepo clientsRepo;
    private final DoctorsRepo doctorsRepo;

    private final ModelMapper mapper;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");

    @Override
    public void save(VisitDto visitDto) {
        Visits visits = new Visits();

        visits.setId(visitDto.getId());
        String[] tokenC = visitDto.getFullClientInfo().split(" ");
        for (Clients clients : clientsRepo.findAll()) {
            if (tokenC[0].equalsIgnoreCase(clients.getLastName()) && tokenC[1].equalsIgnoreCase(clients.getFirstName()) && tokenC[2].equalsIgnoreCase(clients.getPatronymic()) && tokenC[3].equalsIgnoreCase(clients.getSnils())) {
                visits.setClients(clients);
            }
        }

        String[] tokenD = visitDto.getFullDoctorInfo().split(" ");
        for (Doctors doctors : doctorsRepo.findAll()) {
            if (doctors.getLastName().equalsIgnoreCase(tokenD[0]) && doctors.getName().equalsIgnoreCase(tokenD[1]) && doctors.getPatronymic().equalsIgnoreCase(tokenD[2])) {
                visits.setDoctors(doctors);
            }
        }


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date parseDate = dateFormat.parse(visitDto.getDateAndTime());
            Timestamp timestamp = new Timestamp(parseDate.getTime());
            visits.setDateAndTime(timestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        visitsRepo.save(visits);

    }

    @Override
    public void update(VisitDto visitDto) {
        Visits visits = visitsRepo.findById(visitDto.getId()).orElseThrow();


        String[] tokenC = visitDto.getFullClientInfo().split(" ");
        for (Clients clients : clientsRepo.findAll()) {
            if (tokenC[0].equalsIgnoreCase(clients.getLastName()) && tokenC[1].equalsIgnoreCase(clients.getFirstName()) && tokenC[2].equalsIgnoreCase(clients.getPatronymic()) && tokenC[3].equalsIgnoreCase(clients.getSnils())) {
                visits.setClients(clients);
            }
        }


        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
            Date parseDate = dateFormat.parse(visitDto.getDateAndTime());
            Timestamp timestamp = new Timestamp(parseDate.getTime());
            visits.setDateAndTime(timestamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        String[] tokenD = visitDto.getFullDoctorInfo().split(" ");
        for (Doctors doctors : doctorsRepo.findAll()) {
            if (doctors.getLastName().equalsIgnoreCase(tokenD[0]) && doctors.getName().equalsIgnoreCase(tokenD[1]) && doctors.getPatronymic().equalsIgnoreCase(tokenD[2])) {
                visits.setDoctors(doctors);
            }
        }


        visitsRepo.save(visits);


    }

    @Override
    public void remove(Long id) {
        visitsRepo.deleteById(id);


    }

    @Override
    public Optional<VisitDto> findById(Long id) {
        return visitsRepo.findById(id).map(this::map);
    }

    @Override
    public List<VisitDto> findAll() {
        return Streamable.of(visitsRepo.findAll()).map(this::map).toList();
    }

    private VisitDto map(Visits visits) {
        VisitDto result = new VisitDto();
        String clientInfo = visits.getClients().getLastName() + " " + visits.getClients().getFirstName() + " " + visits.getClients().getPatronymic();
        String doctorInfo = visits.getDoctors().getLastName() + " " + visits.getDoctors().getName() + " " + visits.getDoctors().getPatronymic();
        result.setFullClientInfo(clientInfo);
        result.setFullDoctorInfo(doctorInfo);
        Date date = new Date(visits.getDateAndTime().getTime());
        result.setDateAndTime(dateFormat.format(date));
        result.setId(visits.getId());
        return result;
    }

}




