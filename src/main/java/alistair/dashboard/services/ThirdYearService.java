package alistair.dashboard.services;

import alistair.dashboard.models.Student;
import alistair.dashboard.models.ThirdYearMarks;
import alistair.dashboard.repositories.ThirdYearRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ThirdYearService {
    private final ThirdYearRepository repository;

    public ThirdYearService(ThirdYearRepository repository) {
        this.repository = repository;
    }

    public void enrollStudents(ThirdYearMarks marks){
        repository.save(marks);
    }

    public String getLastId(){
        return repository.getLastID();
    }

    public List<ThirdYearMarks> getAllMarks(){
        return repository.findAll();
    }

    public void saveMarks(ThirdYearMarks marks){
        repository.save(marks);
    }

    public List<ThirdYearMarks> getMarksByYear(Date year1, Date year2){
        return repository.getMarksByYear(year1,year2);
    }

    public List<ThirdYearMarks> getFailedStudents(int mark, Date date){
        return repository.getFailedStudents(mark, date);
    }

    public List<ThirdYearMarks> getMarksByStatus(){
        return repository.getMarksByStatus();
    }

    public List<ThirdYearMarks> getStudentAndAcademicYear(){
        return repository.getStudentAndAcademicYear();
    }

    public List<ThirdYearMarks> getByStudentNumberAndYear(long student_number, Date date){
        return repository.getByStudentNumberAndYear(student_number, date);
    }

    public List<ThirdYearMarks> getAcademicYears(){
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

    public ThirdYearMarks getFailedSubject(Date date, long student_number){
        return repository.getFailedSubject(date, student_number).orElse(null);
    }

    public double getTeacherAvg(Date date, long subject1, long subject2){
        return repository.getTeacherAvg(date, subject1, subject2);
    }

    public List<ThirdYearMarks> getMarksForSemOne(long student_number, Date date){
        return repository.getMarksForSemOne(student_number, date);
    }

    public List<ThirdYearMarks> getMarksForSemTwo(long student_number, Date date){
        return repository.getMarksForSemTwo(student_number, date);
    }

    public int getTotalStudentsForSemOne(){
        return repository.getTotalStudentsForSemOne().size();
    }

    public int getTotalStudentsForSemTwo(){
        return repository.getTotalStudentsForSemTwo().size();
    }
}
