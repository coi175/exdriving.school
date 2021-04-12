package com.exdriving.school.controller;

import com.exdriving.school.domain.*;
import com.exdriving.school.repos.UserRepository;
import com.exdriving.school.service.serviceIml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class InstructorController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    InstructorServiceIml instructorServiceIml;

    @Autowired
    LessonServiceIml lessonServiceIml;

    @Autowired
    NotificationServiceIml notificationServiceIml;

    @Autowired
    LessonPlaceServiceIml lessonPlaceServiceIml;

    @Autowired
    ClientServiceIml clientServiceIml;

    @Autowired
    MarkServiceIml markServiceIml;

    Instructor instructor;

    @GetMapping("/instructor")
    public String instructor() {
        return "/panels/instructorPanel/instructor";
    }

    @GetMapping("/instructorBasicInfo")
    public @ResponseBody
    Map<String, Object> getBasicInsInfo(Model model) {
        instructor = getInstructorFromSecurity();
        Map<String, Object> basicInfo = new HashMap<>();
        basicInfo.put("instructorName", instructor.getFirstName() + " " + instructor.getLastName());
        basicInfo.put("instructorCar", instructor.getCar().getModel() + " " + instructor.getCar().getStateNumber());
        basicInfo.put("studentsCount", instructor.getClients().size());

        return basicInfo;
    }

    @GetMapping("clientsList")
    public @ResponseBody
    Map<String, String> getMarks(Model model) {
        instructor = getInstructorFromSecurity();
        Map<String, String> clients = new TreeMap<>(Comparator.naturalOrder());
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        for(Client client : instructor.getClients()) {
            String date;
            if(client.getLesson() != null) {
                date = format.format(client.getLesson().getDate());
            }
            else {
                date = "Нет записи";
            }

            clients.put(client.getFirstName(), client.getFirstName() + " " + client.getLastName() + "/" + client.getRemainingHours() + "/" + date);
        }
        return clients;
    }

    @GetMapping("/instructorGetLessons")
    public @ResponseBody
    Map<Date, String> getLessons(Model model) {
        instructor = getInstructorFromSecurity();
        Map<Date, String> lessons = new TreeMap<>();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for(Lesson lesson : instructor.getLessons()) {
            if(lesson.getDate().compareTo(new Date()) < 0) continue;
            String date = format.format(lesson.getDate());
            String info = lesson.getId() + "/" + date + "/" + lesson.getPlace().getAddress() + "/" + lesson.getClients().size() + "/" + lesson.getStudentsLimit();
            String clientList = "";
            for(Client client : lesson.getClients()) {
                clientList = clientList + client.getFirstName() + " " + client.getLastName() + "/";
            }

            lessons.put(lesson.getDate(), info + "&" + clientList);
        }
        return lessons;
    }

    @PostMapping("/deleteLesson")
    public @ResponseBody
    ResponseEntity<?> deleteLesson(@RequestBody String lessonID) {
        instructor = getInstructorFromSecurity();
        Integer id = Integer.parseInt(lessonID.substring(0, lessonID.length()-1));
        Set<Client> clientsForNotification = new LinkedHashSet<>();
        Lesson lesson = lessonServiceIml.findLessonByID(id);
        for(Client client : instructor.getClients()) {
            if(client.getLesson().getId().equals(id)) {
                clientsForNotification.add(client);
                client.setLesson(null);
            }
        }
        lessonServiceIml.deleteLessonByID(id);
        notificationServiceIml.createNotification(clientsForNotification, lesson);

        return ResponseEntity.ok("200");
    }

    @GetMapping("/getLessonPlacesForInstructor")
    public @ResponseBody List<String> getLessonPlaces(Model model) {
        List<String> addresses = new ArrayList<>();
        for(LessonPlace lessonPlace : lessonPlaceServiceIml.findAll()) {
            addresses.add(lessonPlace.getAddress());
        }
        return addresses;
    }

    @PostMapping(value = "/createLesson", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createLesson(@RequestBody LessonData lessonData) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date date = null;
        try {
            date = format.parse(lessonData.getDate() + " " + lessonData.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        instructor = getInstructorFromSecurity();

        for(Lesson lesson : instructor.getLessons()) {
            String lDate = format.format(lesson.getDate());
            String dDate = lessonData.getDate() + " " + lessonData.getTime();
            if(lDate.equals(dDate)) return ResponseEntity.badRequest().body("404");
        }
        LessonPlace lessonPlace = lessonPlaceServiceIml.findLessonPlaceByAddress(lessonData.getAddress());

        lessonServiceIml.createLesson(date, instructor, lessonPlace, Integer.parseInt(lessonData.getStudentsLimit()));

        return ResponseEntity.ok("200");
    }

    @GetMapping("/getLessonsForMark")
    public @ResponseBody Map<Integer, String> getLessonsForMark(Model model) {
        Map<Integer, String> lessons = new HashMap<>();
        instructor = getInstructorFromSecurity();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        for(Lesson lesson : instructor.getLessons()) {
            lessons.put(lesson.getId(), format.format(lesson.getDate()));
        }
        return lessons;
    }

    @GetMapping("/getStudentsForMark")
    public @ResponseBody Map<Integer, String> getStudentsForMark(Model model) {
        Map<Integer, String> students = new HashMap<>();
        instructor = getInstructorFromSecurity();
        for(Client client : instructor.getClients()) {
            students.put(client.getId(), client.getFirstName() + " " + client.getLastName());
        }
        return students;
    }

    @PostMapping(value = "/addMark", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<?> createLesson(@RequestBody Map<String, String> values) {
        Lesson lesson = lessonServiceIml.findLessonByID(Integer.parseInt(values.get("lessonID")));
        Client client = clientServiceIml.findClientByID(Integer.parseInt(values.get("clientID")));
        int mark = Integer.parseInt(values.get("mark"));
        if(markServiceIml.findAllByLesson(lesson).size() >= lesson.getStudentsLimit()) return ResponseEntity.badRequest().body("404");
        markServiceIml.addMark(lesson, client, mark);
        return ResponseEntity.ok("200");
    }

    private Instructor getInstructorFromSecurity() {
        // достаем ID клиента из пользователя в сессии Spring Security (User из бд и User из сессии Spring - две разных сущности)
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        Integer instructorID = userRepository.findByUsername(userDetails.getUsername()).getInstructorID();
        return instructorServiceIml.findInstructorByID(instructorID);
    }


}
