package Dev_TP2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Class1 {
	
	public static void main(String[] args) throws IOException {
		Employee employee = null;
		System.out.println("How Many Objects Do You Wanna Serialize?");
		Scanner scanner = new Scanner(System.in);
		int numberOfObjects = scanner.nextInt();
		scanner.nextLine();
		//on va créer une arraylist pour stocker les employees.
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		int counter = 0;
		while(counter < numberOfObjects) {
			System.out.println("Enter the name of the employee number " + (counter + 1));
			String name = scanner.nextLine();
			System.out.println("Enter the address of the employee number " + (counter + 1));
			String address = scanner.nextLine();
			System.out.println("Enter the SSN of the employee number " + (counter + 1));
			int SSN = scanner.nextInt();
			System.out.println("Enter the number of the employee number " + (counter + 1));
			int number = scanner.nextInt();
			scanner.nextLine();
			
			employee = new Employee();
			employee.name = name;
			employee.address = address;
			employee.SSN = SSN;
			employee.number = number;
			employeeList.add(employee);
			
			counter++;
		}
		scanner.close();
		
		/*J'ai choisi de sérialiser l'arraylist pour qu'on puisse facilement itérer sur ses elements quand on fait la desérialsation.
		 * Parceque si on fait la sérialistion des objets de façon normal, on ne saura pas le nombre d'objet quand on fait la desérialisation.
		 * */
		
		FileOutputStream myFile = new FileOutputStream("emp.dat");
		ObjectOutputStream objectWriter = new ObjectOutputStream(myFile);
		objectWriter.writeObject(employeeList);
		objectWriter.close();
		System.out.printf("Serialized data is saved in emp.dat");
	}

}
