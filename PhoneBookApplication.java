package phoneBook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class PhoneBookApplication 
{

	public static void main(String[] args) 
	{
		// Assign variables
		Person [] phoneBook = new Person[0];
		
		//Load initial phonebook
		phoneBook = reloadPhonebook();
		
		//Start menu
		menu(phoneBook);
		
	// End main	
	}	
	
		public static void menu(Person[] phoneBook) 
		{	
			// Prompt user for menu input
			System.out.println("\nEnter 1 to add a new phonebook entry"
					+ "\nEnter 2 to delete a phonebook entry associated with a phone number"
					+ "\nEnter 3 to update the phone number of an entry"
					+ "\nEnter 4 to search the phonebook by  first name"
					+ "\nEnter 5 to search the phonebook by  last name"
					+ "\nEnter 6 to search the phonebook by  full name"
					+ "\nEnter 7 to search the phonebook by phone number"
					+ "\nEnter 8 to print all of the entries in the phonebook in alphebetical order by full name"
					+ "\nEnter 9 to EXIT");
					System.out.print("\n\nEnter your choice here(1-9):");
					
					
				// Check to see if the user entered a number and pass that number to the menu				
				int choice = validateChoice();					
				
			//  Execute menu choice 
	        switch (choice) 
	        {
	            case 1: ;
	            	//Call method to add a new entry to the phonebook
	            	phoneBook = expandArray(phoneBook, addPerson()); 
	            	// Return to the menu	           	
	            	menu(phoneBook);
	                     break;
	                     
	            case 2: ;
	            	// Call method to remove an entry from the phonebook
	            	phoneBook = removeEntry(phoneBook);
	            	// Return to the menu
	            	menu(phoneBook);
	            		break;
	            		
	            case 3: ;
	            	// Call method to update the phone number of an entry
	            	changeEntry(phoneBook);
                	// Return to the menu
            		menu(phoneBook);
                     	break;
                     
	            case 4: ;
	            	//Call method to search by first name
    				printResults(searchFirstName(phoneBook));
    				// Return to the menu
    				menu(phoneBook);
    					break;
                     
	            case 5: ;
	            	//Call method to search by last name
					printResults(searchLastName(phoneBook));
					// Return to the menu
					menu(phoneBook);
						break;
        				
	            case 6: ;
	            	//Call method to search by full name
        			printResults(searchFullName(phoneBook));
        			// Return to the menu
        			menu(phoneBook);
        				break;
        				
	            case 7: ;
            		//Call method to search by phone number
            		printResults(searchNumber(phoneBook));
            		// Return to the menu
            		menu(phoneBook);
                     break;
                     
	            case 8: ;
	            	// Print all of the entries in the phonebook in alphabetical order
	            	printPhoneBook(phoneBook);
	            	// Return to the menu
            		menu(phoneBook);
                     break;
                     
	            case 9: ;
	            	// Thank the user and exit the program
	            	updatePhonebook(phoneBook);
        			System.out.println("\nThank you for using my phone book!");
        			break;
        			
                default:
                	// Prompt the user to enter to enter a valid choice
                	System.out.println("\nEnter a number between 1 and 9");
                	// Return to the menu
                	menu(phoneBook);
                	break; 
	        }
		}
		
// Method to validate menu choice
		public static int validateChoice()	
		{				
			//Assign variables		
			int choice = 0;
			
			//Open a scanner and prompt user for input
			Scanner input = new Scanner(System.in);		
			String inputValue=input.next();
			//Check to see if input can be converted into an int and assign the value to age
			try
			{
			return Integer.parseInt(inputValue);
			}
			//Catch exception if string can not be converted into an integer and prompt to try again
			catch(NumberFormatException ex) 
			{
				System.out.println(inputValue+" is not a number between 1 and 9");
				System.out.println("Please try again!!");
				return validateChoice();				
			}			
		}
		
//Method to reload the last phonebook content from file
		public static Person[] reloadPhonebook() 
		{
			//Assign variables
			final String path = "C:\\Users\\lsmlp\\Documents\\Java\\Streams\\";
			String fileName = path+"phoneBook.txt";
			Person[] temp = new Person[0];
			//Catch exceptions
			try 
			{	
				//Read from the file
				Scanner inputEntry = new Scanner(new File(fileName));
					while (inputEntry.hasNextLine()) 
					{
						String info = inputEntry.nextLine();
						
						// Create a string array by separating the string by the pattern ", "
						String[] data = info.split(", ");
						
						// Assign each element to the corresponding data entry for the new phonebook entry
						String name = data[0];
						String street = data[1];
						String city = data[2];
						String state = data[3];
						String zip = data[4];
						String phoneNumber = data[5];
						Address address = new Address(street, city, state, zip);
						Person add = new Person(name, address, phoneNumber);										
						temp = expandArray(temp, add);
					}					
			}
			catch(FileNotFoundException e) 
			{
				System.out.println("Error reading from file");
			}
			return temp;
		}
		
//Method to write changes to ponebook
		public static void updatePhonebook(Person[] source) {
			
			// Check if there are entries that match the search to print
				if (source.length != 0) 
				{
					try 
					{	 
				    FileWriter fileWriter = new FileWriter("C:\\Users\\lsmlp\\Documents\\Java\\Streams\\phoneBook.txt");
				    PrintWriter printWriter = new PrintWriter(fileWriter);
				    
				    // Write updated phonebook to the file
					for(Person person: source) 
					{
						
						printWriter.println(person);
				    }
				    printWriter.close();
				    }
				    catch(FileNotFoundException e) 
					{
						System.out.println("Error writing to file");
					} catch (IOException e) {
						System.out.println("Error writing to file");
					}
				 
				}	
					
				else // Inform the user there are no entries in the phonebook				
				{
					System.out.println("\nThere are currently no enteries in your phonebook!  Database will not be updated.");
				}
					
		}
		
// Method to get new person info 		
		public static Person addPerson() 
		{
			// Prompt the user for input
			System.out.println("\n"+"Creating a new entry:"+"\n");		        	
			System.out.println("Please enter the information in the following format:");
			System.out.println("name, street, city, state, zip code, phone number");
			System.out.println("Example: John Doe, 114 Market St, St Louis, MO, 63403, 6366435698");
			// Get all input from user as a single string 
			Scanner inputEntry = new Scanner(System.in);
			String info = inputEntry.nextLine();
			
			// Create a string array by separating the string by the pattern ", "
			String[] data = info.split(", ");
			
			// Assign each element to the corresponding data entry for the new phonebook entry
			String name = data[0];
			String street = data[1];
			String city = data[2];
			String state = data[3];
			String zip = data[4];
			String phoneNumber = data[5];
			Address address = new Address(street, city, state, zip);
			Person add = new Person(name, address, phoneNumber);										
			System.out.println("\nNew entry added to phonebook!\n");					
			return add;
		}
				
// Method to expand a Person array
		public static Person[] expandArray(Person[] source, Person add)
		{
			Person[] temp = new Person [source.length+1];
			for (int i=0; i<source.length; i++)
			{
				Person x = source[i];
				temp[i] = x;
			}
					temp[temp.length-1]=add;
					updatePhonebook(temp);
			return temp;			
		}
		
// Method to remove a Person from the phonebook
		public static Person[] removeEntry(Person[] source) 
		{
			// Assign variables
			int index = source.length+1;
			if (source.length != 0) 
			{
				Person[] temp = new Person [source.length-1];
			
				// Get phone number of the entry to remove from the user
				System.out.println("\n"+"Removing an entry by phone number:");            		
				System.out.print("Please enter the phone number of the entry you would like to remove:");
				Scanner rem = new Scanner(System.in);
				String remove = rem.nextLine();
			
				// Find the matching entry if it exists
				for (int i=0; i<source.length; i++) 
				{
					if (remove.equals(source[i].getPhoneNumber())) 
					{					
						index = i;
					}		
					
					// Remove the entry if it does exist 			
					// All entries before the entry to be removed
					if (i<index & i<=temp.length-1) 
					{						
						temp[i]=source[i];					
					}
						// All entries after the entry to be removed
						else if (i>index) 
						{
							temp[i-1]=source[i];
						}
				}

					// Inform the user if no matching entry exists and return to program				
					if (index == source.length+1) 
					{								
						System.out.println("That phone number does not have an entry associated with it.");
						return source;				
					}
						// Return the new phonebook with the entry removed
						else 
						{
							System.out.println("The entry associated with "+remove+" has been removed.");
							updatePhonebook(temp);
							return temp;
						}
			}
			else 
			{
				System.out.println("\nThere are no entries in your phonebook");
				return source;	
			}
		}
		

// Method to change the phone number in an entry
		public static void changeEntry(Person[] source) 
		{
			// Assign variables
			int index=source.length+1;
			if (source.length != 0) 
			{
				// Get phone number of the entry to remove from the user
				System.out.println("\n"+"Changing the phone number in an entry:");            		
				System.out.print("Please enter the phone number of the entry you would like to change:");
				Scanner in = new Scanner(System.in);
				String oldN = in.nextLine();
				System.out.print("Please enter the new phone number:");
				String newN = in.nextLine();
			
				// Check to find the phone number to be updated if it exists and change it to the new number
				for(int i=0; i<source.length;i++) 
				{
					if(oldN.equals(source[i].getPhoneNumber())) 
					{
						source[i].setPhoneNumber(newN);
						index=i;
						updatePhonebook(source);
						System.out.println("The Number has been updated.");	
					}
				}
				// Inform the user if there is no entry matching their request
				if (index==source.length+1) 
				{
					System.out.println("\nThere are no entries that match your request. Please try another pnone number.");
				}
			}
			else 
			{
				System.out.println("\nThere are no entries in your phonebook");
			}
		}
// Method to print results of searches	
		public static void printResults(Person[] results) 
		{
			// Check if there are entries that match the search to print
			if (results.length != 0) 
			{
				// Print all of the search results
				System.out.println("The results of your search are:"+"\n");
				for(Person person: results) 
				{					
						System.out.println(person.displayPerson());						
				}
			}
			else // Inform the user if there were no results that match their search				
			{
				System.out.println("\nThere are no enteries that match your search!");
			}
		}
		
// Method to search by phone number
		public static Person[] searchNumber(Person[] search) 
		{
    		// Assign variables
			Person[] results = new Person[0];
			// prompt the user for input
			System.out.println("\nSearching by phone number:");            		
    		System.out.print("Please enter the phone number you would like to search for:");
    		Scanner num = new Scanner(System.in);
			String phNumber = num.nextLine();
			
			// Check for results matching the search
			for(int i=0; i<search.length; i++) 
			{
				if(search[i].getPhoneNumber().equals(phNumber)) 
				{
					results = expandArray(results, search[i]);
				}
			}
			return results;		
		}
		
// Method to search by first name
		public static Person[] searchFirstName(Person[] search) 
		{
    		// Assign variables
			Person[] results = new Person[0];
			// prompt the user for input
		    System.out.println("\nSearching by first name:");            		
		    System.out.print("Please enter the first name you would like to search for:");
		    Scanner inputF = new Scanner(System.in);
			String fName = inputF.nextLine();
			
			// Check for results matching the search
			for(int i=0; i<search.length; i++) 
			{
				if(fName.equalsIgnoreCase(search[i].getName().substring(0, search[i].getName().indexOf(' ')))) 
				{
					results = expandArray(results, search[i]);
				}
			}
			return results;		
		}
		
// Method to search by last name
		public static Person[] searchLastName(Person[] search) 
		{
    		// Assign variables
			Person[] results = new Person[0];
			// prompt the user for input
			System.out.println("\nSearching by last name:");            		
			System.out.print("Please enter the last name you would like to search for:");
			Scanner inputL = new Scanner(System.in);
			String lName = inputL.nextLine();
			// Check for results matching the search
			for(int i=0; i<search.length; i++) 
			{
				if(lName.equalsIgnoreCase(search[i].getName().substring(search[i].getName().lastIndexOf(" ")+1))) 
				{
					results = expandArray(results, search[i]);
				}
			}
			return results;		
		}
// Method to search by full name
		public static Person[] searchFullName(Person[] search) 
		{
    		// Assign variables
			Person[] results = new Person[0];
			// prompt the user for input
			System.out.println("\nSearching by full name:");            		
			System.out.print("Please enter the full name you would like to search for:");
			Scanner inputN = new Scanner(System.in);
			String Name = inputN.nextLine();
			// Check for results matching the search
			for(int i=0; i<search.length; i++) 
			{
				if(Name.equalsIgnoreCase(search[i].getName())) 
				{
					results = expandArray(results, search[i]);
				}
			}
			return results;		
		}
// Method to print the phonebook alphebetically by first name	
		public static void printPhoneBook(Person[] phoneBook) 
		{	
			 // Check to see if there are any entries in the phonebook and print them
			if (phoneBook.length != 0) 
			{
			// Assign variables
			Person temp;
			// Sort the entries alphabetically by full name
	        for (int i = 0; i < phoneBook.length; i++) 
	        {
	            for (int j = i + 1; j < phoneBook.length; j++) 
	            { 
	                if (phoneBook[i].getName().compareTo(phoneBook[j].getName()) > 0) 
	                {
	                    temp = phoneBook[i];
	                    phoneBook[i] = phoneBook[j];
	                    phoneBook[j] = temp;
	                }
	            }
	        }
	            updatePhonebook(phoneBook);
				System.out.println("The phonebook contains the following entries:"+"\n");
				for(Person person: phoneBook) 
				{					
						System.out.println(person.displayPerson());						
				}
			}
				else // Inform the user there are no entries in the phonebook
			{
				System.out.println("\n"+"There are no enteries in the phonebook!");
			}
		}	
		

}
