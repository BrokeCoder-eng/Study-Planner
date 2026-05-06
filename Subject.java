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

    public void printInsertionSort() {
        ArrayList<Test> tempTests = new ArrayList<>(this.tests);

        for (int i = 1; i < tempTests.size(); i++) {
            Test key = tempTests.get(i);
            int j = i - 1;

            while (j >= 0 && tempTests.get(j).getTestDate().isAfter(key.getTestDate())) {
                tempTests.set(j + 1, tempTests.get(j));
                j = j - 1; 
            }
            tempTests.set(j + 1, key);
        }

        for (Test t : tempTests) {
            System.out.println(t);
        }
    } 
    
    @Override
    public int compareTo(Subject other) {
        return Integer.compare(other.getSubjectPriority(), this.getSubjectPriority());
    }

}