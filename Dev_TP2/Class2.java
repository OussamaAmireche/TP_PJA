package Dev_TP2;

import java.io.*;
import java.util.ArrayList;

public class Class2 {

	public static void main(String[] args) {
		Employee e = null;
		try {
			FileInputStream myFile = new FileInputStream("emp.dat");
			RandomAccessFile myNewFile = new RandomAccessFile("empdirect.dat", "rw");
			ObjectInputStream objectReader = new ObjectInputStream(myFile);
			//on fait la desérialisation de l'arraylist.
			ArrayList<Employee> employeeList = (ArrayList<Employee>) objectReader.readObject();
			
			//on itére sur les éléments de l'arraylist et on écrit le contenu de chaque employee dans myNewFile. 
			for(int counter = 0; counter < employeeList.size(); counter++) {
				e = employeeList.get(counter);
				myNewFile.writeUTF(e.name);
				myNewFile.writeUTF(e.address);
				myNewFile.writeInt(e.SSN);
				myNewFile.writeInt(e.number);
			}
			System.out.println("Data saved in empdirect.dat");
			myNewFile.close();
			objectReader.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
