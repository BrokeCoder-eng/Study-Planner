import java.time.LocalDate;

public class Demo {
    public static void main(String[] args) {
        // Create the study planner
        Studyplanner planner = new Studyplanner();
        
        // Create Mathematics subject
        Subject math = new Subject("Mathematics");
        math.addTest(new Test("Test 1", 15, LocalDate.now().plusDays(5)));
        math.addTest(new Test("Test 2", 15, LocalDate.now().plusDays(15)));
        math.addTest(new Test("Final Exam", 70, LocalDate.now().plusDays(40)));
        
        // Create Physics subject
        Subject physics = new Subject("Physics");
        physics.addTest(new Test("Quiz 1", 10, LocalDate.now().plusDays(3)));
        physics.addTest(new Test("Midterm", 35, LocalDate.now().plusDays(12)));
        physics.addTest(new Test("Final Exam", 55, LocalDate.now().plusDays(42)));
        
        // Create English subject
        Subject english = new Subject("English");
        english.addTest(new Test("Essay 1", 40, LocalDate.now().plusDays(7)));
        english.addTest(new Test("Final Exam", 60, LocalDate.now().plusDays(38)));
        
        // Create Computer Science subject
        Subject cs = new Subject("Computer Science");
        cs.addTest(new Test("Project 1", 50, LocalDate.now().plusDays(10)));
        cs.addTest(new Test("Final Exam", 50, LocalDate.now().plusDays(45)));
        
        // Add subjects to planner
        planner.addSubject(math);
        planner.addSubject(physics);
        planner.addSubject(english);
        planner.addSubject(cs);
        
        // Create weekly study plan
        WeeklyStudyPlanner weeklyPlanner = new WeeklyStudyPlanner();
        weeklyPlanner.createPlan(planner, 21);  // 21 hours per week (3 hours/day)
        
        // Print the plan
        weeklyPlanner.printPlan();
    }
}