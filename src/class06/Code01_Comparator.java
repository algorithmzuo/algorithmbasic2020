package class06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class Code01_Comparator {

	public static class Student {
		public String name;
		public int id;
		public int age;

		public Student(String name, int id, int age) {
			this.name = name;
			this.id = id;
			this.age = age;
		}
	}

	// 任何比较器：
	// compare方法里，遵循一个统一的规范：
	// 返回负数的时候，认为第一个参数应该排在前面
	// 返回正数的时候，认为第二个参数应该排在前面
	// 返回0的时候，认为无所谓谁放前面
	public static class IdShengAgeJiangOrder implements Comparator<Student> {

		// 根据id从小到大，但是如果id一样，按照年龄从大到小
		@Override
		public int compare(Student o1, Student o2) {
			return o1.id != o2.id ? (o1.id - o2.id) : (o2.age - o1.age);
		}

	}

	public static class IdAscendingComparator implements Comparator<Student> {

		// 返回负数的时候，第一个参数排在前面
		// 返回正数的时候，第二个参数排在前面
		// 返回0的时候，谁在前面无所谓
		@Override
		public int compare(Student o1, Student o2) {
			return o1.id - o2.id;
		}

	}

	public static class IdDescendingComparator implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o2.id - o1.id;
		}

	}

	// 先按照id排序，id小的，放前面；
	// id一样，age大的，前面；
	public static class IdInAgeDe implements Comparator<Student> {

		@Override
		public int compare(Student o1, Student o2) {
			return o1.id != o2.id ? o1.id - o2.id : (o2.age - o1.age);
		}

	}

	public static void printStudents(Student[] students) {
		for (Student student : students) {
			System.out.println("Name : " + student.name + ", Id : " + student.id + ", Age : " + student.age);
		}
	}

	public static void printArray(Integer[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static class MyComp implements Comparator<Integer> {

		@Override
		public int compare(Integer o1, Integer o2) {
			return o2 - o1;
		}

	}

	public static class AComp implements Comparator<Integer> {

		// 如果返回负数，认为第一个参数应该拍在前面
		// 如果返回正数，认为第二个参数应该拍在前面
		// 如果返回0，认为谁放前面都行
		@Override
		public int compare(Integer arg0, Integer arg1) {

			return arg1 - arg0;

//			return 0;
		}

	}

	public static void main(String[] args) {

		Integer[] arr = { 5, 4, 3, 2, 7, 9, 1, 0 };

		Arrays.sort(arr, new AComp());

		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		System.out.println("===========================");

		Student student1 = new Student("A", 4, 40);
		Student student2 = new Student("B", 4, 21);
		Student student3 = new Student("C", 3, 12);
		Student student4 = new Student("D", 3, 62);
		Student student5 = new Student("E", 3, 42);
		// D E C A B

		Student[] students = new Student[] { student1, student2, student3, student4, student5 };
		System.out.println("第一条打印");

		Arrays.sort(students, new IdShengAgeJiangOrder());
		for (int i = 0; i < students.length; i++) {
			Student s = students[i];
			System.out.println(s.name + "," + s.id + "," + s.age);
		}

		System.out.println("第二条打印");
		ArrayList<Student> studentList = new ArrayList<>();
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);
		studentList.add(student5);
		studentList.sort(new IdShengAgeJiangOrder());
		for (int i = 0; i < studentList.size(); i++) {
			Student s = studentList.get(i);
			System.out.println(s.name + "," + s.id + "," + s.age);
		}
		// N * logN
		System.out.println("第三条打印");
		student1 = new Student("A", 4, 40);
		student2 = new Student("B", 4, 21);
		student3 = new Student("C", 4, 12);
		student4 = new Student("D", 4, 62);
		student5 = new Student("E", 4, 42);
		TreeMap<Student, String> treeMap = new TreeMap<>((a, b) -> (a.id - b.id));
		treeMap.put(student1, "我是学生1，我的名字叫A");
		treeMap.put(student2, "我是学生2，我的名字叫B");
		treeMap.put(student3, "我是学生3，我的名字叫C");
		treeMap.put(student4, "我是学生4，我的名字叫D");
		treeMap.put(student5, "我是学生5，我的名字叫E");
		for (Student s : treeMap.keySet()) {
			System.out.println(s.name + "," + s.id + "," + s.age);
		}

	}

}
