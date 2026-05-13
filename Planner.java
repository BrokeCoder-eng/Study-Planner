import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Planner {
    final static Scanner input = new Scanner(System.in);
    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static void main(String[] args) {
        ArrayList<Subject> subjects = new ArrayList<>();
        boolean isRunning = true;

        while (isRunning) {
            printMenu();

            System.out.print("Enter option: ");
            int option = input.nextInt();
            System.out.println();

            if (option == 5) {
                break;
            }
            else {
                switch (option) {
                    case 1:
                        System.out.print("Enter subject name to be added: ");
                        String name = input.next();
                        System.out.println();
                        subjects.add(new Subject(name));

                        break;
                    case 2:
                        if (subjects.isEmpty()) { 
                            System.out.println("Add a subject before trying to add a test."); 
                            System.out.println();
                        }
                        else {
                            boolean isAddingTest = true;

                            while (isAddingTest) {
                                try {
                                    int backOption = printSubjectMenu(subjects);
                                    System.out.println();

                                    System.out.print("Enter option: ");
                                    option = input.nextInt();
                                    System.out.println();

                                    if (option >= backOption) { break; }

                                
                                    System.out.print("Enter test name to be added: ");
                                    name = input.next();

                                    System.out.print("Enter test weight to be added: ");
                                    int weight = input.nextInt();

                                    System.out.print("Enter test date (yyyy/MM/dd): ");
                                    String dateInput = input.next();

                                    LocalDate date = LocalDate.parse(dateInput, formatter);

                                
                                    subjects.get(option - 1).addTest(new Test(name, weight, date));
                                    System.out.println("Test added.");
                                    System.out.println();
                                    
                                } catch(Exception e) {
                                    System.out.println("\nInvalid option. Test could not be added.\n");
                                    input.nextLine();
                                }
                            }
                        }

                        break;
                    case 3:
                        for (Subject subject : subjects) {
                            System.out.println(subject);
                            subject.printTests();
                            System.out.println();
                        }

                        break;
                    case 4:
                        WeeklyStudyPlanner weekPlan = new WeeklyStudyPlanner();
                        for (Subject subject : subjects) {
                            weekPlan.addSubject(subject);
                        }
                        weekPlan.createPlan(21);
                        weekPlan.printPlan();

                        break;
                }
            }
        }
    }

    public static void printMenu() {
        System.out.println("""
        1. Add Subject
        2. Add Test
        3. Print Tests
        4. Print Study Plan
        5. Exit
        """);
    }

    public static int printSubjectMenu(ArrayList<Subject> subjects) {
        int i = 1;
        for (Subject subject : subjects) {
            System.out.println(i + ". " + subject);
            i++;
        }
        System.out.println(i + ". Back");
        return i;
    }
}