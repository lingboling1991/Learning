package algorithm.interview.thoughtWorks;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CountMoney {
	public static void main(String[] args) {
		String src = "2016-06-02 20:00~22:00 7\n" +
				"2016-06-03 09:00~12:00 14\n" +
				"2016-06-04 14:00~17:00 22\n" +
				"2016-06-05 19:00~22:00 3\n" +
				"2016-06-06 12:00~15:00 15\n" +
				"2016-06-07 15:00~17:00 12\n" +
				"2016-06-08 10:00~13:00 19\n" +
				"2016-06-09 16:00~18:00 16\n" +
				"2016-06-10 20:00~22:00 5\n" +
				"2016-06-11 13:00~15:00 11";
		System.out.println(generateSummary(src));
	}

	public static String generateSummary(String src) {
		String[] srcs = src.split("\n");
		ArrayList<Item> list = new ArrayList<>();

		for (int i = 0; i < srcs.length; i++) {
			list.add(init(srcs[i]));
		}

		return print(list);
	}

	private static Item init(String s) {
		String[] ss = s.split(" ");
		Item item = new Item();

		String[] date = ss[0].split("-");
		item.date = ss[0];
		item.time = ss[1];
		item.week_index = getWeek(Integer.valueOf(date[0]), Integer.valueOf(date[1]), Integer.valueOf(date[2]));

		String[] time = ss[1].split("~");
		item.timeStart = Integer.valueOf(time[0].split(":")[0]);
		item.timeEnd = Integer.valueOf(time[1].split(":")[0]);

		item.people = Integer.valueOf(ss[2]);
		item.fieldCount = getFieldCount(item.people);
		item.income = item.fieldCount == 0 ? 0 : 30 * item.people;
		item.spend = getSpend(item.week_index, item.timeStart, item.timeEnd, item.fieldCount);

		return item;
	}

	private static int getWeek(int year, int month, int day) {
		Date date = new Date(year - 1900, month - 1, day);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;//0 == Sunday
	}

	private static int getFieldCount(int m) {
		int t = m / 6;
		int x = m % 6;
		if (t == 0 && x < 4)
			return 0;
		else if (t == 0 && x >= 4)
			return 1;
		else if (t == 1)
			return 2;
		else if (t == 2 || t == 3) {
			if (x >= 4)
				return t + 1;
		}

		return t;
	}

	private static int getSpend(int week_index, int start, int end, int fieldCount) {
		int[] weekday = new int[24];
		for (int i = 9; i < 12; i++)
			weekday[i] = 30;
		for (int i = 12; i < 18; i++)
			weekday[i] = 50;
		for (int i = 18; i < 20; i++)
			weekday[i] = 80;
		for (int i = 20; i < 22; i++)
			weekday[i] = 60;

		int[] weekend = new int[24];
		for (int i = 9; i < 12; i++)
			weekend[i] = 40;
		for (int i = 12; i < 18; i++)
			weekend[i] = 50;
		for (int i = 18; i < 22; i++)
			weekend[i] = 60;

		int spend = 0;
		if (week_index >= 1 && week_index <= 5)
			for (int i = start; i < end; i++)
				spend += fieldCount * weekday[i];
		else
			for (int i = start; i < end; i++)
				spend += fieldCount * weekend[i];

		return spend;
	}

	private static String print(List<Item> list) {
		int totalIncome = 0;
		int totalSpend = 0;
		int totalProfit = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("[Summary]\n");
		sb.append("\n");
		for (Item item : list) {
			totalIncome += item.income;
			totalSpend += item.spend;
			int profit = item.income - item.spend;
			totalProfit += profit;
			sb.append(item.date).append(" ").append(item.time).append(" +").append(item.income).append(" -").append(item.spend).append(" ").append(profit).append("\n");
		}
		sb.append("\n");
		sb.append("Total Income: ").append(totalIncome).append("\n");
		sb.append("Total Payment: ").append(totalSpend).append("\n");
		sb.append("Profit: ").append(totalProfit).append("\n");

		return sb.toString();
	}

	private static class Item {
		String date;
		String time;
		int week_index;
		int timeStart;
		int timeEnd;
		int people;
		int income;
		int spend;
		int fieldCount;
	}
}
