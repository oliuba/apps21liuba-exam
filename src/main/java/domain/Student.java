package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject studentInfo = super.toJsonObject();
        JsonObject[] examsJson = new JsonObject[exams.length];
        for (int i = 0; i < exams.length; i++) {
            JsonObject examInfo = new JsonObject();
            examInfo.add(new JsonPair("course", new JsonString(exams[i].key)));
            examInfo.add(new JsonPair("mark", new JsonNumber(exams[i].value)));
            boolean passed = exams[i].value > 2;
            examInfo.add(new JsonPair("passed", new JsonBoolean(passed)));
            examsJson[i] = examInfo;
        }
        studentInfo.add(new JsonPair("exams", new JsonArray(examsJson)));
        return studentInfo;
    }
}