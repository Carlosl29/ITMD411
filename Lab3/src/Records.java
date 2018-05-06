import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Records extends BankRecords
{
	static FileWriter fw = null;
	File file = new File("bankrecords.txt");
	DecimalFormat df = new DecimalFormat("0.00");
	
	public Records()
	{
		try {
			fw = new FileWriter(file);
		}
		catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		Records br = new Records();
		br.readData();
		
		AverageComp();
		MortgageASavings();
		CarAChild();
		
		try {
			fw.close();
		}
		catch(IOException e2)
		{
			e2.printStackTrace();
		}
	}
	
	private static void AverageComp()
	{
		Arrays.sort(robjs, new GenderComparator());
		DecimalFormat df = new DecimalFormat("0.00");
		
		
		double fsum = 0;
		double msum = 0;
		double fCt = 0;
		double mCt = 0;
		
		for(int i = 0; i < robjs.length; i++)
		{
			if(robjs[i].getSex().equals("FEMALE"))
			{
				fsum += robjs[i].getIncome();
				++fCt;
			}
			else
			{
				msum += robjs[i].getIncome();
				++mCt;
			}
		}
		
		//print resulting averages to console and txt file
		double femAvg;
		double maleAvg;
		
		femAvg = fsum/(fCt);
		maleAvg = msum/(mCt);
		
		
		
		System.out.println("Calculating averages...");
		
		try {
			
			fw.write("Averages\n================\n-Income for females: $" + df.format(femAvg));
			fw.write("...\n");
			fw.write("-Income for male: $" + df.format(maleAvg));
			fw.write("...\n");
			
			System.out.println("Averages\n================\n");
			System.out.println("Income for females: $" + df.format(femAvg));
			System.out.println("Income for male: $" + df.format(maleAvg));

		}
		catch(IOException e3)
		{
			e3.printStackTrace();
		}
	}
	private static void MortgageASavings()
	{
		GenderComparator gc = new GenderComparator();
		Arrays.sort(robjs, gc);
		int count = 0;
		
		for(int i = 0; i < robjs.length; i++)
		{
			if(robjs[i].getSex().equals("FEMALE"))
			{
				if(robjs[i].getMortgage().equals("YES") && robjs[i].getSave_act().equals("YES"))
				{
					//If the person if female and has 
					//both a mortgage and savings account
					//increment the counter
					++count;
				}
			}
		}
		
		System.out.println("Calculating...");

		try {
			DecimalFormat df = new DecimalFormat("0.00");
			
			fw.write("\nFemales w/ Mortgage and Savings Account:  " + count);
			fw.write("...");
			System.out.println("\nFemales w/ Mortgage and Savings Account:  " + count);
		}
		catch(IOException e3)
		{
			e3.printStackTrace();
		}
		
		
		
	}
	private static void CarAChild()
	{
		SimpleComparator sc = new SimpleComparator();
		Arrays.sort(robjs, sc);
		int ICcount = 0;
		int Tcount = 0;
		int Rcount = 0;
		int Scount = 0;
		int count = 0;
		
		
		for(int i = 0; i < robjs.length; i++)
		{
			if(robjs[i].getSex().equals("MALE"))
			{
				if(robjs[i].getCar().equals("YES") && robjs[i].getChildren() == 1)
				{
					switch(robjs[i].getRegion())
					{
					case "INNER_CITY":
						ICcount++;
						break;
					case "TOWN":
						Tcount++;
						break;
					case "RURAL":
						Rcount++;
						break;
					case "SUBURBAN":
						Scount++;
						break;
					default:
						count++;
						break;
						
					}
				}
			}
		}
		
		try {
			String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
			
			fw.write(System.lineSeparator());
			fw.write("\nInnercity region males with car & 1 child: " + ICcount);
			fw.write("\nRural region males with car & 1 child:  " + Rcount);
			fw.write("\nSuburban region males with car & 1 child:  " + Scount);
			fw.write("\nTown region males with car & 1 child:  " + Tcount);
			fw.write("...");
			
			fw.write("Current Time Stamp = " + timeStamp + "\nProgrammed by Carlos Lopez\n");
			
			
			System.out.println("\nInnercity region males with car & 1 child:  " + ICcount);
			System.out.println("Rural region males with car & 1 child:  " + Rcount);
			System.out.println("Suburban region males with car & 1 child:  " + Scount);
			System.out.println("Town region males with car & 1 child:  " + Tcount);
			
			System.out.println("\nCurrent Time Stamp = " + timeStamp + "\nProgrammed by Carlos Lopez\n");
		}
		catch(IOException e3)
		{
			e3.printStackTrace();
		}
		
	}
}
