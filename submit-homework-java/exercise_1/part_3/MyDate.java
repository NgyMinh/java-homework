package part_3;

public class MyDate {
	private int year, month, day;

	private static final String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
			"Nov", "Dec" };

	private static final String[] DAYS = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
			"Saturday" };

	private static final int[] DAYS_IN_MONTHS = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	public MyDate(int year, int month, int day) {
		if (isValidDate(year, month, day)) {
			this.year = year;
			this.month = month;
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid date");
		}
	}

	public static boolean isLeapYear(int year) {
		return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
	}

	public static boolean isValidDate(int year, int month, int day) {
		if (year < 1 || month < 1 || month > 12 || day < 1)
			return false;
		int daysInMonth = DAYS_IN_MONTHS[month - 1];
		if (month == 2 && isLeapYear(year))
			daysInMonth = 29;
		return day <= daysInMonth;
	}

	public static int getDayOfWeek(int year, int month, int day) {
		int m = (month < 3) ? month + 12 : month;
		int y = (month < 3) ? year - 1 : year;
		int k = y % 100;
		int j = y / 100;
		int dayOfWeek = (day + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) - 2 * j) % 7;
		return (dayOfWeek + 7) % 7;
	}

	public void setDate(int year, int month, int day) {
		if (isValidDate(year, month, day)) {
			this.year = year;
			this.month = month;
			this.day = day;
		} else {
			throw new IllegalArgumentException("Invalid date");
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		int dayOfWeek = getDayOfWeek(year, month, day);
		return String.format("%s %d %s %d", DAYS[dayOfWeek], day, MONTHS[month - 1], year);
	}

	public MyDate nextDay() {
		if (isValidDate(year, month, day + 1)) {
			day++;
		} else if (month < 12) {
			day = 1;
			month++;
		} else {
			day = 1;
			month = 1;
			year++;
		}
		return this;
	}

	public MyDate nextMonth() {
		if (month < 12) {
			month++;
		} else {
			month = 1;
			year++;
		}
		int daysInMonth = DAYS_IN_MONTHS[month - 1];
		if (month == 2 && isLeapYear(year))
			daysInMonth = 29;
		if (day > daysInMonth)
			day = daysInMonth;
		return this;
	}

	public MyDate nextYear() {
		year++;
		if (month == 2 && day == 29 && !isLeapYear(year)) {
			day = 28;
		}
		return this;
	}

	public MyDate previousDay() {
		if (day > 1) {
			day--;
		} else if (month > 1) {
			month--;
			day = DAYS_IN_MONTHS[month - 1];
			if (month == 2 && isLeapYear(year))
				day = 29;
		} else {
			month = 12;
			day = 31;
			year--;
		}
		return this;
	}

	public MyDate previousMonth() {
		if (month > 1) {
			month--;
		} else {
			month = 12;
			year--;
		}
		int daysInMonth = DAYS_IN_MONTHS[month - 1];
		if (month == 2 && isLeapYear(year))
			daysInMonth = 29;
		if (day > daysInMonth)
			day = daysInMonth;
		return this;
	}

	public MyDate previousYear() {
		year--;
		if (month == 2 && day == 29 && !isLeapYear(year)) {
			day = 28;
		}
		return this;
	}

	public static void main(String[] args) {
		MyDate date = new MyDate(2012, 2, 28);
		System.out.println(date);

		date.nextDay();
		System.out.println(date);

		date.nextDay();
		System.out.println(date);

		date.previousDay();
		System.out.println(date);

		date.previousMonth();
		System.out.println(date);

		date.nextYear();
		System.out.println(date);
	}
}
