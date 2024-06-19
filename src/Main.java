package jdhsGPA;

import java.util.Hashtable;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Hashtable<String, Integer> grade_letter = new Hashtable<>();
        Hashtable<String, Integer> course_type = new Hashtable<>();
        String[] grade = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"}; // string array to for loop the dict put to save lines
        String[] type = {"AP", "H", "CP"};
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

        int course_amount = Integer.parseInt(JOptionPane.showInputDialog("Welcome to the JDHS GPA calculator! Please enter your amount of courses taken: "));
        double[] credits = new double[course_amount];
        String[] course_types = new String[course_amount];
        String[] grade_list = new String[course_amount];
        String tutorial = JOptionPane.showInputDialog("Is this your first time using the calculator? (Y/N)");
        String strMessage = "Please be mindful that your credits per class are significant, as well as your course types in a weighted GPA. \n " +
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
        switch (tutorial) {
            case "Y": {
                JOptionPane.showMessageDialog(null, strMessage);
            }
            case "N": {
                break;
            }
        }
        for (int k = 0; k < course_amount; k++) {
            credits[k] = Double.parseDouble(JOptionPane.showInputDialog("Enter information for course " + (k+1) + " out of " + course_amount + " ( below. \n How many credits is this course?: "));
            course_types[k] = String.valueOf(course_type.get(JOptionPane.showInputDialog("What type of course is this?: ")));
            grade_list[k] = String.valueOf(grade_letter.get(gradeConv(String.valueOf(JOptionPane.showInputDialog("Enter your grade in this course: ")))));
        }// reads input, finalizes arrays to be mutable
        double credit_sum = 0;
        double quality_cred_sum = 0;
        for (int l = 0; l < course_amount; l++) {
            credit_sum += credits[l];
            quality_cred_sum += quality[Integer.parseInt(grade_list[l])][2] * credits[l];
        }
        JOptionPane.showMessageDialog(null, "Your unweighted GPA is: " + String.format("%.5f", quality_cred_sum / credit_sum) );
        quality_cred_sum = 0;
        for (int l = 0; l < course_amount; l++) {
            quality_cred_sum += quality[Integer.parseInt(grade_list[l])][Integer.parseInt(course_types[l])] * credits[l];
        }
        JOptionPane.showMessageDialog(null, "Your weighted GPA is: " + String.format("%.5f", quality_cred_sum / credit_sum));
    }

    public static String gradeConv(String grade_y) {
        String letter_grade = "";
        if (isNumeric(grade_y)) {
            int grade_z = Integer.parseInt(grade_y);
            if (grade_z <= 105 && grade_z >= 98) {
                letter_grade = "A+";
            } else if (grade_z < 98 && grade_z >= 92) {
                letter_grade = "A";
            } else if (grade_z < 92 && grade_z >= 90) {
                letter_grade = "A-";
            } else if (grade_z < 90 && grade_z >= 86) {
                letter_grade = "B+";
            } else if (grade_z < 86 && grade_z >= 82) {
                letter_grade = "B";
            } else if (grade_z < 82 && grade_z >= 80) {
                letter_grade = "B-";
            } else if (grade_z < 80 && grade_z >= 76) {
                letter_grade = "C+";
            } else if (grade_z < 76 && grade_z >= 72) {
                letter_grade = "C";
            } else if (grade_z < 72 && grade_z >= 70) {
                letter_grade = "C-";
            } else if (grade_z < 70 && grade_z >= 65) {
                letter_grade = "D";
            } else if (grade_z < 65 && grade_z >= 0) {
                letter_grade = "F";
            }
        }
        else {
            letter_grade = grade_y;
        }
        return letter_grade;
    }
    // big problem, BIG solution!
    // 10/11/23 4.17 uw, 4.61 weighted

    public static boolean isNumeric(String s) {
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
    }
}
