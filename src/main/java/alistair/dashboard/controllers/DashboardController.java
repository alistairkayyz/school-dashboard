package alistair.dashboard.controllers;

import alistair.dashboard.models.*;
import alistair.dashboard.services.*;
import alistair.dashboard.utilities.Session;
import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.RoundingMode;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

@Controller
public class DashboardController {

    private final FirstYearService firstYearService;
    private final SecondYearService secondYearService;
    private final ThirdYearService thirdYearService;
    private final StudentService studentService;
    private final StaffService staffService;
    private final SubjectService subjectService;

    public DashboardController(FirstYearService firstYearService,
                               SecondYearService secondYearService,
                               ThirdYearService thirdYearService,
                               StudentService studentService,
                               StaffService staffService,
                               SubjectService subjectService) {
        this.firstYearService = firstYearService;
        this.secondYearService = secondYearService;
        this.thirdYearService = thirdYearService;
        this.studentService = studentService;
        this.staffService = staffService;
        this.subjectService = subjectService;
    }

    @GetMapping("/")
    public String home(Model model) {

        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }

        header(model);
        barDoughnutChartData(model);
        lineChartData(model);
        radarChartData(model);
        topPerformers(model);

        return "redirect:/index";
    }

    @GetMapping("dashboard")
    public String dashboard(Model model) {

        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }

        header(model);
        barDoughnutChartData(model);
        lineChartData(model);
        radarChartData(model);
        topPerformers(model);

        return "index";
    }

    @GetMapping("school-dashboard")
    public String schoolDashboard(Model model) {

        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }

        schoolBarDoughnutChartData(model);
        schoolLineChartData(model);
        topPerformers(model);

        return "school-dashboard";
    }

    @GetMapping("student-dashboard")
    public String studentDashboard(Model model) {
        if (Session.getSessionUser() == null) {
            model.addAttribute("user", new User());
            return "login";
        }

        return "student-dashboard";
    }

    public void header(Model model){
        Date date1 = Date.valueOf("2021-01-16");
        Date date2 = Date.valueOf("2021-01-15");
        Date date3 = Date.valueOf("2021-01-14");

        Date lastDate1 = Date.valueOf("2020-01-16");
        Date lastDate2 = Date.valueOf("2020-01-15");
        Date lastDate3 = Date.valueOf("2020-01-14");

        double studentAvg2020 = (firstYearService.getAvgMarksByYear(lastDate1,null) +
                secondYearService.getAvgMarksByYear(lastDate2,null) +
                thirdYearService.getAvgMarksByYear(lastDate3,null)) / 3;

        double studentAvg2021 = (firstYearService.getAvgMarksByYear(date1,null) +
                secondYearService.getAvgMarksByYear(date2,null) +
                thirdYearService.getAvgMarksByYear(date3,null)) / 3;

        double studentPerformance = studentAvg2021 - studentAvg2020;

        double teacherAvg2020 = 0;
        double teacherAvg2021 = 0;

        List<Long> firstYearTeachers = staffService.getFirstYearTeachers();
        List<Long> secondYearTeachers = staffService.getSecondYearTeachers();
        List<Long> thirdYearTeachers = staffService.getThirdYearTeachers();

        // get 2020 performance for teachers
        for (long staff : firstYearTeachers){
            List<Integer> subjectCode = subjectService.getSubjectId(staff);

            teacherAvg2020 += firstYearService.getTeacherAvg(lastDate1, subjectCode.get(0), subjectCode.get(1));
        }

        for (long staff : secondYearTeachers){
            List<Integer> subjectCode = subjectService.getSubjectId(staff);

            teacherAvg2020 += secondYearService.getTeacherAvg(lastDate2, subjectCode.get(0), subjectCode.get(1));
        }

        for (long staff : thirdYearTeachers){
            List<Integer> subjectCode = subjectService.getSubjectId(staff);

            teacherAvg2020 += thirdYearService.getTeacherAvg(lastDate3, subjectCode.get(0), subjectCode.get(1));
        }

        // get 2021 performance for teachers
        for (long staff : firstYearTeachers){
            List<Integer> subjectCode = subjectService.getSubjectId(staff);

            teacherAvg2021 += firstYearService.getTeacherAvg(date1, subjectCode.get(0), subjectCode.get(1));
        }

        for (long staff : secondYearTeachers){
            List<Integer> subjectCode = subjectService.getSubjectId(staff);

            teacherAvg2021 += secondYearService.getTeacherAvg(date2, subjectCode.get(0), subjectCode.get(1));
        }

        for (Long staff : thirdYearTeachers){
            List<Integer> subjectCode = subjectService.getSubjectId(staff);

            teacherAvg2021 += thirdYearService.getTeacherAvg(date3, subjectCode.get(0), subjectCode.get(1));
        }

        double teacherPerformance = teacherAvg2021/6 - teacherAvg2020/6;

        System.out.println("\n\n " + teacherPerformance);
        System.out.println(studentPerformance);

        NumberFormat nf = NumberFormat.getNumberInstance();
        model.addAttribute("totalStudent", studentService.getTotalStudents());
        model.addAttribute("totalTeachers", staffService.getTotalTeachers());
        model.addAttribute("studentPerformance", nf.format(studentPerformance) + "%");
        model.addAttribute("teacherPerformance", nf.format(teacherPerformance) + "%");

        System.out.println(nf.format(studentPerformance));
        System.out.println(nf.format(teacherPerformance));

    }

    public void barDoughnutChartData(Model model) {
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
        for (FirstYearMarks student : firstYearList) {
            List<FirstYearMarks> marks = firstYearService.getByStudentNumberAndYear(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (FirstYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

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

        List<SecondYearMarks> secondYearList = secondYearService.getStudentAndAcademicYear();

        // get the number of students as per modules passed
        for (SecondYearMarks student : secondYearList) {
            List<SecondYearMarks> marks = secondYearService.getByStudentNumberAndYear(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (SecondYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

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

        List<ThirdYearMarks> thirdYearList = thirdYearService.getStudentAndAcademicYear();
        // get the number of students as per modules passed
        for (ThirdYearMarks student : thirdYearList) {
            List<ThirdYearMarks> marks = thirdYearService.getByStudentNumberAndYear(
                    student.getStudent().getId(), student.getYear());

            int count = 0;
            for (ThirdYearMarks m : marks) {
                if (m.getFinal_mark() >= 50)
                    count++;
            }

            DecimalFormat df = new DecimalFormat("0");
            df.setRoundingMode(RoundingMode.UP);

            int average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

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

        for (int i = 0; i < 3; i++) {
            pass100.add(count100[i]);
            pass75.add(count75[i]);
            pass50.add(count50[i]);
            pass25.add(count25[i]);
            fail.add(totalFail[i]);
        }

        // send bar chart data
        model.addAttribute("pass100", pass100);
        model.addAttribute("pass75", pass75);
        model.addAttribute("pass50", pass50);
        model.addAttribute("pass25", pass25);
        model.addAttribute("fail", fail);

        // doughnut chart data
        JSONArray doughnutData = new JSONArray();

        doughnutData.add(firstYearList.size());
        doughnutData.add(secondYearList.size());
        doughnutData.add(thirdYearList.size());

        model.addAttribute("doughnutData", doughnutData);
    }

    public void lineChartData(Model model) {
        // send line chart data
        List<FirstYearMarks> academicYearsFirstYear = firstYearService.getAcademicYears();
        List<SecondYearMarks> academicYearsSecondYear = secondYearService.getAcademicYears();
        List<ThirdYearMarks> academicYearsThirdYear = thirdYearService.getAcademicYears();

        List<Double> firstYearAverageMarks = new ArrayList<>();
        List<Double> secondYearAverageMarks = new ArrayList<>();
        List<Double> thirdYearAverageMarks = new ArrayList<>();

        List<String> lineLabels = new ArrayList<>();

        for (FirstYearMarks year : academicYearsFirstYear) {

            lineLabels.add(year.getYear().toString().substring(0, 4));

            // get average and store it
            firstYearAverageMarks.add(firstYearService.getAvgMarksByYear(year.getYear(), null));
        }

        for (SecondYearMarks year : academicYearsSecondYear) {

            // get average and store it
            secondYearAverageMarks.add(secondYearService.getAvgMarksByYear(year.getYear(), null));
        }

        for (ThirdYearMarks year : academicYearsThirdYear) {

            // get average and store it
            thirdYearAverageMarks.add(thirdYearService.getAvgMarksByYear(year.getYear(), null));
        }

        JSONArray lineChartLabels = new JSONArray();
        JSONArray lineFirstYearData = new JSONArray();
        JSONArray lineSecondYearData = new JSONArray();
        JSONArray lineThirdYearData = new JSONArray();

        for (int i = 0; i < lineLabels.size(); i++) {
            lineChartLabels.add(lineLabels.get(i));
            lineFirstYearData.add(firstYearAverageMarks.get(i));
            lineSecondYearData.add(secondYearAverageMarks.get(i));
            lineThirdYearData.add(thirdYearAverageMarks.get(i));
        }

        model.addAttribute("lineLabels", lineChartLabels);
        model.addAttribute("lineData1", lineFirstYearData);
        model.addAttribute("lineData2", lineSecondYearData);
        model.addAttribute("lineData3", lineThirdYearData);
    }

    public void radarChartData(Model model) {
        JSONArray firstYearRadarData = new JSONArray();

        firstYearRadarData.add(firstYearService.getAvgSemesterMark());
        firstYearRadarData.add(firstYearService.getAvgExamMark());
        firstYearRadarData.add(firstYearService.getAvgAssignmentMark());
        firstYearRadarData.add(firstYearService.getAvgCa2Mark());
        firstYearRadarData.add(firstYearService.getAvgCa1Mark());

        JSONArray secondYearRadarData = new JSONArray();

        secondYearRadarData.add(secondYearService.getAvgSemesterMark());
        secondYearRadarData.add(secondYearService.getAvgExamMark());
        secondYearRadarData.add(secondYearService.getAvgAssignmentMark());
        secondYearRadarData.add(secondYearService.getAvgCa2Mark());
        secondYearRadarData.add(secondYearService.getAvgCa1Mark());

        JSONArray thirdYearRadarData = new JSONArray();

        thirdYearRadarData.add(thirdYearService.getAvgSemesterMark());
        thirdYearRadarData.add(thirdYearService.getAvgExamMark());
        thirdYearRadarData.add(thirdYearService.getAvgAssignmentMark());
        thirdYearRadarData.add(thirdYearService.getAvgCa2Mark());
        thirdYearRadarData.add(thirdYearService.getAvgCa1Mark());

        model.addAttribute("firstYearRadarData", firstYearRadarData);
        model.addAttribute("secondYearRadarData", secondYearRadarData);
        model.addAttribute("thirdYearRadarData", thirdYearRadarData);
    }

    public void topPerformers(Model model) {
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
                    entry.getKey().getFirstname() + " " + entry.getKey().getSurname(),
                    entry.getKey().getGender(),
                    entry.getKey().getEmail(),
                    "BSc Information Technology",
                    year,
                    entry.getValue()
            ));

            count++;
            if (count == 10)
                break;

        }

        model.addAttribute("topPerformers", topPerformers);
    }

    public void schoolLineChartData(Model model) {
        // send line chart data
        List<FirstYearMarks> academicYearsFirstYear = firstYearService.getAcademicYears();
        List<SecondYearMarks> academicYearsSecondYear = secondYearService.getAcademicYears();
        List<ThirdYearMarks> academicYearsThirdYear = thirdYearService.getAcademicYears();

        List<Double> firstYearAverageMarks = new ArrayList<>();
        List<Double> secondYearAverageMarks = new ArrayList<>();
        List<Double> thirdYearAverageMarks = new ArrayList<>();

        List<String> lineLabels = new ArrayList<>();

        for (FirstYearMarks year : academicYearsFirstYear) {

            lineLabels.add(year.getYear().toString().substring(0, 4));

            // get average and store it
            firstYearAverageMarks.add(firstYearService.getAvgMarksByYear(year.getYear(), null));
        }

        for (SecondYearMarks year : academicYearsSecondYear) {

            // get average and store it
            secondYearAverageMarks.add(secondYearService.getAvgMarksByYear(year.getYear(), null));
        }

        for (ThirdYearMarks year : academicYearsThirdYear) {

            // get average and store it
            thirdYearAverageMarks.add(thirdYearService.getAvgMarksByYear(year.getYear(), null));
        }

        JSONArray lineChartLabels = new JSONArray();
        JSONArray lineFirstYearData = new JSONArray();
        JSONArray lineSecondYearData = new JSONArray();
        JSONArray lineThirdYearData = new JSONArray();

        for (int i = 0; i < lineLabels.size(); i++) {
            lineChartLabels.add(lineLabels.get(i));
            lineFirstYearData.add(firstYearAverageMarks.get(i));
            lineSecondYearData.add(secondYearAverageMarks.get(i));
            lineThirdYearData.add(thirdYearAverageMarks.get(i));
        }

        model.addAttribute("lineLabels", lineChartLabels);
        model.addAttribute("lineData1", lineFirstYearData);
        model.addAttribute("lineData2", lineSecondYearData);
        model.addAttribute("lineData3", lineThirdYearData);
    }

    public void schoolBarDoughnutChartData(Model model) {
        JSONArray pass100 = new JSONArray();
        JSONArray pass50 = new JSONArray();
        JSONArray fail = new JSONArray();

        /*
         * these arrays will store only three values
         * index = 0 will store first year count
         * index = 1 will store second year count
         * index = 2 will store third year count
         * for all the cases
         */
        int[] count100 = new int[6];
        int[] count50 = new int[6];
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

            int average;
            if (marks.size() > 0) {
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

                switch (average) {
                    /*
                     * this applies in a case where a student is carrying some modules from the previous year(s)
                     */
                    case 100 -> count100[0] += 1; // increment if student passed all modules
                    case 50 -> count50[0] += 1; // increment if student passed 1 modules
                    case 0 -> totalFail[0] += 1; // increment if student failed all modules
                }
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

            int average;
            if (marks.size() > 0) {
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

                switch (average) {
                    /*
                     * this applies in a case where a student is carrying some modules from the previous year(s)
                     */
                    case 100 -> count100[1] += 1; // increment if student passed all modules
                    case 50 -> count50[1] += 1; // increment if student passed 1 modules
                    case 0 -> totalFail[1] += 1; // increment if student failed all modules
                }
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

            int average;
            if (marks.size() > 0) {
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

                switch (average) {
                    /*
                     * this applies in a case where a student is carrying some modules from the previous year(s)
                     */
                    case 100 -> count100[2] += 1; // increment if student passed all modules
                    case 50 -> count50[2] += 1; // increment if student passed 1 modules
                    case 0 -> totalFail[2] += 1; // increment if student failed all modules
                }
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

            int average;
            if (marks.size() > 0) {
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

                switch (average) {
                    /*
                     * this applies in a case where a student is carrying some modules from the previous year(s)
                     */
                    case 100 -> count100[3] += 1; // increment if student passed all modules
                    case 50 -> count50[3] += 1; // increment if student passed 1 modules
                    case 0 -> totalFail[3] += 1; // increment if student failed all modules
                }
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

            int average;
            if (marks.size() > 0) {
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

                switch (average) {
                    /*
                     * this applies in a case where a student is carrying some modules from the previous year(s)
                     */
                    case 100 -> count100[4] += 1; // increment if student passed all modules
                    case 50 -> count50[4] += 1; // increment if student passed 1 modules
                    case 0 -> totalFail[4] += 1; // increment if student failed all modules
                }
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

            int average;
            if (marks.size() > 0) {
                average = Integer.parseInt(df.format(((double) count / marks.size()) * 100.00));

                switch (average) {
                    /*
                     * this applies in a case where a student is carrying some modules from the previous year(s)
                     */
                    case 100 -> count100[5] += 1; // increment if student passed all modules
                    case 50 -> count50[5] += 1; // increment if student passed 1 modules
                    case 0 -> totalFail[5] += 1; // increment if student failed all modules
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            pass100.add(count100[i]);
            pass50.add(count50[i]);
            fail.add(totalFail[i]);
        }

        // send bar chart data
        model.addAttribute("pass100", pass100);
        model.addAttribute("pass50", pass50);
        model.addAttribute("fail", fail);

        // doughnut chart data
        JSONArray doughnutData = new JSONArray();

        doughnutData.add(firstYearService.getTotalStudentsForSemOne());
        doughnutData.add(firstYearService.getTotalStudentsForSemTwo());
        doughnutData.add(secondYearService.getTotalStudentsForSemOne());
        doughnutData.add(secondYearService.getTotalStudentsForSemTwo());
        doughnutData.add(thirdYearService.getTotalStudentsForSemOne());
        doughnutData.add(thirdYearService.getTotalStudentsForSemTwo());

        model.addAttribute("doughnutData", doughnutData);
    }

}
