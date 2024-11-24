import java.util.Scanner;

class Student {
    protected String name;
    protected int age;
    private double[][] marks; //Subjects and Marks per subject
    private String[] subjects;

    public Student(String name, int age, int numSubjects){
        this.name = name;
        this.age = age;
        this.marks = new double [numSubjects][];
        this.subjects = new String[numSubjects];
    }

    public void inputMarks(Scanner scanner){
        System.out.println("Enter number of subjects:");
        int numSubjects = scanner.nextInt();
        marks = new double[numSubjects][];
        subjects = new String[numSubjects];

            for (int i = 0; i < numSubjects; i++){
                System.out.println("Enter subject name: ");
                subjects[i] = scanner.next();
                System.out.println("Enter number of marks for "+subjects[i]+": ");
                int numMarks = scanner.nextInt();
                marks[i] = new double[numMarks];

                    for (int j = 0; j < numMarks; j++) {
                        double mark;

                        do {
                            System.out.println("Enter mark for "+subjects[i]+ ": " );
                            mark = scanner.nextDouble();
                        } while (mark < 0 || mark > 100);
                        marks[i][j] = mark;
                    }
            }
    }

    public double calculateAverage () {
        double total = 0;
        int count = 0;
            for (int i = 0; i < marks.length; i++) {
                for (int j = 0; j < marks.length; i++){
                    total += marks[i][j];
                count++;               
            }
        }
        return total/count;
    }

    public void displayMarks () {
        System.out.println("Grades for "+ name + ": ");
        for (int i = 0; i < subjects.length; i++){
            System.out.println(subjects[i] + ": ");
            for (double mark : marks[i]){
                System.out.println(mark + " ");
            }
            System.out.println();
        }
        System.out.println("Average Marks: " +calculateAverage());
    }

    
    }

public class FinalOutput {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("*******GRADING SYSTEM*******");
        System.out.println("Enter number of Students: ");
        int numStudents = sc.nextInt();
        System.out.println("Enter number of the subjects: ");
        int subjectNum = sc.nextInt();

        for (int i = 0; i < subjectNum; i++) {
            System.out.println("Enter name for subjects: ");
        }
        for (int i = 0; i < numStudents; i++) {
            System.out.println("Enter the name of Students: ");
        }
        sc.close();
    }
    
}
