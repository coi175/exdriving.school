package com.exdriving.school.controller;

import com.exdriving.school.domain.*;
import com.exdriving.school.service.CarService;
import com.exdriving.school.service.serviceIml.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

/**
 * Контроллер для панели (личного кабинета) администратора
 */
@Controller
public class AdminController {

    @Autowired
    ClientServiceIml clientServiceIml;

    @Autowired
    InstructorServiceIml instructorServiceIml;

    @Autowired
    CertificateServiceIml certificateServiceIml;

    @Autowired
    UserServiceIml userServiceIml;

    @Autowired
    CarServiceIml carServiceIml;

    @Autowired
    LessonPlaceServiceIml lessonPlaceServiceIml;

    /**
     * возвращает html страницу
     */
    @GetMapping("/admin")
    public String admin() {
        return "panels/adminPanel/admin";
    }

    /**
     * Перенаправляет на правильный адрес, если пользователь ввёл неправильный в строку браузера
     * @return
     */
    @GetMapping("/admin/")
    public String adminRedirect() {
        return "redirect:admin";
    }

    /**
     * Возвращает базовую информацию об админе (никнейм)
     */
    @GetMapping("/adminBasicInfo")
    public @ResponseBody
    List<String> getAdminBasicInfo() {
        // получаем запись об аутентификации
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // получаем инфу о пользователе который там лежит
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        List<String> values = new ArrayList<>();
        // добавляем username пользователя в список
        values.add(userDetails.getUsername());
        // возвращаем на сервер
        return values;
    }


    // -------------------Вкладка "Список клиентов" -----------------------------------

    /**
     * Возвращает список клиентов
     */
    @GetMapping("/adminGetClientList")
    public @ResponseBody
    Map<String, String> getClientList(Model model) {
        Map<String, String> clients = new TreeMap<>(Comparator.naturalOrder());
        // получаем всех клиентов из базы данных и перебираем их в цикле for
        for(Client client : clientServiceIml.getAllClients()) {
            // достаем имя фамилию клиента
            String name = client.getFirstName() + " " + client.getLastName();
            // достаем имя фамилию инструктора прикрепленного к клиенту
            String instructorName = client.getInstructor().getFirstName() + " " + client.getInstructor().getLastName();
            // добавляем в Map
            clients.put(name, client.getId() + "/" + name + "/" + client.getEmail() + "/" + client.getRemainingHours() + "/" + instructorName);
        }
        // посылаем map обратно в браузер
        return clients;
    }

    /**
     * Возращает информацию для клиента по ID
     * @return Map<String, List<String>>
     */
    @PostMapping("/openAndGetClientInfo")
    public @ResponseBody
    Map<String, List<String>> getClientInfo(@RequestBody Map<String, String> values) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy");
        int id = Integer.parseInt(values.get("id"));
        Client client = clientServiceIml.findClientByID(id);
        Map<String, List<String>> info = new LinkedHashMap<>();

        // Базовая информация о клиенте
        List<String> baseInfo = new ArrayList<>();
        baseInfo.add(client.getId()+"");
        baseInfo.add(client.getFirstName() + " " + client.getLastName());
        baseInfo.add(client.getEmail());
        baseInfo.add(client.getInstructor().getFirstName() + " " + client.getInstructor().getLastName());
        baseInfo.add(client.getRemainingHours()+"");
        baseInfo.add(client.getSpentHours()+"");
        info.put("baseInfo", baseInfo);

        // список оценок клиента
        List<String> marks = new ArrayList<>();
        for(Mark mark : client.getMarks()) {
            marks.add(format.format(mark.getLesson().getDate()) + "/" + mark.getMark());
        }
        info.put("marks", marks);

        // список сертификатов клиента
        List<String> certificates = new ArrayList<>();
        for(Certificate certificate : client.getCertificates()) {
            certificates.add(certificate.getNumber() + "/" + certificate.getMark() + "/" + format1.format(certificate.getDate()));
        }
        info.put("certificates", certificates);
        return info;
    }

    /**
     * возвращает список инструкторов для клиента
     * @return Map<Integer, String>
     */
    @GetMapping("/getInstructorListForClient")
    public @ResponseBody
    Map<Integer, String> getInstructorListForClient(Model model) {
        Map<Integer, String> instructors = new LinkedHashMap<>();
        for(Instructor instructor : instructorServiceIml.getAll()) {
            instructors.put(instructor.getId(), instructor.getFirstName() + " " + instructor.getLastName());
        }
        return instructors;
    }

    /**
     * Изменяет информацию о клиенте (кол-во часов, прикрепленный инструктор)
     * @return ResponseEntity<?>
     */
    @PostMapping("/changeClientData")
    ResponseEntity<?> changeClientData(@RequestBody Map<String, String> values) {
        Instructor instructor = instructorServiceIml.findInstructorByID(Integer.parseInt(values.get("instructorID")));
        Client client = clientServiceIml.findClientByID(Integer.parseInt(values.get("clientID")));
        Integer remainingHours = Integer.parseInt(values.get("remainingHours"));
        clientServiceIml.changeData(client, instructor, remainingHours);
        return ResponseEntity.ok("200");
    }

    /**
     * Добавляет сертификат и прикрепляет его к клиенту по ID
     * @return ResponseEntity<?>
     */
    @PostMapping("/giveCertForClient")
    ResponseEntity<?> giveCertificateForClient(@RequestBody Map<String, String> values) {
        Client client = clientServiceIml.findClientByID(Integer.parseInt(values.get("clientID")));
        Integer certificateNumber = Integer.parseInt(values.get("certificateNumber"));
        Integer certificateMark = Integer.parseInt(values.get("certificateMark"));
        certificateServiceIml.addCertificate(client, certificateNumber, certificateMark);

        return ResponseEntity.ok("200");
    }

    // -------------------Вкладка "Регистрация аккаунтов" -----------------------------------

    /**
     * Создает нового клиента
     * @return ResponseEntity<?>
     */
    @PostMapping("/registerNewClient")
    ResponseEntity<?> registerNewClient(@RequestBody Map<String, String> values) {
        Instructor instructor = instructorServiceIml.findInstructorByID(Integer.parseInt(values.get("instructorID")));
        Integer hours = Integer.parseInt(values.get("hoursLimit"));
        userServiceIml.addClient(values.get("login"), values.get("password"),
                clientServiceIml.createClient(values.get("firstName"), values.get("lastName"), values.get("email"), hours, instructor));
        return ResponseEntity.ok("200");
    }

    /**
     * Возвращает список ммышин для регистрации инструктора
     * @return Map<Integer, String>
     */
    @GetMapping("/getCarsForInstructorRegister")
    public @ResponseBody
    Map<Integer, String> getCarsForInstructorRegister(Model model) {
        Map<Integer, String> cars = new LinkedHashMap<>();
        for(Car car : carServiceIml.getAllCars()) {
            if(car.getInstructor() == null) {
                cars.put(car.getId(), car.getModel() + "   |   " + car.getStateNumber());
            }
        }
        return cars;
    }

    /**
     * Создает нового инструктора
     * @return ResponseEntity<?>
     */
    @PostMapping("/registerNewInstructor")
    ResponseEntity<?> registerNewInstructor(@RequestBody Map<String, String> values) {
        String carID = values.get("carID");
        Car car;
        // если ID машину пустое, значит инструктору не назначена машина
        if(carID != null) {
            car = carServiceIml.getCarById(Integer.parseInt(values.get("carID")));
        }
        else {
            car = null;
        }
        userServiceIml.addInstructor(values.get("login"), values.get("password"),
                instructorServiceIml.createInstructor(values.get("firstName"), values.get("lastName"), car));
        return ResponseEntity.ok("200");
    }


    // -------------------Вкладка "Добавление информации" -----------------------------------

    /**
     * Создает новое место занятий
     * @return ResponseEntity<?>
     */
    @PostMapping("/addNewPlace")
    ResponseEntity<?> AddNewPlace(@RequestBody Map<String, String> values) {
        String address = values.get("city") + ", " + values.get("street") + ", " + values.get("house");
        lessonPlaceServiceIml.addPlace(address);
        return ResponseEntity.ok("200");
    }

    /**
     * Создает новую машину
     * @return ResponseEntity<?>
     */
    @PostMapping("/addNewCar")
    ResponseEntity<?> addNewCar(@RequestBody Map<String, String> values) {
        carServiceIml.addCar(values.get("brand"), values.get("model"), values.get("number"));
        return ResponseEntity.ok("200");
    }


    // -------------------Вкладка "Список инструкторов" -----------------------------------

    /**
     * возвращает список инструкторов
     * @return Map<String, String>
     */
    @GetMapping("/adminGetInstructorList")
    public @ResponseBody
    Map<String, String> getInstructorList(Model model) {
        Map<String, String> instructors = new TreeMap<>(Comparator.naturalOrder());
        for(Instructor instructor : instructorServiceIml.getAll()) {
            String name = instructor.getFirstName() + " " + instructor.getLastName();
            String car;
            if(instructor.getCar() != null) {
                car = instructor.getCar().getModel() + " " + instructor.getCar().getStateNumber();
            }
            else{
                car = "Не назначена";
            }
            instructors.put(name, instructor.getId() + "/" + name + "/" + instructor.getClients().size() + "/" + car);
        }
        return instructors;
    }

    /**
     * возвращает информацию об инструкторе по ID
     * @return Map<String, List<String></String>>
     */
    @PostMapping("/openAndGetInstructorInfo")
    public @ResponseBody
    Map<String, List<String>> getInstructorInfo(@RequestBody Map<String, String> values) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        int id = Integer.parseInt(values.get("id"));
        Instructor instructor = instructorServiceIml.findInstructorByID(id);
        Map<String, List<String>> info = new LinkedHashMap<>();

        // Базовая информация (ФИ, ID, кол-во учеников, кол-во занятий)
        List<String> baseInfo = new ArrayList<>();
        baseInfo.add(instructor.getId()+"");
        baseInfo.add(instructor.getFirstName() + " " + instructor.getLastName());
        if(instructor.getCar() != null) {
            baseInfo.add(instructor.getCar().getModel() + " " + instructor.getCar().getModel());
        }
        else {
            baseInfo.add("Не назначена");
        }
        baseInfo.add(instructor.getClients().size() + "");
        baseInfo.add(instructor.getLessons().size() + "");
        info.put("baseInfo", baseInfo);

        // Список клиентов
        List<String> clients = new ArrayList<>();
        for(Client client : instructor.getClients()) {
            clients.add(client.getFirstName() + " " + client.getLastName() + "/" + client.getEmail() + "/" + client.getRemainingHours());
        }
        info.put("clients", clients);

        // Список занятий
        List<String> lessons = new ArrayList<>();
        for(Lesson lesson : instructor.getLessons()) {
            String date = format.format(lesson.getDate());
            lessons.add(date + "/" + lesson.getPlace().getAddress() + "/" + lesson.getClients().size());
        }
        info.put("lessons", lessons);
        return info;
    }

    /**
     * Возвращает список машин для инструктора
     * @return Map<Integer, String>
     */
    @GetMapping("/getCarsForInstructor")
    public @ResponseBody
    Map<Integer, String> getCarsForInstructor(Model model) {
        Map<Integer, String> cars = new LinkedHashMap<>();
        for(Car car : carServiceIml.getAllCars()) {
            if(car.getInstructor() == null) {
                cars.put(car.getId(), car.getModel() + " " + car.getStateNumber());
            }
        }
        return cars;
    }

    /**
     * устанавливает новую машину для инструктора
     *  @return ResponseEntity<?>
     */
    @PostMapping("/changeCarForInstructor")
    ResponseEntity<?> changeCarForInstructor(@RequestBody Map<String, String> values) {
        Instructor instructor = instructorServiceIml.findInstructorByID(Integer.parseInt(values.get("instructorID")));
        Car car;
        if(!values.get("carID").equals("")) {
            car = carServiceIml.getCarById(Integer.parseInt(values.get("carID")));
        }
        else {
            car = null;
        }
        instructorServiceIml.setNewCar(instructor, car);
        return ResponseEntity.ok("200");
    }
}
