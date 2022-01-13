import java.util.*;

class Student{
	private int id;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
	}
}

//Complete the code

/*
 * You are given a list of student information: ID, FirstName, and CGPA.
 * Your task is to rearrange them according to their CGPA in decreasing
 * order. If two student have the same CGPA, then arrange them according
 * to their first name in alphabetical order. If those two students also
 * have the same first name, then order them according to their ID. 
 * No two students have the same ID.
 *
 * Hint: You can use comparators to sort a list of objects. 
 * See the oracle docs to learn about comparators.
 *
 * Input Format
 *
 * The first line of input contains an integer, representing the total 
 * number of students. The next lines contains a list of student 
 * information in the following structure:
 *
 * ID Name CGPA
 *
 */

public class Solution
{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		int testCases = Integer.parseInt(in.nextLine());
		
		List<Student> studentList = new ArrayList<Student>();
		while(testCases>0){
			int id = in.nextInt();
			String fname = in.next();
			double cgpa = in.nextDouble();
			
			Student st = new Student(id, fname, cgpa);
			studentList.add(st);
			
			testCases--;
		}
      
      	Collections.sort(studentList, new Comparator<Student>() {
      	    @Override
      	    public int compare(Student p1, Student p2)
      	    {
      	        if (p1.getCgpa() != p2.getCgpa()) {
      	            if(p1.getCgpa()>p2.getCgpa()) return -1; else return 1; //return (int) (p1.getCgpa() - p2.getCgpa());
      	        }
      	        return p1.getFname().compareTo(p2.getFname());
      	        //return 0;
      	    }
      	});
		
      	for(Student st: studentList){
			System.out.println(st.getFname());
		}

	
	}
}



