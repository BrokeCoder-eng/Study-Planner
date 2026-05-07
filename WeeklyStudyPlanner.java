import java.util.*;

public class WeeklyStudyPlanner {
    private PriorityQueue<Subject> subjects;
    private Map<String, List<StudySession>> weekPlan;
    private String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public WeeklyStudyPlanner() {
        this.subjects = new PriorityQueue<>();
        this.weekPlan = new HashMap<>();
        for (String day : days) {
            weekPlan.put(day, new ArrayList<>());
        }
    }

    private int calculateStudyHours(Test test) {
        int baseHours = 2;
        if (test.weight >= 50) {
            baseHours = 6;
        } 
        else if (test.weight >= 30) {
            baseHours = 4;
        } 
        else if (test.weight >= 20) {
            baseHours = 3;
        }
        
        long daysLeft = test.getDaysUntilTest();
        if (daysLeft <= 3) {
            baseHours = Math.min(baseHours, 3);
        } 
        else if (daysLeft <= 7) {
            baseHours = Math.min(baseHours, 4);
        }
        
        return baseHours;
    }

    private List<StudySession> generateStudySessions() {
        List<StudySession> sessions = new ArrayList<>();

        PriorityQueue<Subject> tempSubjects = new PriorityQueue<>(subjects);

        while (!tempSubjects.isEmpty()) {
            Subject subject = tempSubjects.poll();
            PriorityQueue<Test> tempTests = new PriorityQueue<>(subject.getTests());
            
            while (!tempTests.isEmpty()) {
                Test test = tempTests.poll();
                int hoursNeeded = calculateStudyHours(test);
                StudySession session = new StudySession(
                    subject.getName(),
                    test.getName(),
                    hoursNeeded,
                    test.getPriority(),
                    test.getDaysUntilTest()
                );
                
                sessions.add(session);
            }
        }
        sessions.sort((s1, s2) -> Integer.compare(s2.priority, s1.priority));
        return sessions;
    }

    private void distributeSessions(List<StudySession> sessions, int totalHoursPerWeek) {
        int hoursPerDay = totalHoursPerWeek / 7;
        int dayIndex = 0;
        
        for (StudySession session : sessions) {
            int remainingHours = session.hoursNeeded;
            while (remainingHours > 0) {
                String day = days[dayIndex % 7];
                int hoursForToday = Math.min(remainingHours, hoursPerDay);
                
                if (hoursForToday >= 1) {
                    StudySession daySession = new StudySession(
                        session.subjectName,
                        session.testName,
                        hoursForToday,
                        session.priority,
                        session.daysRemaining
                    );
                    
                    weekPlan.get(day).add(daySession);
                    remainingHours -= hoursForToday;
                }
                
                dayIndex++;
            }
        }
    }

    public void addSubject(Subject subject) {
        this.subjects.offer(subject);
    }
    
    public void createPlan(int totalHours) {
        for (String day : days) {
            weekPlan.get(day).clear();
        }

        List<StudySession> allSessions = generateStudySessions();

        distributeSessions(allSessions, totalHours);
    }

    public void printPlan() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║               WEEKLY STUDY PLAN                      ║");
        System.out.println("╚══════════════════════════════════════════════════════╝\n");
        
        for (String day : days) {
            List<StudySession> sessions = weekPlan.get(day);
            
            System.out.println("┌─ " + day.toUpperCase() + " " + "─".repeat(50 - day.length()));
            
            if (sessions.isEmpty()) {
                System.out.println("│  Rest day - No sessions scheduled");
            } else {
                int totalHours = 0;
                for (StudySession session : sessions) {
                    System.out.println("│  " + session);
                    totalHours += session.hoursNeeded;
                }
                System.out.println("│  Total: " + totalHours + " hours");
            }
            
            System.out.println("└" + "─".repeat(54));
            System.out.println();
        }
    }
}
