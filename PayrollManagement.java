import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Person{
	// Abstract class Person to allow for additional sub-classes later
	
	private String forname;
	private String surname;
	
	public void setForname(String name) {
		name = name.trim();
		if (validateForname(name)) {
			this.forname = name;
		} else {
			throw new IllegalArgumentException("Forname must contain at least 1 non-whitespace character.");
		}
	}
	
	public String getForname() {
		return this.forname;
	}
	
	public void setSurname(String name) {
		name = name.trim();
		if (validateSurname(name)) {
			this.surname = name;			
		} else {
			this.surname = "";
		}
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public static Boolean validateForname(String name) {
		return (name.trim().length() >= 1);
	}
	
	public static Boolean validateSurname(String name) {
		return (name.trim().length() >= 1);
	}
}

class Employee extends Person{
	// Employee extends abstract Person class inheriting forname and surname + validators
	// Employee holds additional attributes and functionality for a salaried worker
	
	private Integer employeeID;
	private Integer department;
	private Double salary;
	private Double payableTax;
	private Double postTaxSalary;
	
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}

	public Integer getEmployeeID() {
		return employeeID;
	}
	
	public void setDepartment(Integer department) {
		this.department = department;
	}
	
	public Integer getDepartment() {
		return this.department;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public Double getSalary() {
		return this.salary;
	}

	public void setPayableTax(Double tax) {
		this.payableTax = tax;
		setPostTaxSalary(this.salary - this.payableTax);
	}
	
	public Double getPayableTax() {
		return this.payableTax;
	}
	
	public void setPostTaxSalary(Double salary) {
		this.postTaxSalary = salary;
	}
	
	public Double getPostTaxSalary() {
		return this.postTaxSalary;
	}
	
	public static Boolean validateDepartment(Integer department) {
		if (department > 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Department must be a positive integer.");
		}
	}
	
	public static Boolean validateSalary(Double salary) {
		if (salary >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Salary must be at least 0.");
		}
	}
	
	public static Boolean validateTax(Double tax) {
		if (tax >= 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Tax cannot be negative.");
		}
	}
	
	public String toString() {
		return String.format("Employee ID: %d%n"
							+ "Forname: %s%n"
							+ "Surname: %s%n"
							+ "Department ID: %d%n"
							+ "Gross Salary: \u00a3%.2f%n"
							+ "Tax: \u00a3%.2f%n"
							+ "Salary after tax: \u00a3%.2f"
							,this.getEmployeeID()
							,this.getForname()
							,this.getSurname()
							,this.getDepartment()
							,this.getSalary()
							,this.getPayableTax()
							,this.getPostTaxSalary()
							);
	}
	
	public Employee(Integer id
					,String forname
					,String surname
					,Integer departmentID
					,Double salary) {
		setEmployeeID(id);
		setForname(forname);
		setSurname(surname);
		setDepartment(departmentID);
		setSalary(salary);
	}
}

class Department{
	// Department objects with getters, setters, and validation functions
	
	private Integer departmentID;
	private String departmentName;
	private String departmentAddress;
	
	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public Integer getDepartmentID() {
		return this.departmentID;
	}
	
	public void setDepartmentName(String departmentName) {
		departmentName = departmentName.trim();
		if (validateDepartmentName(departmentName)) {
			this.departmentName = departmentName;
		} else {
			throw new IllegalArgumentException("Department name must contain at least 1 non-whitespace character.");
		}
	}
	
	public String getDepartmentName() {
		return this.departmentName;
	}
	
	public void setDepartmentAddress(String departmentAddress) {
		departmentAddress = departmentAddress.trim();
		if (validateDepartmentAddress(departmentAddress)) {
			this.departmentAddress = departmentAddress;
		} else {
			throw new IllegalArgumentException("Department address must contain at least 1 non-whitespace character.");
		}		
		this.departmentAddress = departmentAddress;
	}
	
	public String getDepartmentAddress() {
		return this.departmentAddress;
	}
	
	public static Boolean validateDepartmentName(String name) {
		return (name.trim().length() >= 1);
	}
	
	public static Boolean validateDepartmentAddress(String address) {
		return (address.trim().length() >= 1);
	}
	
	public String toString() {
		return String.format("Department ID: %d%n"
							+ "Department: %s%n"
							+ "Address: %s"
							,this.getDepartmentID()
							,this.getDepartmentName()
							,this.getDepartmentAddress());
	}
	
	public Department(Integer id) {
		setDepartmentID(id);
	}
}

class TaxBand{
	// TaxBand object to hold upper and lower bounds as well as tax rate
	
	private String taxBandName;
	private Double lowerBound;
	private Double upperBound;
	private Double taxRate;
	
	public String getTaxBandName() {
		return this.taxBandName;
	}
	
	public void setTaxBandName(String taxBand) {
		this.taxBandName = taxBand;
	}
	
	public Double getLowerBound() {
		return this.lowerBound;
	}
	
	public void setLowerBound(Double lowerBound) {
		this.lowerBound = lowerBound;
	}
	
	public Double getUpperBound() {
		return this.upperBound;
	}
	
	public void setUpperBound(Double upperBound) {
		this.upperBound = upperBound;
	}
	
	public Double getTaxRate() {
		return this.taxRate;
	}
	
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}

	public TaxBand(String name) {
		this.setTaxBandName(name);	
	}
}

class TaxFreeLimit{
	// Singleton to abstract tax free limits
	
	private static TaxFreeLimit maxTaxFree = null;
	
	static final Double LOWERBOUND = 100000.0;
	static final Double UPPERBOUND = 125000.0;
	
	private TaxFreeLimit(){
		// Does nothing in line with singleton conventions.
	}
	
	public static TaxFreeLimit getInstance() {
		if (maxTaxFree == null) {
			maxTaxFree = new TaxFreeLimit();
		}
		return maxTaxFree;
	}
	
}

class PersonalAllowance{
	// Singleton to abstract personal allowance
	
	private static PersonalAllowance personalAllowance = null;
	
	static final Double ALLOWANCE = 12570.0;
	
	private PersonalAllowance(){
		// Does nothing in line with singleton conventions.
	}
	
	public static PersonalAllowance getInstance() {
		if (personalAllowance == null) {
			personalAllowance = new PersonalAllowance();
		}
		return personalAllowance;
	}
	
}

class Tax{
	
	// Holds the singleton of Tax in tax
	private static Tax tax = null;
	
	// Holds a list of TaxBand objects
	private static ArrayList<TaxBand> taxBandList = new ArrayList<>();
	
	public static Double calculateTax(Double salary) {
		// Calculates tax identifying if the salary is within the taxband's bounds
		
		setBands(salary);
		Double tax = 0.0;		
			
		for (TaxBand taxBand: taxBandList) {
			if ((salary > taxBand.getLowerBound()) && (salary <= taxBand.getUpperBound())) {
				tax = tax + ((salary - taxBand.getLowerBound()) * taxBand.getTaxRate());
			} else if (salary > taxBand.getUpperBound()) {
				tax = tax + ((taxBand.getUpperBound() - taxBand.getLowerBound()) * taxBand.getTaxRate());
			}
		}
		
		return tax;
	}
	
	public static Double calculatePostTaxSalary(Double tax, Double salary) {
		// Determines post tax salary
		
		if (tax > salary) {
			throw new IllegalArgumentException("Tax cannot exceed salary.");
		}
		if ((tax < 0.0) || (salary < 0.0)) {
			throw new IllegalArgumentException("Tax and salary must be positive.");
		}
		
		return salary - tax;
	}
	
	private static Double calculatePersonalAllowance(Double salary) {
		// Determines personal allowance
		
		if (salary > TaxFreeLimit.UPPERBOUND) {
			return 0.0;
		} else if(salary > TaxFreeLimit.LOWERBOUND) {
			return Math.floor((salary - TaxFreeLimit.LOWERBOUND)/2);
		} else {
			return PersonalAllowance.ALLOWANCE;
		}		
	}
	
	private static void setBands(Double salary) {
		/*
		 * These boundaries are defined by - https://www.gov.uk/income-tax-rates
		 * with the personal allowance deviations factored in.
		 * These calculations are based on this table - https://www.moneysavingexpert.com/banking/tax-rates/#:~:text=46%25%20additional%20rate.-,I%20live%20in%20England
		 * Only assuming for England
		 */
		
		Double personalAllowance = calculatePersonalAllowance(salary);
		setBand(0, 0.0, personalAllowance, 0.0);
		setBand(1, personalAllowance, personalAllowance + 37700.0, 0.2);
		setBand(2, personalAllowance + 37700.0, 150000.0, 0.4);
		setBand(3, 150000.0, Double.MAX_VALUE, 0.45);
	}
	
	private static void setBand(Integer element, Double lowerBound, Double upperBound, Double taxRate) {
		// Set band attributes
		
		taxBandList.get(element).setLowerBound(lowerBound);
		taxBandList.get(element).setUpperBound(upperBound);
		taxBandList.get(element).setTaxRate(taxRate);
	}
	
	private static void populateTaxArrayList() {
		// Populating tax bands
		
		TaxBand personalAllowance = new TaxBand("Personal Allowance");
		TaxBand basicRate = new TaxBand("Lower Rate");
		TaxBand higherRate = new TaxBand("Higher Rate");
		TaxBand additionalRate = new TaxBand("Additional Rate");
		taxBandList.add(personalAllowance); 
		taxBandList.add(basicRate);
		taxBandList.add(higherRate);
		taxBandList.add(additionalRate);
	}
	
	private Tax(){
		// Private constructor allow for singleton functionality
		
		// Hard-coded tax code table as not required to be modifiable and simplifies code
		populateTaxArrayList(); 
	}
	
	public static Tax getInstance() {
		// Creates or returns a singleton of Tax
		
		if(tax == null) {
			tax = new Tax();
		}
		return tax;
	}
}

class Sort{
	// Sort function to abstract sorting functionality from the PayrollManagement class
	
	private static Integer[] toSortElementLocation;
	private static Double[] toSortValues;
	
	private static void swap(Integer i, Integer j) {
		// A utility function to swap two elements in element array and value array
    	Integer tempInteger = toSortElementLocation[i];
        toSortElementLocation[i] = toSortElementLocation[j];
        toSortElementLocation[j] = tempInteger;
        Double tempDouble = toSortValues[i];
        toSortValues[i] = toSortValues[j];
        toSortValues[j] = tempDouble;
    }
  
    private static int partition(Integer low, Integer high) {
    	// QuickSort Partition function
    	
        Double pivot = toSortValues[high];
        Integer i = (low - 1);
  
        for (Integer j = low; j < high; j++) {
        	
            if (toSortValues[j] <= pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }
  

    private static void quickSort(Integer low, Integer high){
        // Begins the QuickSort algorithm
        // QuickSort algorithm based on Introduction to Algorithms by Cormen et al, 2009
    	
        if (low < high) {
            Integer partitionIndex = partition(low, high);
            quickSort(low, partitionIndex - 1);
            quickSort(partitionIndex + 1, high);
        } 
    }
	
	public static Integer[] getSortedArray() {
		// Returns sorted array of integers referencing element indices 
		return toSortElementLocation;
	}
	
	private Sort() {
		// Does nothing, private constructor is convention.
	}
	
	public static void sort(Double[] values, Integer[] elements) {
		// Initiates QuickSort processes
		
		toSortValues = new Double[values.length];
		toSortElementLocation = new Integer[values.length];
		
		toSortValues = values;
		toSortElementLocation = elements;
		
		quickSort(0, toSortElementLocation.length - 1);
		
	}
}

public class PayrollManagement {
	
	// Temporary storage for employee and department objects
	static ArrayList<Department> departmentList = new ArrayList<>();
	static ArrayList<Employee> employeeList = new ArrayList<>();
	
	// User input scanner object used globally
	static Scanner userInput = new Scanner(System.in);
	
	// -----------------
	// User interactions
	// -----------------
	
	private static String getUserChoice(String message) {
		// Overloaded method to display a single message and retrieve a string
		
		System.out.println(message);
		System.out.print("Input: ");
		return userInput.nextLine();
	}
	
	private static int getUserChoice(String[] messageArray, int choices) {
		// Overloaded method for multiple options
		// Displays an array of messages offering choices to the user
		
		boolean invalidOption = true;
		int option = -1; 
		
		while (invalidOption) {
			for(int i = 0; i < messageArray.length; i++) {
				System.out.println(messageArray[i]);
			}
			
			try {
				System.out.print("\nOption: ");
				option = Integer.parseInt(userInput.nextLine());
				if ((option >= 1) && (option <= choices)) {
					invalidOption = false;
				} else {
					clearScreen();
					System.out.println("\nPlease enter an integer between 1 and " + choices);
				}
			}
			catch (NumberFormatException e){
				clearScreen();
				System.out.println("\nPlease enter an integer option, e.g.: 1");
			}
		}
		
		return option;
	}	

	// ---------
	// Main Menu
	// ---------
	
	private static void mainMenu() {
		// Displays the main menu options
		
		boolean exit = false;
		while (!exit) {
			clearScreen();
			String menuHeader 			= "\nMain Menu Options\n";
			String departmentOptions 	= "1: Department Options";
			String employeeOptions		= "2: Employee Options";
			String exitMain				= "3: Exit program";	
			
			String[] stringArray = {menuHeader, departmentOptions, employeeOptions, exitMain};
			int menuOption = getUserChoice(stringArray, stringArray.length - 1);
			
			switch(menuOption) {
				case 1:
					departmentOptions();
					break;
				case 2:
					employeeOptions();
					break;
				default:
					exit = true;
					break;
			}
		}
	}
	
	
	// ------------------------
	// Department functionality
	// ------------------------
	
	private static void departmentOptions() {
		// Displays department options
		
		boolean exit = false;
		while (!exit) {
			clearScreen();
			String menuHeader 		= "\nDepartment Options\n";
			String addDepartment 	= "1: Add a department";
			String getDetails 		= "2: Get department details";
			String returnToMain 	= "3: Return to main menu";
			
			String[] stringArray = {menuHeader
									,addDepartment
									,getDetails
									,returnToMain};
			int menuOption = getUserChoice(stringArray, stringArray.length - 1);
		
			switch(menuOption) {
				case 1:
					newDepartment();
					break;
				case 2:
					listDepartments();
					break;
				default:
					exit = true;
					break;
			}
		}
	}
	
	
	private static void listDepartments() {
		// Displays all departments to select from
		
		clearScreen();
		
		if (departmentList.isEmpty()){
			System.out.println("There are no departments, press enter to return.");
			userInput.nextLine();
			return;
		}
		
		ArrayList<String> options = new ArrayList<>();
		for(Department department: departmentList) {
			options.add(String.format("%d: %s"
										,department.getDepartmentID()
										,department.getDepartmentName()));
		}
		String[] stringArray = new String[options.size() + 2];
		stringArray[0] = "\nSelect a department to retrieve details\n";
		stringArray[options.size() + 1] = (options.size()+1) + ": Return to department menu";
		for(int i = 1; i <= options.size(); i++) {
			stringArray[i] = options.get(i-1);
		}
		
		boolean exit = false;
		while(!exit) {
			
			int menuOption = getUserChoice(stringArray, stringArray.length - 1);
			
			if (menuOption == (stringArray.length - 1)) {
				exit = true;
			} else {
				clearScreen();
				System.out.println("Department details for ID: " + menuOption);
				displayDepartments(getDepartmentDetails(menuOption));
			}
		}
	}
	
	private static Integer[] getDepartmentDetails(Integer id) {
		// Get department element based on ID
		
		Integer[] departmentElement = {1}; 
		
		for (int i = 0; i < employeeList.size(); i++) {
			if (departmentList.get(i).getDepartmentID() == id) {
				departmentElement[0] = i;
				break;
			}
		}
		return departmentElement;	
	}
	
	private static void displayDepartments(Integer[] elementList) {
		/*
		 *  Displays departments in a formatted table
		 *  Based on elements for fast lookup
		 */
		
		// Table width
		Integer tableRuleLength = 89;
		String tableRule = dynamicStringBuilder(tableRuleLength, '=');
		
		//Output Table Headers
		String depID = "Department ID";
		String depName = "Department Name";
		String depAddress = "Department Address";
		
		// Display table headers
		System.out.println("\n" + tableRule);
		System.out.format("|%15s|%20s|%50s|\n"
						,centerString(15,depID)
						,centerString(20,depName)
						,centerString(50,depAddress));
		tableRule = dynamicStringBuilder(tableRuleLength, '-');
		System.out.print("" + tableRule);
		
		String departmentID;
		String name;
		String address;
		
		// Display every employee with element in element list
		for (int i = 0; i < elementList.length; i++) {
		
			departmentID = String.valueOf(departmentList.get(elementList[i]).getDepartmentID());
			name = stringSizeLimit(departmentList.get(elementList[i]).getDepartmentName(),19);
			address = stringSizeLimit(departmentList.get(elementList[i]).getDepartmentAddress(),49);
			
			System.out.format("\n|%15s|%20s|%50s|"
							,centerString(15,departmentID)
							,stringSizeLimit(name,20)
							,stringSizeLimit(address,50));		}
		tableRule = dynamicStringBuilder(tableRuleLength, '=');
		System.out.println("\n" + tableRule);
	}
	
	private static Boolean departmentExists(Integer id) {
		// Checks department exists by ID
		
		for(Department department: departmentList) {
			if(department.getDepartmentID().equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	private static void newDepartment() {
		// Gets user inputs for a new department
		
		Integer nextID = 0;
		String departmentName = "";
		String departmentAddress = "";
		Boolean valid = false;
		
		if (departmentList.isEmpty()) {
			nextID = 1;
		} else {
			nextID = (departmentList.size() + 1);
		}
		
		System.out.println("\nWarning: postal addresses are not validated.\n");
		System.out.println("Department will be assigned ID: " + nextID + "\n");
		
		//Check for duplicate department names
		while(!valid) {
			departmentName = getUserChoice("Enter department name.");
			valid = checkDepartmentExists(departmentName);
			valid = valid && Department.validateDepartmentName(departmentName);
			if (!valid) {
				System.out.println("Department name must be unique.");
				System.out.println("Department name must be at least 1 non-white character.\n");
			}
		}
		
		System.out.println("");		
		
		valid = false;
		
		while(!valid) {
			departmentAddress = getUserChoice("Enter department address.");
			valid = Department.validateDepartmentAddress(departmentAddress);
			if (!valid) {
				System.out.println("Department address must be at least 1 non-white character.\n");
			}
		}
		
		addDepartment(nextID,departmentName,departmentAddress);
	}
	
	private static void addDepartment(Integer departmentID
			,String departmentName
			,String departmentAddress) {
		// Added a department
		
		Department newDepartment = new Department(departmentID);
		newDepartment.setDepartmentName(departmentName);
		newDepartment.setDepartmentAddress(departmentAddress);
		departmentList.add(newDepartment);
	}
	
	private static Boolean checkDepartmentExists(String name) {
		// Check if department name already exists
		
		Boolean valid = true;
		for(Department department: departmentList) {
			if(name.toLowerCase().equals(department.getDepartmentName().toLowerCase())) {
				System.out.println("\nDepartment name already exists.");
				valid = false;
			}
		}
		return valid;
	}
	
	
	// ----------------------
	// Employee functionality
	// ----------------------
	
	private static void employeeOptions() {
		// Display employee options
		
		clearScreen();
		
		boolean exit = false;
		while (!exit) {
			String menuHeader 			= "\nEmployee Options\n";
			String newEmployee 			= "1: Add a new employee";
			String employeeDetails 		= "2: Get employee details";
			String highestPaid 			= "3: Get highest paid employee information";
			String lowestPaid 			= "4: Get lowest paid employee information";
			String sortByDepartment 	= "5: Sort and display employees by department";
			String sortBySalaray 		= "6: Sort and display employees by salary";
			String returnToMain 		= "7: Return to main menu";
			
			String[] stringArray = {menuHeader
									,newEmployee
									,employeeDetails
									,highestPaid
									,lowestPaid
									,sortByDepartment
									,sortBySalaray
									,returnToMain};
			int menuOption = getUserChoice(stringArray, stringArray.length - 1);
			
			Double[] values = new Double[employeeList.size()];
			Integer[] elements = new Integer[employeeList.size()];
			
			switch(menuOption) {
				case 1:
					if(departmentList.isEmpty()) {
						System.out.println("\nThere are no departments, employee cannot be created.");
						break;
					}
					newEmployee();
					break;
				case 2:
					if (emptyEmployeeList()) {
						break;
					}
					listEmployees();
					break;
				case 3:
					if (emptyEmployeeList()) {
						break;
					}
					clearScreen();
					System.out.println("\nThe highest paid employee's details are:");
					displayEmployees(getHighestPaidEmployee());
					break;
				case 4:
					if (emptyEmployeeList()) {
						break;
					}
					clearScreen();
					System.out.println("\nThe lowest paid employee's details are:");
					displayEmployees(getLowestPaidEmployee());
					break;
				case 5:
					if (emptyEmployeeList()) {
						break;
					}
					clearScreen();
					
					// Gathers values to sort, converted to Double type for sorting algorithm reuse
					// Gathers element ids
					for (int i = 0; i < employeeList.size(); i++) {
						values[i] = (double)employeeList.get(i).getDepartment();
						elements[i] = i;
					}

					Sort.sort(values, elements);
					System.out.println("\nEmployee details listed by department:");					
					displayEmployees(Sort.getSortedArray());
					break;
				case 6:
					if (emptyEmployeeList()) {
						break;
					}
					clearScreen();

					// Gathers values and element ids to sort
					for (int i = 0; i < employeeList.size(); i++) {
						values[i] = employeeList.get(i).getSalary();
						elements[i] = i;
					}
					
					Sort.sort(values, elements);
					System.out.println("\nEmployee details listed by salary:");
					displayEmployees(Sort.getSortedArray());
					break;
				default:
					exit = true;
			}
		}
	}
	
	// Code from solution https://stackoverflow.com/questions/8154366/how-to-center-a-string-using-string-format
	// that implemented solution found at https://www.leveluplunch.com/java/examples/center-justify-string/
	private static String centerString (int width, String s) {
		// Works like centre-align. Used American spelling as source
	    return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
	}
	
	// Inspired by https://stackoverflow.com/questions/2804827/create-a-string-with-n-characters
	private static String dynamicStringBuilder(Integer length, Character c) {
		// Create a string of length filled with characters c. Used for table rules
		return new String(new char[length]).replace('\0', c);
	}
	
	private static String stringSizeLimit(String string, Integer limit) {
		// Truncates Strings that exceed table width boundaries
		
		StringBuilder returnString = new StringBuilder(string);
		if(returnString.length() > limit) {
			returnString.setLength(limit - 2);
			returnString.append("..");
		}
		
		return returnString.toString();
	}
	
	private static void displayEmployees(Integer[] elementList) {
		/*
		 *  Displays employees in a formatted table
		 *  Based on elements for fast lookup
		 */
		
		// Table width
		Integer tableRuleLength = 100;
		String tableRule = dynamicStringBuilder(tableRuleLength, '=');
		
		//Output table headers
		String empID = "Employee ID";
		String firstName = "First Name";
		String lastName = "Last Name";
		String depID = "Department ID";
		String sal = "Salary";
		String tax = "Tax";
		String postTaxSal = "Salary After Tax";
		
		// Display table headers
		System.out.println("\n" + tableRule);
		System.out.format("|%13s|%12s|%11s|%15s|%12s|%11s|%18s|\n"
						,centerString(13,empID)
						,centerString(12,firstName)
						,centerString(11,lastName)
						,centerString(15,depID)
						,centerString(12,sal)
						,centerString(11,tax)
						,centerString(18,postTaxSal));
		tableRule = dynamicStringBuilder(tableRuleLength, '-');
		System.out.print("" + tableRule);
		
		String employeeID;
		String forname;
		String surname;
		String departmentID;
		Double salary;
		Double payableTax;
		Double postTaxSalary;
		
		// Display every employee with element in element list
		for (int i = 0; i < elementList.length; i++) {
		
			employeeID = String.valueOf(employeeList.get(elementList[i]).getEmployeeID());
			forname = stringSizeLimit(employeeList.get(elementList[i]).getForname(),12);
			surname = stringSizeLimit(employeeList.get(elementList[i]).getSurname(),11);
			departmentID = String.valueOf(employeeList.get(elementList[i]).getDepartment());
			salary = employeeList.get(elementList[i]).getSalary();
			payableTax = employeeList.get(elementList[i]).getPayableTax();
			postTaxSalary = employeeList.get(elementList[i]).getPostTaxSalary();
			
			System.out.format("\n|%13s|%12s|%11s|%15s|\u00a3%11.2f|\u00a3%10.2f|\u00a3%17.2f|"
							,centerString(13,employeeID)
							,forname
							,surname
							,centerString(15,departmentID)
							,salary
							,payableTax
							,postTaxSalary);
		}
		tableRule = dynamicStringBuilder(tableRuleLength, '=');
		System.out.println("\n" + tableRule);
	}
	
	private static void newEmployee() {
		// Get user inputs for a new employee
		
		Boolean valid = false;
		Integer nextID = 0;
		String newForname = "";
		String newSurname = "";
		Integer newDepartmentID = 0;
		Double newGrossSalary = 0.0;
		
		if (employeeList.isEmpty()) {
			nextID = 1;
		} else {
			nextID = (employeeList.size() + 1);
		}
		
		System.out.println("\nEmployee will be assigned ID: " + nextID + "\n");
		
		while(!valid) {
			newForname = getUserChoice("Enter employee forname.");
			valid = Person.validateForname(newForname);
			if (!valid) {
				System.out.println("Employee forname must must be at least 1 non-white character.\n");
			}
		}
		
		System.out.println("");		
		
		newSurname = getUserChoice("Enter employee surname (may be empty).");
		
		System.out.println("");		
		
		valid = false;
		
		while(!valid) {
			
			// list all departments to choose from with extra option to create department
			for(Department department: departmentList) {
				System.out.format("%d: %s%n"
											,department.getDepartmentID()
											,department.getDepartmentName());
			}
			
			try {
				newDepartmentID = Integer.parseInt(getUserChoice("\nEnter department id."));
				valid = departmentExists(newDepartmentID);
			}
			catch (NumberFormatException e) {
				System.out.print("Department ID must be an integer, e.g.: 1");
			}
						
			if (!valid) {
				String menuHeader = "Department does not exist, do you wish to create one?";
				String no = "1: No - will result in returning to the main menu.";
				String yes = "2: Yes";
				String[] stringArray = {menuHeader, no, yes}; 
				Integer response = getUserChoice(stringArray, stringArray.length - 1);
				if (response == 2){
						newDepartment();
				} else {
					return;
				}
			}
		}
		
		System.out.println("");		
		
		valid = false;
		
		while(!valid) {
			try {
				newGrossSalary = Double.parseDouble(getUserChoice("Enter employee salary."));				
				valid = Employee.validateSalary(newGrossSalary);
			}
			catch (NumberFormatException e) {
				System.out.println("Salary must be of double type, e.g.: 59000 or 12496.1");
			}
			if (!valid) {
				System.out.println("Salary must 0 or more.\n");
			}
		}
		
		addEmployee(nextID
					,newForname
					,newSurname
					,newDepartmentID
					,newGrossSalary);		
	}
	
	
	private static void addEmployee(Integer employeeID
									,String forname
									,String surname
									,Integer departmentID
									,Double grossSalary) {
		// Initiate a new employee object and add to employee ArrayList
		
		Employee newEmployee = new Employee(employeeID
											,forname
											,surname
											,departmentID
											,grossSalary);
		Double tax = Tax.calculateTax(grossSalary);

		newEmployee.setPayableTax(tax);
		newEmployee.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, grossSalary));
		employeeList.add(newEmployee);
		System.out.println("\nEmployee successfully added.");
	}
	
	private static void listEmployees() {
		// Lists all employees to select details from
		
		clearScreen();
		
		if (employeeList.isEmpty()){
			System.out.println("There are no employees, press enter to return.");
			userInput.nextLine();
			return;
		}
		
		ArrayList<String> options = new ArrayList<>();
		for(Employee employee: employeeList) {
			options.add(String.format("%d: %s %s"
										,employee.getEmployeeID()
										,employee.getForname()
										,employee.getSurname()));
		}
		String[] stringArray = new String[options.size() + 2];
		stringArray[0] = "\nSelect an employee to retrieve details\n";
		stringArray[options.size() + 1] = (options.size()+1) + ": Return to employee menu";
		for(int i = 1; i <= options.size(); i++) {
			stringArray[i] = options.get(i-1);
		}
		
		boolean exit = false;
		while(!exit) {
			
			int menuOption = getUserChoice(stringArray, stringArray.length - 1);
			
			if (menuOption == (stringArray.length - 1)) {
				exit = true;
			} else {
				clearScreen();
				System.out.println("\nEmployee Details for ID: " + menuOption);
				displayEmployees(getEmployeeDetails(menuOption));
			}
		}
	}
	
	private static Boolean emptyEmployeeList() {
		// Checks for no employees stored
		
		if (employeeList.isEmpty()) {
			System.out.println("\nThere are no employees in the database.");
			return true;
		}
		return false;
	}
	
	private static Integer[] getEmployeeDetails(Integer id) {
		// Returns element of employee in ArrayList
		
		Integer[] employeeElement = {1}; 
		
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getEmployeeID() == id) {
				employeeElement[0] = i;
				break;
			}
		}
		return employeeElement;	
	}
	
	private static Integer[] getHighestPaidEmployee() {
		// Iterative search to find to highest paid employee's index	
		
		Integer[] highest = {0};
		for (int i = 1; i < employeeList.size(); i++) {
			if (employeeList.get(highest[0]).getSalary() < employeeList.get(i).getSalary()) {
				highest[0] = i;
			}
		}
		return highest;
	}
	
	private static Integer[] getLowestPaidEmployee() {
		// Iterative search to find to lowest paid employee's index
		
		Integer[] lowest = {0};
		for (int i = 1; i < employeeList.size(); i++) {
			if (employeeList.get(lowest[0]).getSalary() > employeeList.get(i).getSalary()) {
				lowest[0] = i;
			}
		}
		return lowest;
	}	
	
	// ------------------------
	// Welcome and exit screens
	// ------------------------
	
	private static void welcomeMessage() {
		clearScreen();
		System.out.println("Welcome to the Payroll Management example program\n");
		System.out.println("To use this program, navigate the menus through integers "
				+ "representing the menu option.");
		System.out.println("Such as 3, for \"3: Exit Program\"\n");
		System.out.println("Press enter to engage with the program.");
		userInput.nextLine();
	}
	
	private static void exit() {
		clearScreen();
		userInput.close();
		System.out.println("\nThe program has been successfully closed.");
		System.out.println("No data has been retained.");
	}
	
	// This only works on Windows in the command prompt
	private static void clearScreen(){
		if (System.getProperty("os.name").contains("Windows")){		
			try{
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();  
			}
			catch (InterruptedException | IOException e) {
				System.out.print(e);
			}
		}
	}
	
	private static void test() {
		/*
		 * Hard-coding in test data from the assignment brief for faster testing
		 * and experimentation
		 */
		
		Double tax;

		Department newDepartment = new Department(1);
		newDepartment.setDepartmentName("Human Resources");
		newDepartment.setDepartmentAddress("1 Willcott Crescent, Mount Albert");
		departmentList.add(newDepartment);
		
		Department newDepartment2 = new Department(2);
		newDepartment2.setDepartmentName("Marketing");
		newDepartment2.setDepartmentAddress("45 Pitt Street, Mount Wellington");
		departmentList.add(newDepartment2);
		
		Department newDepartment3 = new Department(3);
		newDepartment3.setDepartmentName("Production");
		newDepartment3.setDepartmentAddress("30 Willcott Crescent, Mount Albert");
		departmentList.add(newDepartment3);
		
		Employee newEmployee = new Employee(1,"Mike","Watts",1,50000.0);
		tax = Tax.calculateTax(50000.0);
		newEmployee.setPayableTax(tax);
		newEmployee.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, 50000.0));
		employeeList.add(newEmployee);
		
		Employee newEmployee2 = new Employee(2,"Rakesh","Kumar",2,40000.0);
		tax = Tax.calculateTax(40000.0);
		newEmployee2.setPayableTax(tax);
		newEmployee2.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, 40000.0));
		employeeList.add(newEmployee2);
		
		Employee newEmployee3 = new Employee(3,"Havea","Fonua",1,80000.0);
		tax = Tax.calculateTax(80000.0);
		newEmployee3.setPayableTax(tax);
		newEmployee3.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, 80000.0));
		employeeList.add(newEmployee3);
		
		Employee newEmployee4 = new Employee(4,"Jack","Moral",2,165000.0);
		tax = Tax.calculateTax(165000.0);
		newEmployee4.setPayableTax(tax);
		newEmployee4.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, 165000.0));
		employeeList.add(newEmployee4);
		
		Employee newEmployee5 = new Employee(5,"Sarah","Karim",3,12000.0);
		tax = Tax.calculateTax(12000.0);
		newEmployee5.setPayableTax(tax);
		newEmployee5.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, 12000.0));
		employeeList.add(newEmployee5);
		
		Employee newEmployee6 = new Employee(6,"Shazia","Naeem",3,38000.0);
		tax = Tax.calculateTax(38000.0);
		newEmployee6.setPayableTax(tax);
		newEmployee6.setPostTaxSalary(Tax.calculatePostTaxSalary(tax, 38000.0));
		employeeList.add(newEmployee6);
	}
	
	public static void main(String[] args){
		
		Tax.getInstance();
		test(); // Inserts test people and departments
		welcomeMessage();
		mainMenu();
		exit();
	}
}
