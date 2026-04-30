import java.util.*;

public class Studyplanner {
    PriorityQueue<Subject> subjects;
    HashMap<String, Subject> subjectMap;

    public Studyplanner() {
        this.subjects = new PriorityQueue<>();
        this.subjectMap = new HashMap<>();
    }

    public void addSubject(Subject subject) {
        this.subjects.offer(subject);
        this.subjectMap.put(subject.getName(), subject);
    }

    public void addTestToSubject(String subjectName, Test test) {
        Subject subject = subjectMap.get(subjectName);
        if (subject != null) {
            subjects.remove(subject);
            subject.addTest(test);
            subjects.offer(subject);
        }
    }

    public Test getNextTestOverall() {
        Subject topSubject = subjects.peek();
        return (topSubject != null) ? topSubject.getNextTest() : null;
    }
    
    public Subject getTopSubject() {
        return subjects.peek();
    }
    
    public boolean hasTests() {
        return !subjects.isEmpty() && subjects.peek().hasTests();
    }
    
    public void showAllTests() {
        System.out.println("\n=== All Tests by Priority ===");
        PriorityQueue<Subject> tempSubjects = new PriorityQueue<>(subjects);
        
        while (!tempSubjects.isEmpty()) {
            Subject subject = tempSubjects.poll();
            System.out.println("\n" + subject.name + ":");
            
            PriorityQueue<Test> tempTests = new PriorityQueue<>(subject.tests);
            while (!tempTests.isEmpty()) {
                System.out.println("  - " + tempTests.poll());
            }
        }
    }
}
