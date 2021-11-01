package alistair.dashboard.bootstrap;

import alistair.dashboard.models.*;
import alistair.dashboard.services.*;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.*;

@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    private final SubjectService subjectService;
    private final ThirdYearService thirdYearService;
    private final StudentService studentService;
    private final SecondYearService secondYearService;
    private final FirstYearService firstYearService;

    public MyRunner(SubjectService subjectService,
                    ThirdYearService thirdYearService,
                    StudentService studentService,
                    SecondYearService secondYearService,
                    FirstYearService firstYearService) {
        this.subjectService = subjectService;
        this.thirdYearService = thirdYearService;
        this.studentService = studentService;
        this.secondYearService = secondYearService;
        this.firstYearService = firstYearService;
    }

    @Override
    public void run(String... args) throws Exception {

    }

    public void schoolBarDoughnutChartData() {
        JSONArray pass100 = new JSONArray();
        JSONArray pass75 = new JSONArray();
        JSONArray pass50 = new JSONArray();
        JSONArray pass25 = new JSONArray();
        JSONArray fail = new JSONArray();

        /*
         * these arrays will store only three values
         * index = 0 will store first year count
         * index = 1 will store second year count
         * index = 2 will store third year count
         * for all the cases
         */
        int[] count100 = new int[6];
        int[] count75 = new int[6];
        int[] count50 = new int[6];
        int[] count25 = new int[6];
        int[] totalFail = new int[6];

        List<FirstYearMarks> firstYearList = firstYearService.getStudentAndAcademicYear();

        // get the number of students for semester 1 as per modules passed
        for (FirstYearMarks student : firstYearList) {
            List<FirstYearMarks> marks = firstYearService.getMarksForSemOne(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (FirstYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = 0;
            if (marks.size() > 0)
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average) {
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[0] += 1; // increment if student passed all modules
                case 75 -> count75[0] += 1; // increment if student passed 3 modules
                case 50 -> count50[0] += 1; // increment if student passed 2 modules
                case 25 -> count25[0] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[0] += 1; // increment if student failed all modules
            }
        }

        // get the number of students for semester 1 as per modules passed
        for (FirstYearMarks student : firstYearList) {
            List<FirstYearMarks> marks = firstYearService.getMarksForSemTwo(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (FirstYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = 0;
            if (marks.size() > 0)
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average) {
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[1] += 1; // increment if student passed all modules
                case 75 -> count75[1] += 1; // increment if student passed 3 modules
                case 50 -> count50[1] += 1; // increment if student passed 2 modules
                case 25 -> count25[1] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[1] += 1; // increment if student failed all modules
            }
        }

        List<SecondYearMarks> secondYearList = secondYearService.getStudentAndAcademicYear();

        // get the number of students as per modules passed
        for (SecondYearMarks student : secondYearList) {
            List<SecondYearMarks> marks = secondYearService.getMarksForSemOne(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (SecondYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = 0;
            if (marks.size() > 0)
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average) {
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[2] += 1; // increment if student passed all modules
                case 75 -> count75[2] += 1; // increment if student passed 3 modules
                case 50 -> count50[2] += 1; // increment if student passed 2 modules
                case 25 -> count25[2] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[2] += 1; // increment if student failed all modules
            }
        }

        // get the number of students as per modules passed
        for (SecondYearMarks student : secondYearList) {
            List<SecondYearMarks> marks = secondYearService.getMarksForSemTwo(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (SecondYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = 0;
            if (marks.size() > 0)
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average) {
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[3] += 1; // increment if student passed all modules
                case 75 -> count75[3] += 1; // increment if student passed 3 modules
                case 50 -> count50[3] += 1; // increment if student passed 2 modules
                case 25 -> count25[3] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[3] += 1; // increment if student failed all modules
            }
        }

        List<ThirdYearMarks> thirdYearList = thirdYearService.getStudentAndAcademicYear();
        // get the number of students as per modules passed
        for (ThirdYearMarks student : thirdYearList) {
            List<ThirdYearMarks> marks = thirdYearService.getMarksForSemOne(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (ThirdYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = 0;
            if (marks.size() > 0)
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average) {
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[4] += 1; // increment if student passed all modules
                case 75 -> count75[4] += 1; // increment if student passed 3 modules
                case 50 -> count50[4] += 1; // increment if student passed 2 modules
                case 25 -> count25[4] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[4] += 1; // increment if student failed all modules
            }
        }

        // get the number of students as per modules passed
        for (ThirdYearMarks student : thirdYearList) {
            List<ThirdYearMarks> marks = thirdYearService.getMarksForSemTwo(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (ThirdYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = 0;
            if (marks.size() > 0)
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average) {
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[5] += 1; // increment if student passed all modules
                case 75 -> count75[5] += 1; // increment if student passed 3 modules
                case 50 -> count50[5] += 1; // increment if student passed 2 modules
                case 25 -> count25[5] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[5] += 1; // increment if student failed all modules
            }
        }

        for (int i = 0; i < 6; i++) {
            pass100.add(count100[i]);
            pass75.add(count75[i]);
            pass50.add(count50[i]);
            pass25.add(count25[i]);
            fail.add(totalFail[i]);
        }



//        // send bar chart data
//        model.addAttribute("pass100", pass100);
//        model.addAttribute("pass75", pass75);
//        model.addAttribute("pass50", pass50);
//        model.addAttribute("pass25", pass25);
//        model.addAttribute("fail", fail);
//
//        // doughnut chart data
//        JSONArray doughnutData = new JSONArray();
//
//        doughnutData.add(firstYearList.size());
//        doughnutData.add(secondYearList.size());
//        doughnutData.add(thirdYearList.size());
//
//        model.addAttribute("doughnutData", doughnutData);
    }

    public void topPerformers() {
        Date date1 = Date.valueOf("2021-01-16");
        Date date2 = Date.valueOf("2021-01-15");
        Date date3 = Date.valueOf("2021-01-14");

        Date lastDate1 = Date.valueOf("2020-01-16");
        Date lastDate2 = Date.valueOf("2020-01-15");
        Date lastDate3 = Date.valueOf("2020-01-14");

        Map<Student, Double> studentMap = new HashMap<>();

        List<Student> studentsYear1 = firstYearService.getStudentsByYear(date1);
        for (Student student : studentsYear1) {
            if (firstYearService.getFailedSubject(lastDate1, student.getId()) == null)
                studentMap.put(student, firstYearService.getStudentAvgFinalMark(date1, student.getId()));
        }

        List<Student> studentsYear2 = secondYearService.getStudentsByYear(date2);
        for (Student student : studentsYear2) {
            if (secondYearService.getFailedSubject(lastDate2, student.getId()) == null)
                studentMap.put(student, secondYearService.getStudentAvgFinalMark(date2, student.getId()));
        }

        List<Student> studentsYear3 = thirdYearService.getStudentsByYear(date3);
        for (Student student : studentsYear3) {
            if (thirdYearService.getFailedSubject(lastDate3, student.getId()) == null)
                studentMap.put(student, thirdYearService.getStudentAvgFinalMark(date3, student.getId()));
        }


        // sort the list of performers
        List<Map.Entry<Student, Double>> list = new LinkedList<>(studentMap.entrySet());
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        // set a sorted map of performers
        Map<Student, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Student, Double> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        /*
        * set top 10 performers
        */
        List<TopPerformers> topPerformers = new LinkedList<>();
        int count = 0;
        for (Map.Entry<Student, Double> entry : sortedMap.entrySet()) {
            int year = 0;
            if (studentsYear1.stream().anyMatch(student -> student.getId() == entry.getKey().getId()))
                year = 1;

            if (studentsYear2.stream().anyMatch(student -> student.getId() == entry.getKey().getId()))
                year = 2;

            if (studentsYear3.stream().anyMatch(student -> student.getId() == entry.getKey().getId()))
                year = 3;

            topPerformers.add(new TopPerformers(
                    entry.getKey().getId(),
                    entry.getKey().getFirstname() + entry.getKey().getSurname(),
                    entry.getKey().getGender(),
                    entry.getKey().getEmail(),
                    "Bachelor of Science in Information Technology",
                    year,
                    entry.getValue()
            ));

            count++;
            if (count == 10)
                break;

        }
    }

    public void barChartData(){
        JSONArray pass100 = new JSONArray();
        JSONArray pass75 = new JSONArray();
        JSONArray pass50 = new JSONArray();
        JSONArray pass25 = new JSONArray();
        JSONArray fail = new JSONArray();

        /*
         * these arrays will store only three values
         * index = 0 will store first year count
         * index = 1 will store second year count
         * index = 2 will store third year count
         * for all the cases
         */
        int[] count100 = new int[3];
        int[] count75 = new int[3];
        int[] count50 = new int[3];
        int[] count25 = new int[3];
        int[] totalFail = new int[3];

        List<FirstYearMarks> firstYearList = firstYearService.getStudentAndAcademicYear();

        // get the number of students as per modules passed
        for (FirstYearMarks student : firstYearList){
            List<FirstYearMarks> marks = firstYearService.getByStudentNumberAndYear(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (FirstYearMarks m : marks){
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            System.out.println("Count: " + count);
            System.out.println("Size: " + marks.size());
            System.out.println("Average: " + average);

            switch (average){
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[0] += 1; // increment if student passed all modules
                case 75 -> count75[0] += 1; // increment if student passed 3 modules
                case 50 -> count50[0] += 1; // increment if student passed 2 modules
                case 25 -> count25[0] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[0] += 1; // increment if student failed all modules
            }
        }

        List<SecondYearMarks> secondYearList = secondYearService.getStudentAndAcademicYear();

        // get the number of students as per modules passed
        for (SecondYearMarks student : secondYearList){
            List<SecondYearMarks> marks = secondYearService.getByStudentNumberAndYear(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (SecondYearMarks m : marks){
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average){
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[1] += 1; // increment if student passed all modules
                case 75 -> count75[1] += 1; // increment if student passed 3 modules
                case 50 -> count50[1] += 1; // increment if student passed 2 modules
                case 25 -> count25[1] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[1] += 1; // increment if student failed all modules
            }
        }

        List<ThirdYearMarks> thirdYearList = thirdYearService.getStudentAndAcademicYear();
        // get the number of students as per modules passed
        for (ThirdYearMarks student : thirdYearList){
            List<ThirdYearMarks> marks = thirdYearService.getByStudentNumberAndYear(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (ThirdYearMarks m : marks){
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

            switch (average){
                /*
                 * this applies in a case where a student is carrying some modules from the previous year(s)
                 */
                case 100 -> count100[2] += 1; // increment if student passed all modules
                case 75 -> count75[2] += 1; // increment if student passed 3 modules
                case 50 -> count50[2] += 1; // increment if student passed 2 modules
                case 25 -> count25[2] += 1; // increment if student passed 1 modules
                case 0 -> totalFail[2] += 1; // increment if student failed all modules
            }
        }

        for (int i = 0; i < 3; i++) {
            pass100.add(count100[i]);
            pass75.add(count75[i]);
            pass50.add(count50[i]);
            pass25.add(count25[i]);
            fail.add(totalFail[i]);
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(" " + pass100.get(i));
            System.out.println(" " + pass75.get(i));
            System.out.println(" " + pass50.get(i));
            System.out.println(" " + pass25.get(i));
            System.out.println(" " + fail.get(i));
            System.out.println("\n\n");
        }


    }

    public void enrollReturning2020(){
        List<FirstYearMarks> firstYearMarks = firstYearService.getFailedStudents(50,Date.valueOf("2019-01-16"));

        List<SecondYearMarks> secondYearMarks = secondYearService.getFailedStudents(50,Date.valueOf("2019-01-15"));

        List<ThirdYearMarks> thirdYearMarks = thirdYearService.getFailedStudents(50,Date.valueOf("2019-01-14"));

        long marksId;
        for (FirstYearMarks marks : firstYearMarks){
            if (firstYearService.getLastId() == null || firstYearService.getLastId().isEmpty() )
                marksId = 1;
            else
                marksId = Long.parseLong(firstYearService.getLastId()) + 1;

            marks.setId(marksId);

            marks.setCa1(0);
            marks.setCa1submitted(false);

            marks.setCa2(0);
            marks.setCa2submitted(false);

            marks.setAssignment(0);
            marks.setAssignment_submitted(false);

            marks.setExam(0);
            marks.setExam_submitted(false);

            marks.setFinal_mark(0);
            marks.setFinal_mark_available(false);

            marks.setStatus(null);
            marks.setRemarks(null);

            marks.setYear(Date.valueOf("2020-01-16"));
            firstYearService.enrollStudents(marks);
        }

        for (SecondYearMarks marks : secondYearMarks){
            if (secondYearService.getLastId() == null || secondYearService.getLastId().isEmpty() )
                marksId = 1;
            else
                marksId = Long.parseLong(secondYearService.getLastId()) + 1;

            marks.setId(marksId);

            marks.setCa1(0);
            marks.setCa1submitted(false);

            marks.setCa2(0);
            marks.setCa2submitted(false);

            marks.setAssignment(0);
            marks.setAssignment_submitted(false);

            marks.setExam(0);
            marks.setExam_submitted(false);

            marks.setFinal_mark(0);
            marks.setFinal_mark_available(false);

            marks.setStatus(null);
            marks.setRemarks(null);

            marks.setYear(Date.valueOf("2020-01-15"));
            secondYearService.enrollStudents(marks);
        }

        for (ThirdYearMarks marks : thirdYearMarks){
            if (thirdYearService.getLastId() == null || thirdYearService.getLastId().isEmpty() )
                marksId = 1;
            else
                marksId = Long.parseLong(thirdYearService.getLastId()) + 1;

            marks.setId(marksId);

            marks.setCa1(0);
            marks.setCa1submitted(false);

            marks.setCa2(0);
            marks.setCa2submitted(false);

            marks.setAssignment(0);
            marks.setAssignment_submitted(false);

            marks.setExam(0);
            marks.setExam_submitted(false);

            marks.setFinal_mark(0);
            marks.setFinal_mark_available(false);

            marks.setStatus(null);
            marks.setRemarks(null);

            marks.setYear(Date.valueOf("2020-01-14"));
            thirdYearService.enrollStudents(marks);
        }
    }

    public void enrollReturning2021(){
        List<FirstYearMarks> firstYearMarks = firstYearService.getFailedStudents(50,Date.valueOf("2020-01-16"));

        List<SecondYearMarks> secondYearMarks = secondYearService.getFailedStudents(50,Date.valueOf("2020-01-15"));

        List<ThirdYearMarks> thirdYearMarks = thirdYearService.getFailedStudents(50,Date.valueOf("2020-01-14"));

        long marksId;
        for (FirstYearMarks marks : firstYearMarks){
            if (firstYearService.getLastId() == null || firstYearService.getLastId().isEmpty() )
                marksId = 1;
            else
                marksId = Long.parseLong(firstYearService.getLastId()) + 1;

            marks.setId(marksId);

            marks.setCa1(0);
            marks.setCa1submitted(false);

            marks.setCa2(0);
            marks.setCa2submitted(false);

            marks.setAssignment(0);
            marks.setAssignment_submitted(false);

            marks.setExam(0);
            marks.setExam_submitted(false);

            marks.setFinal_mark(0);
            marks.setFinal_mark_available(false);

            marks.setStatus(null);
            marks.setRemarks(null);

            marks.setYear(Date.valueOf("2021-01-16"));
            firstYearService.enrollStudents(marks);
        }

        for (SecondYearMarks marks : secondYearMarks){
            if (secondYearService.getLastId() == null || secondYearService.getLastId().isEmpty() )
                marksId = 1;
            else
                marksId = Long.parseLong(secondYearService.getLastId()) + 1;

            marks.setId(marksId);

            marks.setCa1(0);
            marks.setCa1submitted(false);

            marks.setCa2(0);
            marks.setCa2submitted(false);

            marks.setAssignment(0);
            marks.setAssignment_submitted(false);

            marks.setExam(0);
            marks.setExam_submitted(false);

            marks.setFinal_mark(0);
            marks.setFinal_mark_available(false);

            marks.setStatus(null);
            marks.setRemarks(null);

            marks.setYear(Date.valueOf("2021-01-15"));
            secondYearService.enrollStudents(marks);
        }

        for (ThirdYearMarks marks : thirdYearMarks){
            if (thirdYearService.getLastId() == null || thirdYearService.getLastId().isEmpty() )
                marksId = 1;
            else
                marksId = Long.parseLong(thirdYearService.getLastId()) + 1;

            marks.setId(marksId);

            marks.setCa1(0);
            marks.setCa1submitted(false);

            marks.setCa2(0);
            marks.setCa2submitted(false);

            marks.setAssignment(0);
            marks.setAssignment_submitted(false);

            marks.setExam(0);
            marks.setExam_submitted(false);

            marks.setFinal_mark(0);
            marks.setFinal_mark_available(false);

            marks.setStatus(null);
            marks.setRemarks(null);

            marks.setYear(Date.valueOf("2021-01-14"));
            thirdYearService.enrollStudents(marks);
        }
    }

    public void enroll2021Second(){
        SecondYearMarks marks = new SecondYearMarks();

        List<Student> students = studentService.getByRange(201900001,201900030);
        long marksId;
        for (Student student : students){
            for (int i = 5; i <= 8; i++) {
                if (secondYearService.getLastId() == null || secondYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(secondYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2021-01-15"));
                secondYearService.enrollStudents(marks);
            }
        }
    }

    public void enroll2021Third(){
        ThirdYearMarks marks = new ThirdYearMarks();

        List<Student> students = studentService.getByRange(201900031,201900052);

        long marksId;
        for (Student student : students){
            for (int i = 9; i <= 12; i++) {
                if (thirdYearService.getLastId() == null || thirdYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(thirdYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2021-01-14"));
                thirdYearService.enrollStudents(marks);
            }
        }
    }

    public void enroll2020Second(){
        SecondYearMarks marks = new SecondYearMarks();

        List<Student> students = studentService.getByRange(201900031,201900052);
        long marksId;
        for (Student student : students){
            for (int i = 5; i <= 8; i++) {
                if (secondYearService.getLastId() == null || secondYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(secondYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2020-01-15"));
                secondYearService.enrollStudents(marks);
            }
        }
    }

    public void enroll2020Third(){
        ThirdYearMarks marks = new ThirdYearMarks();

        List<Student> students = studentService.getByRange(201900013,201900030);

        long marksId;
        for (Student student : students){
            for (int i = 9; i <= 12; i++) {
                if (thirdYearService.getLastId() == null || thirdYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(thirdYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2020-01-14"));
                thirdYearService.enrollStudents(marks);
            }
        }
    }

    public void enrollFirstYears(){
        FirstYearMarks marks = new FirstYearMarks();

        List<Student> students = studentService.getByRange(202100001,202100035);
        long marksId;
        for (Student student : students){
            for (int i = 1; i <= 4; i++) {
                if (firstYearService.getLastId() == null || firstYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(firstYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2021-01-16"));
                firstYearService.enrollStudents(marks);
            }
        }
    }

    public void enrollSecondYears(){
        SecondYearMarks marks = new SecondYearMarks();

        List<Student> students = studentService.getByRange(201900013,201900030);
        long marksId;
        for (Student student : students){
            for (int i = 5; i <= 8; i++) {
                if (secondYearService.getLastId() == null || secondYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(secondYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2019-01-15"));
                secondYearService.enrollStudents(marks);
            }
        }
    }

    public void enrollThirdYears(){
        ThirdYearMarks marks = new ThirdYearMarks();

        List<Student> students = studentService.getByRange(201900001,201900012);
        long marksId;
        for (Student student : students){
            for (int i = 9; i <= 12; i++) {
                if (thirdYearService.getLastId() == null || thirdYearService.getLastId().isEmpty() )
                    marksId = 1;
                else
                    marksId = Long.parseLong(thirdYearService.getLastId()) + 1;

                marks.setId(marksId);
                marks.setStudent(student);
                marks.setSubject(subjectService.getSubject(i).orElse(null));
                marks.setYear(Date.valueOf("2019-01-14"));
                thirdYearService.enrollStudents(marks);
            }
        }
    }
}
