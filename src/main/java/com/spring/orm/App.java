package com.spring.orm;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.InputStreamReader;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.orm.dao.StudentDao;
import com.spring.orm.entities.Student;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

		StudentDao studentdDao = context.getBean("studentDao", StudentDao.class);

		/*
		 * Student student = new Student(3, "Rju", "Lucknow");
		 * 
		 * int r = studentdDao.insert(student); System.out.println("Done:" + r);
		 * 
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		boolean go = true;
		while (go) {
			System.out.println("------------------------------------------------");
			System.out.println("Press 1 to add a new student");
			System.out.println("Press 2 for display all student");
			System.out.println("Press 3 for get detail of single student");
			System.out.println("Press4 for delete students");
			System.out.println("Press 5 for update student");
			System.out.println("Press 6 to exit");

			try {

				int input = Integer.parseInt(br.readLine());

				switch (input) {
				case 1:
					System.out.println("Enter ID of student");
					int id = Integer.parseInt(br.readLine());
					System.out.println("Enter name of student");

					String name = br.readLine();
					System.out.println("Enter city of student");
					String city = br.readLine();

					Student student = new Student(id, name, city);

					studentdDao.insert(student);
					break;
				case 2:
					List<Student> allStudent = studentdDao.getAllStudents();
					for (Student s : allStudent) {
						System.out.println("ID:" + s.getStudentId() + "  " + "Name:" + s.getStudentName() + "  "
								+ "City:" + s.getStudentCity());
					}
					break;
				case 3:
					System.out.println("Enter the ID of student");
					int ID = Integer.parseInt(br.readLine());
					Student s = studentdDao.getStudent(ID);
					System.out.println("Name:" + s.getStudentName() + "  " + "City:" + s.getStudentCity());

					break;
				case 4:
					System.out.println("Enter the ID of student to deleter");
					int idTodelete = Integer.parseInt(br.readLine());
					studentdDao.delete(idTodelete);

					break;
				case 5:
					System.out.println("Enter the ID of the student to update:");
					int updateId = Integer.parseInt(br.readLine());

					// Check if student exists
					Student existingStudent = studentdDao.getStudent(updateId);
					if (existingStudent == null) {
						System.out.println("Student with ID " + updateId + " not found.");
						break;
					}

					System.out.println("Enter the new name of the student:");
					String newName = br.readLine();
					System.out.println("Enter the new city of the student:");
					String newCity = br.readLine();

					// Update the student
					existingStudent.setStudentName(newName);
					existingStudent.setStudentCity(newCity);
					studentdDao.updateStudent(existingStudent);

					System.out.println("Student updated successfully!");
					break;

				case 6:
					go = false;
					System.out.println("Thanks for using out app");
					break;

				}

			} catch (Exception e) {
				// : handle exception
				System.out.println("Invalid input try another one");
				System.out.println(e.getMessage());
			}
		}

	}
}
