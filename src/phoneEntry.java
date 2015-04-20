
import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class phoneEntry {
	public static Entry[] entryList = new Entry[200];
	static Scanner fileReader, stdin;
	static int numEntries;
	public static void main(String[] args) throws Exception {
		Scanner stdin;
		String command;
		String[] name;
		final String FILE;
		char commandChar;
		int index;
		
		stdin = new Scanner(System.in);
		FILE = "entries.txt";  
		index = -1;
		numEntries = readPhoneBook(FILE);
		
		if (numEntries == 200) {
			System.out.println("Error: Phonebook is full.");
			System.exit(0);
		}
		
		System.out.println("Codes are entered as 1 to 8 characters.");
		System.out.println("Use \"e\" for enter, \"f\" for find, \"l\" for \nlist, \"q\" for quit.");
		System.out.println();
		System.out.print("Command: ");
		command = stdin.nextLine();
		commandChar = command.charAt(0);
		
		
		
		while(!command.matches("q") && !command.matches("Q")) {
			//Creates new entries
			if (commandChar == 'e' || commandChar == 'E') {
				entryList[numEntries] = new Entry();
				
				name = command.split(" ");
				//Checks to see if the code is of the specified length
				while (name[1].length() > 8) {
					System.out.println("Error: codes must contain 1-8 characters.");
					System.out.print("Command: ");
					command = stdin.nextLine();
					name = command.split(" ");
				}
				
				entryList[numEntries].name = name[1];
				System.out.print("Enter number: ");
				entryList[numEntries].number = stdin.nextLine();
				System.out.print("Enter notes: ");
				entryList[numEntries].notes = stdin.nextLine();
				
				if (entryList[numEntries].notes.matches("")) {
					entryList[numEntries].notes = "No notes";
				}
				
				System.out.println();
				numEntries++;
			}
			//Finds an entry
			else if (commandChar == 'f' || commandChar == 'F') {
				name = command.split(" ");
				
				for (int x = 0; x < numEntries; x++) {
					
					if (entryList[x].name.equalsIgnoreCase(name[1])) {
						index = x;
						break;
					} else {
						index = -1;
					}
				
				}
				
				if (index >= 0) {
					System.out.println("-- " + entryList[index].name);
					System.out.println("-- " + entryList[index].number);
					System.out.println("-- " + entryList[index].notes);
				} else if (index == -1) {
					System.out.println("** No entry with code " + name[1]);
					System.out.println();
				}
				
				
			}
			//Lists all entries
			else if (commandChar == 'l' || commandChar == 'L') {
			
				for (int i = 0; i < numEntries; i++) {
					System.out.println("-- " + entryList[i].name);
					System.out.println("-- " + entryList[i].number);
					System.out.println("-- " + entryList[i]. notes);
					System.out.println();
				}
				
			} else {
				System.out.println("Command " + command + " is an invalid command.");
			}
			System.out.print("Command: ");
			command = stdin.nextLine();
			commandChar = command.charAt(0);
			
		}
		storePhoneBook(FILE);
		stdin.close();
	}
	
	
	
	
	
	
	//Stores entries into the file
	public static void storePhoneBook(String file) throws Exception {
		PrintStream print;
		print = new PrintStream(file);
		
		for (int i = 0; i < numEntries; i++) {
			print.println(entryList[i].name + "\t" + entryList[i].number + "\t" + entryList[i].notes);
		}
		
		System.out.println("Phonebook stored");
		print.close();
	}
	//Reads entries into the program from the file
	public static int readPhoneBook(String file) throws Exception {
		int i;
		i = 0;
		
		try {
			fileReader = new Scanner(new File(file));
			fileReader.useDelimiter("\t|\n");
			
			while (fileReader.hasNext()) {
				entryList[i] = new Entry();
				entryList[i].name = fileReader.next();
				entryList[i].number = fileReader.next();
				entryList[i].notes = fileReader.next();
				i++;
			}
			
		} catch (Exception NoSuchElementException){
			
		}
		
		return i;
	}
}



