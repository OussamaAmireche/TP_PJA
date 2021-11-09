package Dev_TP2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Class3 {

	public static void main(String[] args) {
		try {
			RandomAccessFile myFile = new RandomAccessFile("empdirect.dat", "rw");
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter the employee's number");
			int employeeNumber = scanner.nextInt();
			scanner.close();
			int counter = 1;
			if(employeeNumber <= 0) {
				System.out.println("Employee doesn't exist");
			}
			else {
				//si le fichier est vide alors il n'y a pas d'employees.
				if(myFile.length() == 0) {
					System.out.println("the employee doesn't exist");
				} 
				else {
					while(counter <= employeeNumber) {
						if(counter == employeeNumber) {
							System.out.println(myFile.readUTF());
							System.out.println(myFile.readUTF());
							System.out.println(myFile.readInt());
							System.out.println(myFile.readInt());
							break;
						} else {
							//on fait ces 4 instructions pour sauter un employee.
							myFile.readUTF();
							myFile.readUTF();
							myFile.readInt();
							myFile.readInt();
							//si on atteint la fin du fichier alors cet employee n'existe pas
							if(myFile.getFilePointer() == myFile.length()) {
								System.out.println("the employee doesn't exist");
								break;
							}
						}
						counter++;
					}
				}
			}
			myFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
