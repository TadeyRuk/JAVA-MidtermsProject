import java.util.Scanner;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

class Student extends Person {
    private int id;
    public double[][] marks; // marks[subjects][students]
    private String[] subjects;
    private static int studentCount = 0;

    public Student(String name, int age, int numSubjects) {
        super(name, age);
        this.id = ++studentCount;
        this.marks = new double[numSubjects][];
        this.subjects = new String[numSubjects];
    }

    public void inputDetails(Scanner scanner) {
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
    
        System.out.println("Student ID: " + id);
    }

    public void inputMarks(Scanner scanner) {
        System.out.print("Enter number of subjects: ");
        int numSubjects = scanner.nextInt();
        marks = new double[numSubjects][];
        subjects = new String[numSubjects];

        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter subject name: ");
            subjects[i] = scanner.next();
            System.out.print("Enter number of marks for " + subjects[i] + ": ");
            int numMarks = scanner.nextInt();
            marks[i] = new double[numMarks];

            for (int j = 0; j < numMarks; j++) {
                double mark;
                do {
                    System.out.print("Enter mark for " + subjects[i] + " (0-100): ");
                    mark = scanner.nextDouble();
                } while (mark < 0 || mark > 100);
                marks[i][j] = mark;
            }
        }
    }

    public double calculateAverage(int subjectIndex) {
        double total = 0;
        for (double mark : marks[subjectIndex]) {
            total += mark;
        }
        return total / marks[subjectIndex].length;
    }

    public void displayMarks() {
        System.out.println("Marks for " + getName() + ":");
        for (int i = 0; i < subjects.length; i++) {
            System.out.print(subjects[i] + ": ");
            System.out.println(java.util.Arrays.toString(marks[i]));
        }
    }

    public void updateMarks(int subjectIndex, int markIndex, double newMark) {
        if (newMark >= 0 && newMark <= 100) {
            marks[subjectIndex][markIndex] = newMark;
        } else {
            System.out.println("Invalid mark! Must be between 0 and 100.");
        }
    }

    public double getAverage() {
        double total = 0;
        for (int i = 0; i < marks.length; i++) {
            total += calculateAverage(i);
        }
        return total / marks.length;
    }

    public double getHighestMark(int subjectIndex) {
        double highest = 0;
        for (double mark : marks[subjectIndex]) {
            if (mark > highest) {
                highest = mark;
            }
        }
        return highest;
    }

    public double getLowestMark(int subjectIndex) {
        double lowest = 100;
        for (double mark : marks[subjectIndex]) {
            if (mark < lowest) {
                lowest = mark;
            }
        }
        return lowest;
    }

    public int getId() {
        return id;
    }

    public String getSubject(int index) {
        return subjects[index];
    }

    public static void displaySubjectStats(Student[] students, int numSubjects) {
        for (int i = 0; i < numSubjects; i++) {
            double highest = 0;
            double lowest = 100;
            for (Student student : students) {
                for (double mark : student.marks[i]) {
                    if (mark > highest) highest = mark;
                    if (mark < lowest) lowest = mark;
                }
            }
            System.out.println("Subject " + (i + 1) + " - Highest: " + highest + ", Lowest: " + lowest);
        }
    }

    public static void displayRankings(Student[] students) {
        // Simple bubble sort for ranking
        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < students.length - 1; j++) {
                if (students[j].getAverage() < students[j + 1].getAverage()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
        System.out.println("Rankings:");
        for (Student student : students) {
            System.out.println(student.getName() + ": Average Marks: " + student.getAverage());
        }
    }

    public static Student searchStudent(Student[] students, String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}

public class MIDTERM1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();

        Student[] students = new Student[numStudents];

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name for student " + (i + 1) + ": ");
            String name = scanner.next();
            System.out.print("Enter age for student " + name + ": ");
            int age = scanner.nextInt();
            students[i] = new Student(name, age, 0); // Age can be stored in the Person class
            students[i].inputMarks(scanner);
        }

        for (Student student : students) {
            student.displayMarks();
            System.out.println(student.getName() + ": Average Marks: " + student.getAverage());
        }

        Student.displaySubjectStats(students, students[0].marks.length);

        Student.displayRankings(students);

        // Search for a student
        System.out.print("Enter student name to search: ");
        String searchName = scanner.next();
        Student foundStudent = Student.searchStudent(students, searchName);
        if (foundStudent != null) {
            foundStudent.displayMarks();
            System.out.println(foundStudent.getName() + ": Average Marks: " + foundStudent.getAverage());
        } else {
            System.out.println("Student not found.");
        }

        scanner.close();
    }
}
