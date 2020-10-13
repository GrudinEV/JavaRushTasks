package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        //TODO:
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {
        //TODO:
        Student student = students.get(0);
        double maxAverageGrade = student.getAverageGrade();
        for (Student studentFromList : students) {
            if (maxAverageGrade < studentFromList.getAverageGrade()) {
                maxAverageGrade = studentFromList.getAverageGrade();
                student = studentFromList;
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade() {
        Student student = students.get(0);
        double minAverageGrade = student.getAverageGrade();
        for (Student studentFromList : students) {
            if (minAverageGrade > studentFromList.getAverageGrade()) {
                student = studentFromList;
                minAverageGrade = student.getAverageGrade();
            }
        }
        return student;
    }

    public void expel(Student student) {
        students.remove(student);
    }
}