package ru.edu.bsu;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private boolean isStudent;
    private String param;
    private String url;

    /**
     *
     * @param isStudent true - расписание студентов, false -преподавателя
     * @param param параметр группа для студента, userid для преподавателя
     */
    public Schedule(boolean isStudent, String param) {
        this.url = "https://dekanat.bsu.edu.ru/blocks/bsu_api/bsu_schedule";
        this.isStudent = isStudent;
        this.param = param;
    }

    /**
     * Получить расписание на текущий день
     * @return Массив объектов Lessons
     */
    protected Lesson[] getCurrentSchedule(){
        DateTimeFormatter formatter
                = DateTimeFormatter.ofPattern(
                "dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        String date = now.format(formatter);

        if (isStudent){
            return getScheduleStudent(date);
        }else {
            //:TODO в будущем добавить для преподов
            return getScheduleStudent(date);
        }
    }

    /**
     *
     * @param date Дата в формате "dd.MM.yyyy", за которую нужно вытащить расписание
     * @return
     */
    protected Lesson[] getScheduleByDate(String date){
        if (isStudent){
            return getScheduleStudent(date);
        }else {
            //:TODO в будущем добавить для преподов
            return getScheduleStudent(date);
        }
    }

    private Lesson[] getScheduleStudent(String date){
        this.url = url + "/readStudent.php?os=android&group=" + param + "&date=" + date;
        try {
            return getLessons();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Lesson[] getLessons() throws IOException {
        URL url = new URL(this.url);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.readTree(url);
        if (!json.has("message")){
            String schedule = String.valueOf(json.get("schedule"));
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            Lesson[] lessons = mapper.readValue(schedule, Lesson[].class);
            return lessons;
        }
        return new Lesson[0];
    }
}
