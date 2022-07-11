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
import java.util.*;

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
                visits.setClientId(clients.getId());
            }
        }

        String[] tokenD = visitDto.getFullDoctorInfo().split(" ");
        for (Doctors doctors : doctorsRepo.findAll()) {
            if (doctors.getLastName().equalsIgnoreCase(tokenD[0]) && doctors.getName().equalsIgnoreCase(tokenD[1]) && doctors.getPatronymic().equalsIgnoreCase(tokenD[2])) {
                visits.setDoctorId(doctors.getId());
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
                visits.setClientId(clients.getId());
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
                visits.setDoctorId(doctors.getId());
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
    public List<VisitDto> findAllForClientAfter(Long id) {
        List<VisitDto> list = new ArrayList<>();
        for (Visits visit : visitsRepo.findAllByClientId(id)) {
            if (visit.getDateAndTime().after(new Timestamp(new Date().getTime()))) {
                list.add(map(visit));
            }
        }
        return list;
//        return Streamable.of(visitsRepo.findAllByClientId(id)).map(this::map).toList();
    }

    @Override
    public List<VisitDto> findAllForClientBefore(Long id) {
        List<VisitDto> list = new ArrayList<>();
        for (Visits visit : visitsRepo.findAllByClientId(id)) {
            if (visit.getDateAndTime().before(new Timestamp(new Date().getTime()))) {
                list.add(map(visit));
            }
        }
        return list;
    }

    @Override
    public List<VisitDto> findAllForDoctorAfter(Long id) {
        List<VisitDto> list = new ArrayList<>();
        for (Visits visit : visitsRepo.findAllByDoctorId(id)) {
            if (visit.getDateAndTime().after(new Timestamp(new Date().getTime()))) {
                list.add(map(visit));
            }
        }
//        return Streamable.of(visitsRepo.findAllByDoctorId(id)).map(this::map).toList();
        return list;
    }

    @Override
    public List<VisitDto> findAllForDoctorBefore(Long id) {
        List<VisitDto> list = new ArrayList<>();
        for (Visits visit : visitsRepo.findAllByDoctorId(id)) {
            if (visit.getDateAndTime().before(new Timestamp(new Date().getTime()))) {
                list.add(map(visit));
            }
        }
//        return Streamable.of(visitsRepo.findAllByDoctorId(id)).map(this::map).toList();
        return list;
    }

    @Override
    public List<VisitDto> findAll() {
        return Streamable.of(visitsRepo.findAll()).map(this::map).toList();
    }

    private VisitDto map(Visits visits) {
        VisitDto result = new VisitDto();
        Optional<Doctors> doctors = doctorsRepo.findById(visits.getDoctorId());
        Optional<Clients> clients = clientsRepo.findById(visits.getClientId());
        String doctorInfo = null;
        if (doctors.isPresent()) {
            Doctors doctors1 = doctors.get();
            doctorInfo = doctors1.getLastName() + " " + doctors1.getName() + " " + doctors1.getPatronymic()+" / "+doctors1.getSpecialization();
        }
        String clientInfo = null;
        if (clients.isPresent()) {
            Clients clients1 = clients.get();
            clientInfo = clients1.getLastName() + " " + clients1.getFirstName() + " " + clients1.getPatronymic() + " / " + clients1.getSnils();
        }
        result.setFullClientInfo(clientInfo);
        result.setFullDoctorInfo(doctorInfo);
        Date date = new Date(visits.getDateAndTime().getTime());
        result.setDateAndTime(dateFormat.format(date));
        result.setId(visits.getId());
        return result;
    }

}




