package com.exdriving.school.controller;

import com.exdriving.school.domain.*;
import com.exdriving.school.repos.UserRepository;
import com.exdriving.school.service.serviceIml.ClientServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ClientController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ClientServiceIml clientServiceIml;

    Client client;

    @GetMapping("/client")
    public String client() {
        return "/panels/clientPanel/client";
    }
    @GetMapping("/client/")

    public String clientRedirect() {
        return "redirect:/client";
    }

    @GetMapping("/clientBasicInfo")
    public @ResponseBody
    Map<String, Object> getBasicClientInf(Model model) {
        client = getClientFromSecurity();
        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("clientName", client.getFirstName() + " " + client.getLastName());
        basicInfo.put("remainingHours", client.getRemainingHours());
        basicInfo.put("spentHours", client.getSpentHours());
        Instructor instructor = client.getInstructor();
        basicInfo.put("instructorName", instructor.getFirstName() + " " + instructor.getLastName());
        basicInfo.put("studentsCount", instructor.getClients().size());
        basicInfo.put("instructorCar", instructor.getCar().getModel() + " " + instructor.getCar().getStateNumber());
        return basicInfo;
    }

    @GetMapping("/currentLesson")
    public @ResponseBody
    List<String> getCurrentLesson(Model model) {
        client = getClientFromSecurity();
        Lesson lesson = client.getLesson();
        List<String> lessonInfo = new ArrayList<>();
        if (lesson != null) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
            String date = format.format(lesson.getDate());
            lessonInfo.add(date);
            lessonInfo.add(lesson.getClients().size() + "");
            lessonInfo.add(lesson.getStudentsLimit() + "");
            lessonInfo.add(lesson.getPlace().getAddress() + "");
        }
        return (lessonInfo.size() != 0) ? lessonInfo : null;
    }

    @GetMapping("/lessonList")
    public @ResponseBody
    List<String> getLessonList(Model model) {
        client = getClientFromSecurity();

        List<Lesson> lessonList = new ArrayList<>();
        for(Lesson lesson : client.getInstructor().getLessons()) {
            if(lesson.getStudentsLimit() <= lesson.getClients().size()) continue;
            if(lesson.getDate().compareTo(new Date()) < 0) continue;
            if(client.getLesson() != null && lesson.getId().equals(client.getLesson().getId())) continue;
            lessonList.add(lesson);
        }
        List<String> lessons = new ArrayList<>();
        Collections.sort(lessonList, new Comparator<Lesson>() {
            @Override
            public int compare(Lesson lesson, Lesson t1) {
                return lesson.getDate().compareTo(t1.getDate());
            }
        }.reversed());

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
        for(Lesson lesson : lessonList) {
            String date = format.format(lesson.getDate());
            String info = lesson.getId() + "/" + date + "/" + lesson.getPlace().getAddress() + "/" + lesson.getStudentsLimit() + "/" + lesson.getClients().size() + "/";
            lessons.add(info);
        }
        return (lessons.size() != 0) ? lessons : null;
    }

    @PostMapping("/recordToLesson")
    public @ResponseBody
    ResponseEntity<?> recordToLesson(@RequestBody String lessonID) {
        client = getClientFromSecurity();
        Integer id = Integer.parseInt(lessonID.substring(0,1));
        clientServiceIml.recordToLesson(client, id);
        return ResponseEntity.ok("200");
    }

    @PostMapping("/cancelRecording")
    public @ResponseBody
    ResponseEntity<?> cancelRecording() {
        client = getClientFromSecurity();
        if(client.getLesson() != null) {
            clientServiceIml.cancelRecording(client);
            return ResponseEntity.ok("200");
        }
        else {
            return ResponseEntity.badRequest().body("404");
        }

    }

    @GetMapping("/notifications")
    public @ResponseBody
    List<String> getNotifications(Model model) {
        client = getClientFromSecurity();
        List<String> notifications = new ArrayList<>();
        for(Notification notification : client.getNotifications()) {
            notifications.add(notification.getName() + "/" + notification.getText());
        }
        Collections.reverse(notifications);
        return notifications;
    }

    @PostMapping("/clearNotifications")
    public @ResponseBody
    ResponseEntity<?> clearNotifications() {
        client = getClientFromSecurity();
        clientServiceIml.removeNotifications(client);
        return ResponseEntity.ok("200");

    }

    @GetMapping("/marks")
    public @ResponseBody
    Map<Date, String> getMarks(Model model) {
        client = getClientFromSecurity();
        Map<Date, String> data = new TreeMap<>(Comparator.reverseOrder());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");

        for(Mark mark : client.getMarks()) {
            String date = format.format(mark.getLesson().getDate());
            data.put(mark.getLesson().getDate(), date + " " + mark.getMark());
        }
        return data;
    }

    @GetMapping("/certificates")
    public @ResponseBody
    List<String> getCertificates(Model model) {
        client = getClientFromSecurity();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        List<String> certificates = new ArrayList<>();
        for(Certificate certificate : client.getCertificates()) {
            String date = format.format(certificate.getDate());
            certificates.add(certificate.getNumber() + "/" + certificate.getClient().getFirstName() + " " + certificate.getClient().getLastName() + "/" + certificate.getMark() + "/" + date);
        }
        return certificates;
    }

    private Client getClientFromSecurity() {
        // достаем ID клиента из пользователя в сессии Spring Security (User из бд и User из сессии Spring - две разных сущности)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Integer clientID = userRepository.findByUsername(userDetails.getUsername()).getClientID();
        return clientServiceIml.findClientByID(clientID);
    }

}
