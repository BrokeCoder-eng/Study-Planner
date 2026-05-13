import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

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

            if (option == 7) {
                break;
            } else {
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
                        } else {
                            boolean isAddingTest = true;

                            while (isAddingTest) {
                                try {
                                    int backOption = printSubjectMenu(subjects);
                                    System.out.println();

                                    System.out.print("Enter option: ");
                                    option = input.nextInt();
                                    System.out.println();

                                    if (option >= backOption) {
                                        break;
                                    }

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

                                } catch (Exception e) {
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
                    case 5:
                        subjects = loadSubjects("subjects.dat");
                        break;
                    case 6:
                        saveSubjects(subjects, "subjects.dat");
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
                5. Load Subjects
                6. Save Subjects
                7. Exit
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

    public static void saveSubjects(
            ArrayList<Subject> subjects,
            String fileName) {

        try (
                FileOutputStream fos = new FileOutputStream(fileName);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(subjects);

            System.out.println("Subjects saved successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Subject> loadSubjects(String fileName) {

        ArrayList<Subject> subjects = new ArrayList<>();

        try (
                FileInputStream fis = new FileInputStream(fileName);
                ObjectInputStream ois = new ObjectInputStream(fis)) {

            subjects = (ArrayList<Subject>) ois.readObject();

            System.out.println("Subjects loaded successfully.");

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return subjects;
    }
}
