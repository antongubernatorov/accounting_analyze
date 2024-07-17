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
    //Считывание
    private void readMonthlyReports() throws IOException {
        for (int month = 0; month < 12; month++){
            ArrayList<MonthlyRecord> monthRecord = new ArrayList<>();
            String path = "resources/m.20210" + month + ".csv";
            if(Files.exists(Path.of(path))){
                BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
                String line = null;
                int iteration = 0;
                Scanner scanner = null;
                while((line = bufferedReader.readLine()) != null){
                    if(iteration == 0){
                        iteration++;
                        continue;
                    }
                    scanner = new Scanner(line);
                    scanner.useDelimiter(",");
                    while(scanner.hasNext()){
                        String itemName = scanner.next();
                        boolean isExpense = Boolean.parseBoolean(scanner.next());
                        int quantity = Integer.parseInt(scanner.next());
                        int sumOfOne = Integer.parseInt(scanner.next());
                        MonthlyRecord monthlyRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);
                        monthRecord.add(monthlyRecord);
                    }
                }
                monthlyRecords.put(month, monthRecord);
            }
        }
        System.out.println(monthlyRecords);
    }

    //Определение самого прибыльного товара
    private void defineMaxGain(){
        for(Integer monthIndex : monthlyRecords.keySet()){
            int maxSum = Integer.MIN_VALUE;
            String maxItemName = null;
            ArrayList<MonthlyRecord> month = monthlyRecords.get(monthIndex);
            for(MonthlyRecord el : month){
                if(!el.isExpense){
                    int total = el.quantity * el.sumOfOne;
                    if(total > maxSum){
                        maxSum = total;
                        maxItemName = el.itemName;
                    }
                }
            }
            System.out.println("Самый прибыльный товар за " + monthTitle[monthIndex - 1] + ": " + maxItemName + ", Сумма: "+maxSum);
        }
    }

    //Определение самого убыточного товара
    private void defineMaxExpense(){
        for(Integer monthIndex : monthlyRecords.keySet()){
            int maxSum = Integer.MAX_VALUE;
            String maxItemName = null;
            ArrayList<MonthlyRecord> month = monthlyRecords.get(monthIndex);
            for(MonthlyRecord el : month){
                if(el.isExpense){
                    int total = el.quantity * el.sumOfOne;
                    if(total < maxSum){
                        maxSum = total;
                        maxItemName = el.itemName;
                    }
                }
            }
            System.out.println("Самый убыточный товар за " + monthTitle[monthIndex - 1] + ": " + maxItemName + ", Сумма: "+maxSum);
        }
    }

    //подгрузка отчетов
    public void downloadMonthlyReports() throws IOException {
        if(monthlyRecords.isEmpty()){
            readMonthlyReports();
            System.out.println("Месячные отчеты успешно загружены. \n");
        } else {
            System.out.println("Данные о месячных отчетах уже имеются. Нет необходимости в загрузке");
        }
    }

    //Печать статистики
    public void printMonthlyStatistic(){
        if(monthlyRecords.isEmpty()){
            System.out.println("Для вывода статистики необходимо подгрузить месячные отчеты.");
        } else {
            System.out.println("Статистика по самым прибыльным позициям:");
            defineMaxGain();
            System.out.println();
            System.out.println("Статистика по самым убыточным позициям:");
            defineMaxExpense();
        }
    }
}