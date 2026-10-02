package com.vti.backend;

import java.util.ArrayList;
import java.util.List;

import com.vti.entity.Student;

public class StudentBackend {
	private static final String DEFAULT_USERNAME = "admin";
	private static final String DEFAULT_PASSWORD = "123456";

	private final ArrayList<Student> students = new ArrayList<>();

	public boolean login(String username, String password) {
		return DEFAULT_USERNAME.equals(username) && DEFAULT_PASSWORD.equals(password);
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
		for (Student student : students) {
			System.out.println(student);
		}
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
}
