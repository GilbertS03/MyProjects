import java.util.*;
import java.io.*;

class Student{
    private String name;
    private String major;
    private double gpa;
    public Student(){
        name = "";
        major = "";
        gpa = 0.0;
    }
    public Student(String n, String m, double g){
        name = n;
        major = m;
        gpa = g;
    }
    public String getName(){
        return name;
    }
    public String getMajor(){
        return major;
    }
    public double getGpa(){
        return gpa;
    }
    public void setName(String n){
        name = n;
    }
    public void setMajor(String m){
        major = m;
    }
    public void setGpa(double g){
        if (g >= 0 && g <= 4.0) {
            gpa = g;
        }
    }
}
public class class_practice{
//    public static void populate(Student[] students) throws IOException{
//        Scanner infile = new Scanner(new File("data.txt"));
//        for (int i = 0; i < students.length; i++){
//            String n = infile.next();
//            String m = infile.next();
//            double g = infile.nextDouble();
//            students[i].setName(n);
//            students[i].setMajor(m);
//            students[i].setGpa(g);
//        }
//    }
public static void populate(ArrayList<Student> students) throws IOException{
    Scanner infile = new Scanner(new File("data.txt"));
    //int i = 0;
    while (infile.hasNext()){
        String n = infile.next();
        String m = infile.next();
        double g = infile.nextDouble();
        students.add(new Student(n, m, g));

//        students[i].setName(n);
//        students[i].setMajor(m);
//        students[i].setGpa(g);
    }
}
    public static void findMajorOnce(Student[] s, String m){
        int i = 0;
        boolean found = false;
        while (!found && i < s.length){
            if (m.equals(s[i].getMajor())){
                found = true;
            }
            i++;
        }
        if (found){
            System.out.println("Found");
        }
        else{
            System.out.println("Not found");
        }
    }
    public static void findMajor(Student[] s, String m){
        for (int i = 0; i < s.length; i++){
            if (m.equals(s[i].getMajor())){
                System.out.println(s[i].getName());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Student> s = new ArrayList<Student>();
        populate(s);
        System.out.println("Before Removal\n");
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i).getName() + " " + s.get(i).getMajor() + " " + s.get(i).getGpa());
        }
        s.remove(0);
        System.out.println("\nAfter removal\n");
        for (int i = 0; i < s.size(); i++) {
            System.out.println(s.get(i).getName() + " " + s.get(i).getMajor() + " " + s.get(i).getGpa());
        }

        s.clear();
        if (s.isEmpty()){
            System.out.println("\nArrayList is empty");
        }
        else{
            System.out.println("ArrayList is not empty");
        }
//        Student[] s = new Student[5];
//        for (int i = 0; i < s.length; i++){
//            s[i] = new Student();
//        }
//        populate(s);
//        for (int i = 0; i < s.length; i++){
//            System.out.println(s[i].getName() + " " + s[i].getMajor() + " " + s[i].getGpa());
//        }
//        findMajor(s, "CSC");
//        findMajorOnce(s, "CSC");

//        Student s;
//        s = new Student();
//        s.setName("Gilbert");
//        s.setMajor("Math and CSC");
//        s.setGpa(4.0);
//        System.out.println(s.getName() + " " + s.getMajor() + " " + s.getGpa());
//        System.out.println(s.getMajor());
//        System.out.println(s.getGpa());
//        Student[] students = new Student[5];

    }
}
