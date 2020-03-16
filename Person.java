package phoneBook;


public class Person
{
	// Assign instance variables
		private String name;
		private Address address;
		private String phoneNumber;
		
		// Default constructor
		public Person() 
		{
			
		}

		// Parameterized constructor
		public Person(String name, Address address, String phoneNumber)
		{
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.address = address;			
		}
		
		public Person(String name) 
		{
			this.name = name;
		}
						
		// Adding getters/setters:
		public String getName() 
		{
			return this.name;
		}
		
		public void setName(String name)
		{
			this.name = name;
		}
		 		
		public String getPhoneNumber() 
		{
			return this.phoneNumber;
		}
		
		public void setPhoneNumber(String phoneNumber)
		{
			this.phoneNumber = phoneNumber;
		}		
					
		public Address getAddress() 
		{
			return this.address;
		}
		public void setAddress(Address address)
		{
			this.address = address;
		}

		@Override
		public String toString() 
		{
			return name + ", "+address+", "+phoneNumber;
		}	
		
		public String displayPerson() {
			return "Name: " + name + ", Phone Number: " + phoneNumber.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1)$2-$3") + ", "+address.displayAddress();
		}
		
}