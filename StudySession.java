public class StudySession {
    String subjectName;
    String testName;
    int hoursNeeded;
    int priority;
    long daysRemaining;

    public StudySession(String subjectName, String testName, int hoursNeeded, int priority, long daysRemaining) {
        this.subjectName = subjectName;
        this.testName = testName;
        this.hoursNeeded = hoursNeeded;
        this.priority = priority;
        this.daysRemaining = daysRemaining;
    }

    @Override
    public String toString() {
        return String.format("- %s - %s (%dh) [Priority: %d, %d days until test]", subjectName, testName, hoursNeeded, priority, daysRemaining);
    }
}