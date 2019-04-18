import java.util.Comparator;

public class ArrivalComparator implements Comparator{

	@Override
	public int compare(Object arg0, Object arg1) {
		Process p1 = (Process)arg0;
		Process p2 = (Process)arg1;
		if(p1.getArrivalTime() < p2.getArrivalTime()) {
			return -1;
		}
		else if(p1.getArrivalTime() > p2.getArrivalTime()){
			return 1;
		}
		return 0;
	}

}
