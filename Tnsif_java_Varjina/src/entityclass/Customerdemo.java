package entityclass;

public class Customerdemo  {

	//Program to demonstrate simple class in Java

		// Var declaration / Data members
		private int serialNum; // int, string, boolean, float = Primitive Data types
		private String name; // private , public, protected and default = Access modifiers / specifiers
		private int age;

		// Getters and Setters method
		
		// Object class method - to returns string representation of the object
		@Override
		public String toString() {
			return "OopsConceptDemo [Serial Number=" + serialNum + ", name=" + name + ", age=" + age + "]";
		}

		public int getSerialNum() {
			return serialNum;
		}

		public void setSerialNum(int serialNum) {
			this.serialNum = serialNum; // this is the keyword which request to the current object
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}
