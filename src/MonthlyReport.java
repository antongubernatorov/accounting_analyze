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

    //Определение самого убыточного товара
    //Печать статистики

}