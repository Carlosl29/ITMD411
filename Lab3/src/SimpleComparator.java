import java.util.Comparator;

public class SimpleComparator implements Comparator<BankRecords> {

	@Override
	public int compare(BankRecords arg0, BankRecords arg1) {
		// TODO Auto-generated method stub
		int result;
		result = arg0.getRegion().compareTo(arg1.getRegion());
		return result;
	}

}
