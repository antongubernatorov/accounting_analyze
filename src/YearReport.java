import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class YearReport {
    ArrayList<YearlyRecord> yearlyRecords;
    HashMap<Integer, Integer> gainRecords;
    HashMap<Integer, Integer> costRecords;
    MonthlyReport monthlyReport = new MonthlyReport();
    String[] yearTitle = {"2021"};

    public YearReport() {
        this.yearlyRecords = new ArrayList<>();
        this.gainRecords = new HashMap<>();
        this.costRecords = new HashMap<>();
    }



    private void readYearlyReports(int year) throws IOException {
        String fileName = "resources/y." + year + ".csv";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int iteration = 0;
        String line = null;
        Scanner scanner = null;
        while((line = reader.readLine()) != null){
            if(iteration == 0){
                iteration++;
                continue;
            }
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                int month = Integer.parseInt(scanner.next());
                int amount = Integer.parseInt(scanner.next());
                boolean isExpense = Boolean.parseBoolean(scanner.next());
                YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);
                yearlyRecords.add(yearlyRecord);
            }
        }
    }

    private void readYearlyStatistic(){
        for (YearlyRecord record : yearlyRecords){
            if(record.IS_EXPENSE){
                costRecords.put(record.MONTH, record.AMOUNT);
            } else{
                gainRecords.put(record.MONTH, record.AMOUNT);
            }
            if(costRecords.get(record.MONTH) != null && gainRecords.get(record.MONTH) != null){
                int profitOfTheMonth;
                profitOfTheMonth = gainRecords.get(record.MONTH) - costRecords.get(record.MONTH);
                if(profitOfTheMonth < 0){
                    System.out.println("Убыток за " + monthlyReport.monthTitle[record.MONTH - 1] + " составил " + profitOfTheMonth);
                } else {
                    System.out.println("Прибыль за " + monthlyReport.monthTitle[record.MONTH - 1] + " составила " + profitOfTheMonth);
                }
            }
        }
    }

    public void downloadYearlyReports(int year) throws IOException {
        if(yearlyRecords.isEmpty()) {
            readYearlyReports(year);
            System.out.println("Годовой отчет успешно загружен\n");
        } else {
            System.out.println("Данные по годовому отчету уже загружены. Дополнительная загрузка не требуется");
        }
    }

    private void averageGain(){
        int sum = 0;
        for(Integer el : gainRecords.values()){
            sum += el;
        }
        int average = sum / gainRecords.size();
        System.out.println("Средняя прибыль за год: " + average);
    }

    private void averageCost(){
        int sum = 0;
        for(Integer el : costRecords.values()){
            sum += el;
        }
        int average = sum /  costRecords.size();
        System.out.println("Средний расход за год: " + average);
    }

    public void printYearlyStatistic(){
        if(!yearlyRecords.isEmpty()){
            System.out.println("Отчет за " + yearTitle[0] + " год");
            readYearlyStatistic();
            averageGain();
            averageCost();
        } else {
            System.out.println("Необходимо загрузить годовой отчет \n");
        }
    }
}