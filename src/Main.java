import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static MonthlyReport monthlyReport = new MonthlyReport();
    public static YearReport yearReport = new YearReport();
    public static void main(String[] args) throws IOException {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Автоматизация бухгалтерии");
            System.out.println("Для работы необходимо загрузить отчеты");
            while(true){
                printMenu();
                int userAction = scanner.nextInt();
                if(userAction == 0){
                    System.out.println("Вы вышли из приложения");
                    break;
                } else if (userAction == 1){
                    monthlyReport.downloadMonthlyReports();
                } else if (userAction == 2){
                    yearReport.downloadYearlyReports(2021);
                } else if(userAction == 3){
                    monthlyReport.printStatistic();
                } else if (userAction == 4){
                    yearReport.printYearlyStatistic();
                } else if (userAction == 5){
                    //
                }
            }
    }
    public static void printMenu(){
        System.out.println("Для выбора действия введите цифру: ");
        System.out.println("1 - Загрузка месячных отчетов");
        System.out.println("2 - Загрузка годового отчета");
        System.out.println("3 - Получить статистику по всем месячным отчетам");
        System.out.println("4 - Получить статистику по годовому отчету");
        System.out.println("5 - Сверить загруженные отчеты");
        System.out.println("0 - Выйти из приложения");
    }

    public static void compareReports(){
        boolean isValid = true;
        for(YearlyRecord yearlyRecord : yearReport.yearlyRecords){
            if(yearlyRecord.IS_EXPENSE){
                if(monthlyReport.)
            }
        }
    }
}