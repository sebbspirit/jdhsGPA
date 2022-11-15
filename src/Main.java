import java.util.Hashtable;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Hashtable<String, Integer> grade_letter = new Hashtable<>();
        Hashtable<String, Integer> course_type = new Hashtable<>();
        String[] grade = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"}; // string array to for loop the dict put to save lines
        String[] type = {"AP", "H", "CP"};
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        double[][] quality = {{5.00, 4.67, 4.33}, {4.67, 4.33, 4.00}, {4.33, 4.00, 3.67}, {4.00, 3.67, 3.33}, {3.67, 3.33, 3.00}, {3.33, 3.00, 2.67}, {3.00, 2.67, 2.33}, {2.67, 2.33, 2.00}, {2.33, 2.00, 1.67}, {1.67, 1.33, 1.00}, {0.00, 0.00, 0.00}
        }; // nice table of quality point values
        for (int i = 0; i < grade.length; i++) {
            grade_letter.put(grade[i], i);
            // System.out.println("\nValue at key = " + grade[i] + ": " + grade_letter.get(grade[i]));
        } // ASSIGN GRADE ROW!!
        for (int j = 0; j < type.length; j++) {
            course_type.put(type[j], j);
            //System.out.println("\nValue at key = " + type[j] + ": " + course_type.get(type[j]));
        } // ASSIGN TYPE COLUMN!!
        System.out.println("Welcome to the JDHS GPA calculator! Please enter your amount of courses taken: ");
        int course_amount = scanner.nextInt();
        double[] credits = new double[course_amount];
        String[] course_types = new String[course_amount];
        String[] grade_list = new String[course_amount];
        System.out.println("Is this your first time using the calculator? (Y/N)");
        String strMessage = " Please be mindful that your credits per class are significant, as well as your course types in a weighted GPA. \n " +
                "If your course is more specific upon credits, consult the 2022 Program of Studies. \n" +
                "Phys Ed - 3.0 w/ lab pullout, 3.75 w/ out. \n" +
                "Health - 1.0 w/ lab pullout, 1.25 w/ out. \n" +
                "Core Science - 6.0\n" +
                "Core Classes (Math, English, Language, etc.) - 5.0 \n" +
                "Full year electives - 5.0 \n" +
                "One Semester electives - 2.5 \n" +
                "CP - College Prep -> Normal courses. \n" +
                "H - Honors -> Honors OR High-level courses/electives. \n" +
                "AP - Advanced Placement -> Always in front of course title. \n";
        String tutorial = scanner2.nextLine();
        switch (tutorial) {
            case "Y": {
                printSlow(strMessage);
            }
            case "N": {
                break;
            }
        }
        for (int k = 0; k < course_amount; k++) {
            System.out.println("Enter information for your course below. \n   ");
            System.out.println("How many credits is this course?: ");
            credits[k] = scanner.nextDouble();
            System.out.println("What type of course is this?: ");
            course_types[k] = String.valueOf(course_type.get(scanner2.nextLine()));
            System.out.println("What is your grade in this course? ");
            int grade_input = scanner.nextInt();
            grade_list[k] = String.valueOf(grade_letter.get(gradeConv(grade_input)));
        }// reads input, finalizes arrays to be mutable
        double credit_sum = 0;
        double quality_cred_sum = 0;
        for (int l = 0; l < course_amount; l++) {
            credit_sum += credits[l];
            quality_cred_sum += quality[Integer.parseInt(grade_list[l])][2] * credits[l];
        }
        System.out.println("Your unweighted GPA is: " + quality_cred_sum / credit_sum);
        quality_cred_sum = 0;
        for (int l = 0; l < course_amount; l++) {
            quality_cred_sum += quality[Integer.parseInt(grade_list[l])][Integer.parseInt(course_types[l])] * credits[l];
        }
        System.out.println("Your weighted GPA is: " + quality_cred_sum / credit_sum);
    }

    /**
     * @param message //string
     */
    public static void printSlow(String message) throws InterruptedException {
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            System.out.print(c);
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }

    public static String gradeConv(int grade_y) {
        String letter_grade = "";
        if (grade_y <= 100 && grade_y >= 98) {
            letter_grade = "A+";
        } else if (grade_y < 98 && grade_y >= 92) {
            letter_grade = "A";
        } else if (grade_y < 92 && grade_y >= 90) {
            letter_grade = "A-";
        } else if (grade_y < 90 && grade_y >= 86) {
            letter_grade = "B+";
        } else if (grade_y < 86 && grade_y >= 82) {
            letter_grade = "B";
        } else if (grade_y < 82 && grade_y >= 80) {
            letter_grade = "B-";
        } else if (grade_y < 80 && grade_y >= 76) {
            letter_grade = "C+";
        } else if (grade_y < 76 && grade_y >= 72) {
            letter_grade = "C";
        } else if (grade_y < 72 && grade_y >= 70) {
            letter_grade = "C-";
        } else if (grade_y < 70 && grade_y >= 65) {
            letter_grade = "D";
        } else if (grade_y < 65) {
            letter_grade = "F";
        } else {
            System.out.println("Input insufficient. Try again!");
            System.exit(0);
        }
        return letter_grade;
    }
}