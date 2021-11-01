package alistair.dashboard.services;

import alistair.dashboard.models.SecondYearMarks;
import alistair.dashboard.models.Student;
import alistair.dashboard.repositories.SecondYearRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SecondYearService {
    private final SecondYearRepository repository;

    public SecondYearService(SecondYearRepository repository) {
        this.repository = repository;
    }

    public void enrollStudents(SecondYearMarks marks){
        repository.save(marks);
    }

    public String getLastId(){
        return repository.getLastID();
    }

    public void saveMarks(SecondYearMarks marks){
        repository.save(marks);
    }

    public List<SecondYearMarks> getAllMarks(){
        return repository.findAll();
    }

    public List<SecondYearMarks> getMarksByYear(Date year1, Date year2){
        return repository.getMarksByYear(year1,year2);
    }

    public List<SecondYearMarks>  getFailedStudents(int mark, Date date){
        return repository.getFailedStudents(mark, date);
    }

    public List<SecondYearMarks> getMarksByStatus(){
        return repository.getMarksByStatus();
    }

    public List<SecondYearMarks> getStudentAndAcademicYear(){
        return repository.getStudentAndAcademicYear();
    }

    public List<SecondYearMarks> getByStudentNumberAndYear(long student_number, Date date){
        return repository.getByStudentNumberAndYear(student_number, date);
    }

    public List<SecondYearMarks> getAcademicYears(){
        return repository.getAcademicYears();
    }

    public double getAvgMarksByYear(Date year1, Date year2){
        return repository.getAvgMarksByYear(year1,year2);
    }

    public double getAvgSemesterMark(){
        return repository.getAvgSemesterMark();
    }

    public double getAvgCa1Mark(){
        return repository.getAvgCa1Mark();
    }

    public double getAvgCa2Mark(){
        return repository.getAvgCa2Mark();
    }

    public double getAvgAssignmentMark(){
        return repository.getAvgAssignmentMark();
    }

    public double getAvgExamMark(){
        return repository.getAvgExamMark();
    }

    public List<Student> getStudentsByYear(Date date){
        return repository.getStudentsByYear(date);
    }

    public double getStudentAvgFinalMark(Date date, long student_number){
        return repository.getStudentAvgFinalMark(date, student_number);
    }

    public SecondYearMarks getFailedSubject(Date date, long student_number){
        return repository.getFailedSubject(date, student_number).orElse(null);
    }

    public double getTeacherAvg(Date date, long subject1, long subject2){
        return repository.getTeacherAvg(date, subject1, subject2);
    }

    public List<SecondYearMarks> getMarksForSemOne(long student_number, Date date){
        return repository.getMarksForSemOne(student_number, date);
    }

    public List<SecondYearMarks> getMarksForSemTwo(long student_number, Date date){
        return repository.getMarksForSemTwo(student_number, date);
    }

    public int getTotalStudentsForSemOne(){
        return repository.getTotalStudentsForSemOne().size();
    }

    public int getTotalStudentsForSemTwo(){
        return repository.getTotalStudentsForSemTwo().size();
    }
}
