package BatteryMonitor;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BatteryCurrentMeasurements {

	public static boolean IsReadingListEmptyOrNull(List<Integer> ReadingsList) {
		// TODO Auto-generated method stub
		return Objects.isNull(ReadingsList)||ReadingsList.isEmpty()||ReadingsList==null;
	}

	public  static Map<String, Integer> ValidateAndComputeRange(List<Integer> readingsList){
		 Map<String, Integer> readingsWithRanges = new HashMap<String, Integer>();
		 return IsReadingListEmptyOrNull(readingsList)?readingsWithRanges:getCountWithContinousRangeLimit(readingsList);
	}
	
	public static Map<String, Integer> getCountWithContinousRangeLimit(List<Integer> readingsList) {
		 Collections.sort(readingsList);
         int FirstVal,LastVal,currentVal;
         FirstVal=LastVal=currentVal= readingsList.get(0);
         int count = 1;
         Map<String, Integer> readingsWithRanges = new HashMap<String, Integer>();
         for (int i = 1; i < readingsList.size(); i++)
         {
             if (currentVal + 1 == readingsList.get(i)||currentVal == readingsList.get(i) )
             {  count++;
            	 currentVal = LastVal = readingsList.get(i);
              }
             else
             {
            	 count= 1;
            	 readingsWithRanges.put(FirstVal + "-" + LastVal, count);
            	 currentVal = LastVal = FirstVal = readingsList.get(i);
             }
         }
         readingsWithRanges.put(FirstVal + "-" + LastVal, count);
         return readingsWithRanges;
	}
}
