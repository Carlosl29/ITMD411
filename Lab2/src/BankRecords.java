/*
Use 
*/
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.*;



public class BankRecords extends Client
{	
	String id; 
	String sex; // Male or female
	String region; // INNER_CITY, TOWN, RURAL, SUBURBAN
	String married; // YES, NO
	String car; //YES, NO
	String save_act; //YES, NO
	String current_act; //YES, NO
	String mortgage; //YES, NO
	String pep; //YES, NO
	int age;
	int children; // 0, 1, 2, 3
	double income;
	static BankRecords robjs[] = new BankRecords[600];
	static ArrayList<List<String>> array = new ArrayList<>();
	
	public BankRecords()
	{
		id = "None";
		sex = "Male";
		region = "INNER_CITY";
		married = "YES";
		car = "YES";
		save_act = "YES";
		current_act = "YES";
		mortgage = "YES";
		pep = "YES";
		age = 22;
		children = 1;
		income = 25000.00;
	}
	
	@Override
	public void readData()
	{
		// TODO Auto-generated method stub
		System.out.println("Read Data");
		BufferedReader br;
		FileReader fr;
		File file;
		
		String line;
		
		try
		{
			file = new File("bank-Detail.csv");
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			while((line = br.readLine()) != null)
			{
				array.add(Arrays.asList(line.split(",")));
			}
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
			System.out.println("File Not Found...");
		}
		
		processData();
		
	}

	@Override
	public void processData() 
	{
		try{
			// TODO Auto-generated method stub
			System.out.println("Process Data");
			int indx = 0;
			
			
			for(List<String> rowData: array)
			{
				robjs[indx] = new BankRecords();

				robjs[indx].setId(rowData.get(0));
				robjs[indx].setAge(Integer.parseInt(rowData.get(1)));
				robjs[indx].setSex(rowData.get(2));
				robjs[indx].setRegion(rowData.get(3));
				robjs[indx].setIncome(Double.parseDouble(rowData.get(4)));
				robjs[indx].setMarried(rowData.get(5));
				robjs[indx].setChildren(Integer.parseInt(rowData.get(6)));
				robjs[indx].setCar(rowData.get(7));
				robjs[indx].setSave_act(rowData.get(8));
				robjs[indx].setCurrent_act(rowData.get(9));
				robjs[indx].setMortgage(rowData.get(10));
				robjs[indx].setPep(rowData.get(11));
				
				indx++;
			}
			
			printData();
		}
		catch(java.lang.NumberFormatException e2)
		{
			e2.printStackTrace();
		}
		
	}

	@Override
	public void printData() 
	{
		// TODO Auto-generated method stub
		System.out.println("Print Data");
		
		System.out.println("\nID \tAge \tSex \tRegion \t\tIncome \tMarried? \tChildren \tCar? \tSavings Account? \tCurrent Account? \tMortgage? \tPEP?");
		
		int indx = 0;
		
		
		for(indx = 0; indx < 600; indx++)
		{
			
			System.out.print(robjs[indx].getId() + "\t");
			System.out.print(robjs[indx].getAge() + "\t");
			System.out.print(robjs[indx].getSex() + "\t");
			System.out.print(robjs[indx].getRegion() + "\t");
			if(robjs[indx].getRegion().equals("RURAL") || robjs[indx].getRegion().equals("TOWN"))
			{
				System.out.print("\t");
			}
			System.out.print(robjs[indx].getIncome() + "\t");
			System.out.print(robjs[indx].getMarried() + "\t\t");
			System.out.print(robjs[indx].getChildren() + "\t\t");
			System.out.print(robjs[indx].getCar() + "\t");
			System.out.print(robjs[indx].getSave_act() + "\t\t\t");
			System.out.print(robjs[indx].getCurrent_act() + "\t\t\t");
			System.out.print(robjs[indx].getMortgage() + "\t\t");
			System.out.print(robjs[indx].getPep() + "\t\t\n");
			indx++;
		}
		
	}


	
	
	public String getId() {
		return id;
	}
	public String getSex() {
		return sex;
	}
	public String getRegion() {
		return region;
	}
	public String getMarried() {
		return married;
	}
	public String getCar() {
		return car;
	}
	public String getSave_act() {
		return save_act;
	}
	public String getCurrent_act() {
		return current_act;
	}
	public String getMortgage() {
		return mortgage;
	}
	public String getPep() {
		return pep;
	}
	public int getAge() {
		return age;
	}
	public int getChildren() {
		return children;
	}
	public double getIncome() {
		return income;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public void setCar(String car) {
		this.car = car;
	}
	public void setSave_act(String save_act) {
		this.save_act = save_act;
	}
	public void setCurrent_act(String current_act) {
		this.current_act = current_act;
	}
	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}
	public void setPep(String pep) {
		this.pep = pep;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setChildren(int children) {
		this.children = children;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	
}
