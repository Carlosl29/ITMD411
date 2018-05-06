import java.util.Comparator;

public class GenderComparator implements Comparator<BankRecords> {

	public int compare(BankRecords br1, BankRecords br2)
	{
		int result = br1.getSex().compareTo(br2.getSex());
		return result;
	}
}
