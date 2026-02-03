import java.io.*;
import java.util.*;

public class StudentManager {
    private final String FILE_NAME = "students.txt";
    private List<Student> students = new ArrayList<>();

    public StudentManager() {
        loadStudents();
    }

    private void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("No existing data found.");
        }
    }

    private void saveStudents() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    public void addStudent(Student s) {
        students.add(s);
        saveStudents();
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        students.forEach(s -> System.out.println(s.toString()));
    }

    public Student searchStudent(String id) {
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void deleteStudent(String id) {
        students.removeIf(s -> s.getId().equals(id));
        saveStudents();
    }
}

