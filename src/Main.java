import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static YearReport yearReport = new YearReport();
    static MonthlyReport monthlyReport = new MonthlyReport();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Автоматизация бухгалтерии \n");
        printMenu();
        while (true){
            switch (scanner.nextInt()) {
                case 1:
                    monthlyReport.downloadMonthlyReports();
                    continue;
                case 2:
                    yearReport.downloadYearReport();
                    continue;
                case 3:
                    monthlyReport.printMonthlyStatistic();
                    continue;
                case 4:
                    yearReport.printYearStatistic();
                    continue;
                case 5:
                    compareMonthAndYearReports();
                    continue;
                case 0:
                    scanner.close();
                    System.out.println("Вы вышли из приложения");
                    break;
            }
        }
    }

    static void printMenu(){
        System.out.println("Введите цифру для продолжения:");
        System.out.println("1 - загрузить месячные отчеты");
        System.out.println("2 - загрузить годовой отчет");
        System.out.println("3 - вывести статистику по месяцам");
        System.out.println("4 - вывести статистику за год");
        System.out.println("5 - сравнить месячный и годовой отчеты");
        System.out.println("0 - выйти из приложения");
    }

    public static void compareMonthAndYearReports(){
        boolean isValid = true;
        for(Integer month : yearReport.totalGain.keySet()){
            int monthNum = yearReport.totalGain.get(month);
            int yearNum = monthlyReport.totalPerMonth.get(month);
            if(monthNum != yearNum){
                System.out.println("Найдены расхождения в месяце: " + monthlyReport.monthTitle[month - 1] +
                        "на сумму: " + Math.abs(monthNum - yearNum));
                isValid = false;
            }
        }
        if(isValid){
            System.out.println("Расхождений не найдено");
        }
    }
}