package chapter04;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: tsy
 * @Date: 2020/12/20
 * @Description p103, 4-1 打印当前月的日历
 */
public class CalendarTest {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        for (int i = 0; i < 24; i++) {
            printCalendar(date);
            date = date.plusMonths(1);
        }
    }

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static void printCalendar(LocalDate date) {
        System.out.println();
        System.out.println("==========" + DateTimeFormatter.ofPattern("yyyy-MM").format(date) + "==========");
        int today = date.getDayOfMonth();
        LocalDate first = date.minusDays(today - 1);
        int dow = first.getDayOfWeek().getValue();
        int month = date.getMonthValue();
        String[] titles = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fir", "Sta"};
        System.out.println(String.join(" ", titles));

        dow = dow % 7;
        for (int i = 0; i < dow; i++) {
            System.out.print("    ");
        }


        while (first.getMonthValue() == month) {
            System.out.printf("%3d", first.getDayOfMonth());
            if (formatter.format(LocalDate.now()).equals(formatter.format(first))) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            if (first.getDayOfWeek().getValue() == 6) {
                System.out.println();
            }
            first = first.plusDays(1);
        }
        System.out.println();
    }
}
