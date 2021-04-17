package com.exdriving.school.service.serviceIml;

import com.exdriving.school.domain.Client;
import com.exdriving.school.domain.Instructor;
import com.exdriving.school.domain.Lesson;
import com.exdriving.school.domain.Notification;
import com.exdriving.school.repos.ClientRepository;
import com.exdriving.school.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceIml implements ClientService {
    @Autowired
    ClientRepository clientRepository;

    @Override
    public Client findClientByID(Integer id) {
        return clientRepository.findClientById(id);
    }

    @Override
    public void removeNotifications(Client client) {
        client.getNotifications().clear();
        clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void cancelRecording(Client client) {
        client.setLesson(null);
        client.setRemainingHours(client.getRemainingHours()+1);
        client.setSpentHours(client.getSpentHours()-1);
        clientRepository.save(client);
    }

    @Override
    public void recordToLesson(Client client, Integer id) {
        if(client.getLesson() == null) {
            client.setSpentHours(client.getSpentHours() + 1);
            client.setRemainingHours(client.getRemainingHours()-1);
        }
        for(Lesson lesson : client.getInstructor().getLessons()) {
            if(lesson.getId().equals(id)) client.setLesson(lesson);
        }
        clientRepository.save(client);
    }

    @Override
    public void changeData(Client client, Instructor instructor, Integer remainingHours) {
        client.setInstructor(instructor);
        client.setRemainingHours(remainingHours);
        clientRepository.saveAndFlush(client);
    }

    @Override
    public Client createClient(String firstName, String lastName, String email, Integer hoursLimit, Instructor instructor) {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setEmail(email);
        client.setRemainingHours(hoursLimit);
        client.setInstructor(instructor);
        return clientRepository.saveAndFlush(client);
    }


}
