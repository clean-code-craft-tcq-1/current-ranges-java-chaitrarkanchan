package BatteryMonitor;
import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;

public class BatteryMonitorTest {
	
	 @Test
	 public void givenEmptyReadingsList_whenListIsEmpty_thenReturnTrue() {
		 List<Integer> emptyReadingsList =Collections.emptyList();	
		 assertTrue(BatteryCurrentMeasurements.IsReadingListEmptyOrNull(emptyReadingsList));
	 }
	 
	 @Test
	 public void givenReadingsList_whenListNotEmpty_thenReturnFalse() {
		 List<Integer> ReadingsList =Arrays.asList(3, 3, 5, 4, 10, 11, 12);	
		 assertFalse(BatteryCurrentMeasurements.IsReadingListEmptyOrNull(ReadingsList));
	 }
	 
	 @Test
	 public void givenReadingsList_WhenListHasContinuousRanges_thenCompareExpectedWithComputed() {
		 Map<String, Integer> expectedList = new HashMap<String, Integer>();
		 expectedList.put("3-5", 4);
		 expectedList.put("10-12", 3);
		 List<Integer> ReadingsList =Arrays.asList(3, 3, 5, 4, 10, 11, 12);	
		 assertEquals(expectedList, BatteryCurrentMeasurements.ValidateAndComputeRange(ReadingsList));
	 }
	 
	 @Test
	 public void givenReadingsList_whenListIsEmpty_thenCompareExpectedWithComputed() {
		 Map<String, Integer> expectedList = new HashMap<String, Integer>();
		 expectedList.put("3-5", 4);
		 expectedList.put("10-12", 3);
		 List<Integer> emptyReadingsList =Collections.emptyList();
		 assertNotEquals(expectedList, BatteryCurrentMeasurements.ValidateAndComputeRange(emptyReadingsList));
	 }
	 
	 @Test(expected=NullPointerException.class)
	 public void givenReadingsList_whenListHaveNullValues_thenCompareExpectedWithComputed() {
		 Map<String, Integer> expectedList = new HashMap<String, Integer>();
		 expectedList.put("3-5", 4);
		 expectedList.put("10-12", 3);
		 List<Integer> emptyReadingsList =Arrays.asList(3, 3,null, 4, 10, 11, 12);	
		 assertNotEquals(expectedList, BatteryCurrentMeasurements.ValidateAndComputeRange(emptyReadingsList));
	 }
}
