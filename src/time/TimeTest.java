package time;


import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	@Test
	void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05");
		assertTrue("The seconds were not calculated properly", seconds==18305);
	}
	
	@Test
	void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class,
				()->{Time.getTotalSeconds("10:00");});
	}
	
	@Test	
	void testGetTotalSecondsBoundary() {
		int seconds = Time.getTotalSeconds("05:59:59");
		assertTrue("The seconds were not calculated properly", seconds==21599);
	}

	@Test
	void testGetMilliseconds() {
		int milliseconds = Time.getMilliseconds("12:05:05:005");
		assertTrue("The Milliseconds were not calculated properly", milliseconds==5);
		
	}
	@Test
	void testGetMillisecondsBad() {
		assertThrows(NumberFormatException.class,
				()->{Time.getMilliseconds("10:00:09:Bad");});
		
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"07:08:10:010", "07:99:90:999"})
	void testGetMillisecondsBoundary(String Candidate) {
		int milliseconds = Time.getMilliseconds(Candidate);
		assertTrue("The hours were not calculated properly", milliseconds==10 || milliseconds==999);
		
	}
	
	@Test
	void testGetSecondsGood() {
		int seconds = Time.getSeconds("10:10:59");
		assertTrue("The seconds were not calculated properly", seconds==59);
	}
	
	@Test
	void testGetSecondsBad() {
		assertThrows(NumberFormatException.class,
				()->{Time.getSeconds("10 second");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"03:06:05", "05:05:59"})
	void testGetSecondsBoundary(String Candidate) {
		int seconds = Time.getSeconds(Candidate);
		assertTrue("The seconds were not calculated properly", seconds==05 || seconds==59);
		
	}
	

	@Test
	void testGetTotalMinutesGood() {
		int minutes = Time.getTotalMinutes("05:05:00");
		assertTrue("The minutes were not calculated properly", minutes==05);
	}
	
	@Test
	void testGetTotalMinutesBad() {
		assertThrows(StringIndexOutOfBoundsException.class,
				()->{Time.getTotalMinutes("3005");});
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"03:06:05", "05:06:59"})
	void testGetTotalMinutesBoundary(String Candidate) {
		int minutes = Time.getTotalMinutes(Candidate);
		assertTrue("The minutes were not calculated properly", minutes==6);
		
	}

	@ParameterizedTest
	@ValueSource(strings = {"04:06:05", "04:05:59"})
	void testGetTotalHoursBoundary(String Candidate) {
		int hours = Time.getTotalHours(Candidate);
		assertTrue("The hours were not calculated properly", hours==4);
		
	}
	
	@Test
	void testGetTotalHoursBad() {
		assertThrows(StringIndexOutOfBoundsException.class,
				()->{Time.getTotalHours("7");});
	}

}
