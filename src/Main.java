import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    YearReport yearReport = new YearReport();
    MonthlyReport monthlyReport = new MonthlyReport();
    public static void main(String[] args) throws IOException {

    }

    public void compareMonthAndYearReports(){
        for(Integer month : yearReport.totalGain.keySet()){
            Integer monthNum = yearReport.totalGain.get(month);
        }
    }
}