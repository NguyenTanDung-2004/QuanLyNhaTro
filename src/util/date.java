package util;

import java.time.LocalDate;

public class date {
	public static String get_date() {
		LocalDate currentDate = LocalDate.now();
        int day = currentDate.getDayOfMonth();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        return day + "/" + month + "/" + year;
	}
}
