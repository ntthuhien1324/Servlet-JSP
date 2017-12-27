package Persistence;

import java.util.ArrayList;

import practice.sv.bai1.*;
import sv.practice.mysql.Insert;

public class InsertFile {
	public static void main(String filename) {
		ArrayList<Student> students = new ArrayList<>();
		students = (ArrayList<Student>) ReadFile.listStudent(filename);
		Insert.insertInfo(students);
	}
}
