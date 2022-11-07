import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hashtable<String, Integer> grade_letter = new Hashtable<>();
        Hashtable<String, Integer> course_type = new Hashtable<>();
        String[] grade = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"}; // string array to for loop the dict put to save lines
        String[] type = {"AP", "H", "CP"};
        Scanner scanner = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        Scanner scanner3 = new Scanner(System.in);
        double[][] quality = {
                {5.00, 4.67, 4.33},
                {4.67, 4.33, 4.00},
                {4.33, 4.00, 3.67},
                {4.00, 3.67, 3.33},
                {3.67, 3.33, 3.00},
                {3.33, 3.00, 2.67},
                {3.00, 2.67, 2.33},
                {2.67, 2.33, 2.00},
                {2.33, 2.00, 1.67},
                {1.67, 1.33, 1.00},
                {0.00, 0.00, 0.00}
        }; // nice table of quality point values
        double[][] quality2 = new double[11][3];
        double temp_x = 5;
        for (int r = 0; r < quality2.length-1; r++) {
            if (r>0 && r < 9) {
                temp_x = quality2[r-1][1];
            }
            if (r == 9) {
                temp_x = quality[r-1][2];
            }
            for (int c = 0; c < 3; c++) {
                quality2[r][c] = (double) Math.round((temp_x-(0.33*c))*100) / 100;
            }

        }
        System.out.println(Arrays.deepToString(quality2));
        for (int i = 0; i < grade.length; i++) {
            grade_letter.put(grade[i], i);
            // System.out.println("\nValue at key = " + grade[i] + ": " + grade_letter.get(grade[i]));
        } // ASSIGN GRADE ROW!!
        for (int j = 0; j < type.length; j++) {
            course_type.put(type[j], j);
            //System.out.println("\nValue at key = " + type[j] + ": " + course_type.get(type[j]));
        } // ASSIGN TYPE COLUMN!!
        System.out.println("Enter amount of courses taken: ");
        int course_amount = scanner.nextInt();
        double[] credits = new double[course_amount];
        String[] course_types = new String[course_amount];
        String[] grade_list = new String[course_amount];
        for (int k = 0; k < course_amount; k++) {
            System.out.println("Enter information for your first/next course below \n   ");
            System.out.println("How many credits is this course?: ");
            credits[k] = scanner.nextDouble();
            System.out.println("What type of course is this?: ");
            course_types[k] = String.valueOf(course_type.get(scanner2.nextLine()));
            System.out.println("What is your grade in this course? (letter):");
            grade_list[k] = String.valueOf(grade_letter.get(scanner2.nextLine()));
        }// reads input, finalizes arrays to be mutable
        System.out.println("Would you like an unweighted or weighted average?: ");
        String choice = scanner3.nextLine();
        double credit_sum = 0;
        double quality_cred_sum = 0;
        if (choice.equals("unweighted")) {
            for (int l = 0; l < course_amount; l++) {
                credit_sum += credits[l];
                quality_cred_sum += quality[Integer.parseInt(grade_list[l])][2] * credits[l];
            }
            System.out.println("Your unweighted GPA is: " + quality_cred_sum / credit_sum);
        } else if (choice.equals("weighted")) {
            for (int l = 0; l < course_amount; l++) {
                credit_sum += credits[l];
                quality_cred_sum += quality[Integer.parseInt(grade_list[l])][Integer.parseInt(course_types[l])] * credits[l];
            }
            System.out.println("Your weighted GPA is: " + quality_cred_sum / credit_sum);
        }

    }
}
