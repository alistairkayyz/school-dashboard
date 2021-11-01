package alistair.dashboard.controllers;

import alistair.dashboard.models.*;
import alistair.dashboard.services.*;
import org.springframework.web.bind.annotation.*;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;

@RestController
public class LoadController {

    private final StudentService studentService;
    private final ParentService parentService;
    private final FirstYearService firstYearService;
    private final SecondYearService secondYearService;
    private final ThirdYearService thirdYearService;

    public LoadController(StudentService studentService,
                          ParentService parentService,
                          FirstYearService firstYearService,
                          SecondYearService secondYearService,
                          ThirdYearService thirdYearService) {
        this.studentService = studentService;
        this.parentService = parentService;
        this.firstYearService = firstYearService;
        this.secondYearService = secondYearService;
        this.thirdYearService = thirdYearService;
    }

    @GetMapping("/load/students")
    public List<Student> getStudents(){

        return studentService.getStudents();
    }
    @PostMapping("/save/students")
    public List<Student> saveStudents(@RequestBody List<Student> students){
        studentService.saveAllStudents(students);
        return studentService.getStudents();
    }
    @PutMapping("/save/students/all")
    public List<Student> updateStudents(@RequestBody List<Student> students){
        studentService.saveAllStudents(students);
        return studentService.getStudents();
    }
    @PostMapping("/load/parents")
    public List<Parent> getParents(){

        return parentService.getParents();
    }
    @PostMapping("/save/parents")
    public List<Parent> saveParents(@RequestBody List<Parent> parents){
        parentService.saveAllParent(parents);
        return parentService.getParents();
    }

    @PostMapping("marks/null/year3")
    public List<StudentMarks> marksNullThird(@RequestBody List<StudentMarks> marksList){
        List<ThirdYearMarks> marks = thirdYearService.getMarksByStatus();

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            thirdYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/null/year2")
    public List<StudentMarks> marksNullSecond(@RequestBody List<StudentMarks> marksList){
        List<SecondYearMarks> marks = secondYearService.getMarksByStatus();

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            secondYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/null/year1")
    public List<StudentMarks> marksNull(@RequestBody List<StudentMarks> marksList){
        List<FirstYearMarks> marks = firstYearService.getMarksByStatus();

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            firstYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/year3")
    public List<StudentMarks> marksThird(@RequestBody List<StudentMarks> marksList){
        List<ThirdYearMarks> marks = thirdYearService.getMarksByYear(Date.valueOf("2019-01-14"),null);

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            thirdYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/year2")
    public List<StudentMarks> marksSecond(@RequestBody List<StudentMarks> marksList){
        List<SecondYearMarks> marks = secondYearService.getMarksByYear(Date.valueOf("2019-01-15"),null);

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            secondYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/year1")
    public List<StudentMarks> marks(@RequestBody List<StudentMarks> marksList){
        List<FirstYearMarks> marks = firstYearService.getMarksByYear(Date.valueOf("2019-01-16"),null);

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            firstYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/2020/year1")
    public List<StudentMarks> marksFirst2020(@RequestBody List<StudentMarks> marksList){
        List<FirstYearMarks> marks = firstYearService.getMarksByYear(Date.valueOf("2020-01-16"),null);

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            firstYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("marks/2021/year1")
    public List<StudentMarks> marksFirst2021(@RequestBody List<StudentMarks> marksList){
        List<FirstYearMarks> marks = firstYearService.getMarksByYear(Date.valueOf("2021-01-16"),null);

        double test, assignment, exam, finalMark;
        DecimalFormat df = new DecimalFormat("0");
        df.setRoundingMode(RoundingMode.UP);
        for (int i = 0; i < marks.size(); i++) {

            // set ca2 mark
            if (marksList.get(i).getCa1() != 0){
                marks.get(i).setCa1(marksList.get(i).getCa1());
                marks.get(i).setCa1submitted(true);
            }

            //set ca2 mark
            if (marksList.get(i).getCa2() != 0){
                marks.get(i).setCa2(marksList.get(i).getCa2());
                marks.get(i).setCa2submitted(true);
            }

            // set assignment mark
            if (marksList.get(i).getAssignment() != 0){
                marks.get(i).setAssignment(marksList.get(i).getAssignment());
                marks.get(i).setAssignment_submitted(true);
            }

            // set exam mark
            if (marksList.get(i).getExam() != 0){
                marks.get(i).setExam(marksList.get(i).getExam());
                marks.get(i).setExam_submitted(true);
            }

            // set Status
            if (marks.get(i).getCa1() == 0 ||
                    marks.get(i).getCa2() == 0 ||
                    marks.get(i).getAssignment() == 0 ||
                    marks.get(i).getExam() == 0){
                marks.get(i).setStatus("At Risk");
            }
            else
                marks.get(i).setStatus("Safe");

            // set final mark
            {
                test = (marks.get(i).getCa1() + marks.get(i).getCa2())/200.00 * 20;
                assignment = marks.get(i).getAssignment() / 100.00 * 20;
                exam = marks.get(i).getExam() / 100.00 * 60;

                finalMark = Double.parseDouble(df.format(test + assignment + exam));

                if (finalMark >= 75)
                    marks.get(i).setRemarks("Passed With Distinction");
                else if (finalMark >= 50)
                    marks.get(i).setRemarks("Passed");
                else
                    marks.get(i).setRemarks("Failed");

                marks.get(i).setFinal_mark((int) finalMark);
                marks.get(i).setFinal_mark_available(true);
            }

            firstYearService.saveMarks(marks.get(i));
        }
        return marksList;
    }

    @PostMapping("address")
    public List<Address> addresses(@RequestBody List<Address> addressList){

        List<Student> students = studentService.getStudents();
        for (int i = 0; i < students.size(); i++) {
            students.get(i).setPost_code(addressList.get(i).getPost_code());
            students.get(i).setSuburb(addressList.get(i).getSuburb());
            students.get(i).setNationality(addressList.get(i).getNationality());
            students.get(i).setCity(addressList.get(i).getCity());
        }

        for (Student s : students)
            studentService.updateStudent(s);

        return addressList;
    }
}
