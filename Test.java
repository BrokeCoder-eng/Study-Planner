import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test implements Comparable<Test> {
    String name;
    int weight;
    LocalDate testDate;
    int priority;

    public Test(String name, int weight, LocalDate testDate) {
        this.name = name;
        this.weight = weight;
        this.testDate = testDate;
        this.priority = calculatePriority();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getDaysUntilTest() {
        return ChronoUnit.DAYS.between(LocalDate.now(), testDate);
    }

    public LocalDate getTestDate() {
        return this.testDate;
    }

    public void setTestDate(LocalDate testDate) {
        this.testDate = testDate;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPrioriteit(int priority) {
        this.priority = priority;
    }

    public boolean isFinished() {
        return (ChronoUnit.DAYS.between( LocalDate.now(), testDate) < 1);
    }

    private int calculatePriority() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), testDate);
        
        double dateFactor = 1.0 / Math.max(daysLeft, 1);
        double weightFactor = this.weight / 100.0;

        return (int)(dateFactor * weightFactor * 1000);
    }

    @Override
    public int compareTo(Test other) {
        return Integer.compare(other.getPriority(), this.priority);
    }

    @Override
    public String toString() {
        return ("Test name: " + this.name + ", Test date: " + this.testDate + ", Priority: " + this.priority);
    }
}
