import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class YearReport {
    public void readYearlyReports(int year) throws IOException {
        String fileName = "y." + year + ".csv";
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int iteration = 0;
        String line = null;
        Scanner scanner = null;
        while((line = reader.readLine()) != null){
            if(iteration == 0){
                iteration++;
            }
            scanner = new Scanner(line);
            scanner.useDelimiter(",");
            while(scanner.hasNext()){
                int month = Integer.parseInt(scanner.next());
                int amount = Integer.parseInt(scanner.next());
                boolean isExpense = Boolean.parseBoolean(scanner.next());
                YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);
            }
        }
    }

    public void downloadReports(int year){
        if()
    }
}