package com.vti.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vti.entity.Student;

public class StudentBackend {
	private static final String DEFAULT_USERNAME = "admin";
	private static final String DEFAULT_PASSWORD = "123456";
	private static final String USER_USERNAME = "user";
	private static final String USER_PASSWORD = "123456";

	private final ArrayList<Student> students = new ArrayList<>();

	public boolean login(String username, String password) {
		return (DEFAULT_USERNAME.equals(username) && DEFAULT_PASSWORD.equals(password))
				|| (USER_USERNAME.equals(username) && USER_PASSWORD.equals(password));
	}

	public boolean isAdmin(String username) {
		return DEFAULT_USERNAME.equals(username);
	}

	public void addStudent(Student student) {
		if (student == null || exists(String.valueOf(student.getId()))) {
			return;
		}
		students.add(student);
	}

	public void updateStudent(Student student) {
		if (student == null) {
			return;
		}

		Student existingStudent = findStudentById(String.valueOf(student.getId()));
		if (existingStudent == null) {
			return;
		}

		existingStudent.setName(student.getName());
		existingStudent.setAge(student.getAge());
		existingStudent.setScore(student.getScore());
	}

	public void deleteStudent(Student student) {
		if (student == null) {
			return;
		}
		students.removeIf(existingStudent -> existingStudent.getId() == student.getId());
	}

	public void getAllStudent(String id) {
		printStudentTable(students);
	}

	public void printStudent(Student student) {
		if (student == null) {
			return;
		}
		printStudentTable(Collections.singletonList(student));
	}

	public Student findStudentById(String id) {
		Integer studentId = parseId(id);
		if (studentId == null) {
			return null;
		}

		for (Student student : students) {
			if (student.getId() == studentId) {
				return student;
			}
		}
		return null;
	}

	public boolean exists(String id) {
		return findStudentById(id) != null;
	}

	public List<Student> getStudents() {
		return new ArrayList<>(students);
	}

	private Integer parseId(String id) {
		if (id == null || id.trim().isEmpty()) {
			return null;
		}

		try {
			return Integer.valueOf(id.trim());
		} catch (NumberFormatException exception) {
			return null;
		}
	}

	private void printStudentTable(List<Student> studentList) {
		if (studentList.isEmpty()) {
			System.out.println("(Danh sach sinh vien dang trong.)");
			return;
		}

		String border = "+--------+----------------------+-------+--------+";
		System.out.println(border);
		System.out.printf("| %-6s | %-20s | %-5s | %-6s |%n", "ID", "TEN", "TUOI", "DIEM");
		System.out.println(border);
		for (Student student : studentList) {
			System.out.printf("| %-6d | %-20s | %-5d | %-6.2f |%n",
					student.getId(), student.getName(), student.getAge(), student.getScore());
		}
		System.out.println(border);
	}
}
