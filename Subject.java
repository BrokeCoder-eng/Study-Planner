import java.util.ArrayList;
import java.util.PriorityQueue;

public class Subject implements Comparable<Subject>{
    String name;
    PriorityQueue<Test> tests;
    
    
    public Subject(String name) {
        this.name = name;
        tests = new PriorityQueue<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addTest(Test test) {
        this.tests.offer(test);
    }

    public Test getNextTest() {
        return this.tests.peek();
    }

    public boolean hasTests() {
        return !tests.isEmpty();
    }

    public PriorityQueue<Test> getTests() {
        return this.tests;
    }

    public void setTests(ArrayList<Test> tests) {
        for (Test t : tests) {
            this.tests.add(new Test(t.getName(), t.getWeight(), t.getTestDate()));
        }
    }
    
    public int getSubjectPriority() {
        Test nextTest = tests.peek();
        return (nextTest != null) ? nextTest.priority : 0;
    }
    
    @Override
    public int compareTo(Subject other) {
        return Integer.compare(other.getSubjectPriority(), this.getSubjectPriority());
    }

}