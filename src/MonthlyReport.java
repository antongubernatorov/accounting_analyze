import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class MonthlyReport {
    HashMap<Integer, ArrayList<MonthlyRecord>> monthlyRecords = new HashMap<>();
    String[] monthTitle = {"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь",
            "октябрь", "ноябрь", "декабрь"};

    private void readAllMonths() throws IOException {
        for(int i = 0; i < 12; i++){
            ArrayList<MonthlyRecord> months = new ArrayList<>();
            String path = "resources/m.20210" + i + ".csv";
            if(Files.exists(Path.of(path))){
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String line = null;
                int iteration = 0;
                Scanner scanner = null;
                int index = 0;
                while((line = reader.readLine()) != null){
                    if (iteration == 0){
                        iteration ++;
                        continue;
                    }
                    scanner = new Scanner(line);
                    scanner.useDelimiter(",");
                    String itemName = scanner.next();
                    boolean isExpense = Boolean.parseBoolean(scanner.next());
                    int quantity = Integer.parseInt(scanner.next());
                    int sumOfOne = Integer.parseInt(scanner.next());
                    MonthlyRecord monthlyRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);
                    months.add(monthlyRecord);
                    monthlyRecords.put(i, months);
                }
            }
        }
    }

    private int getSumGainOrCostForMonth(int monthKey, boolean isExpense){
        int sum = 0;
        try{
            for(MonthlyRecord month : monthlyRecords.get(monthKey)){
                if(month.isExpense == isExpense){
                    sum += month.quantity * month.sumOfOne;
                }
            }
        } catch (NullPointerException e){
            System.out.println(e);
        }
        return sum;
    }

    String getMaxGain(int monthKey){
        int maxGain = 0;
        String item = null;
        String maxItem = null;
        int sum = 0;
        for(MonthlyRecord monthInfo : monthlyRecords.get(monthKey)){
            if(!monthInfo.isExpense){
                sum = monthInfo.quantity * monthInfo.sumOfOne;
                item = monthInfo.itemName;
            }
            if(sum > maxGain){
                maxGain =  sum;
                maxItem = item;
            }
        }
        return "Самый прибыльный товар: " + maxItem + ", Сумма: " + sum;
    }

    String getMaxCost(int monthKey){
        int maxCost = 0;
        String item = null;
        String maxItem = null;
        int sum = 0;
        for(MonthlyRecord monthInfo : monthlyRecords.get(monthKey)){
            if(monthInfo.isExpense){
                sum = monthInfo.quantity * monthInfo.sumOfOne;
                item = monthInfo.itemName;
            }
            if(sum > maxCost){
                maxCost =  sum;
                maxItem = item;
            }
        }
        return "Самая большая трата: " + maxItem + ", Сумма: " + sum;
    }

    void printStatistic(){
        if(!monthlyRecords.isEmpty()){
            for(int month: monthlyRecords.keySet()){
                System.out.println("Отчет за: " + monthTitle[month - 1]);
                System.out.println(getMaxGain(month));
                System.out.println(getMaxCost(month));
            }
        } else {
            System.out.println("Для вывода статистики неообходимо загрузить отчеты");
        }
    }

    void downloadMonthlyReports() throws IOException {
        if(monthlyRecords.isEmpty()){
            readAllMonths();
            System.out.println("Месячные отчеты успешно загружены");
        } else{
            System.out.println("Данные по месячным отчетам уже имеются. Повторная загрузка не требуется");
        }
    }

    public void getSummaryGainOrCostForMonth(int month, boolean isExpense){
        if(isExpense){

        }
    }
}