import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class YearReport {
    HashMap<String, ArrayList<YearlyRecord>> yearlyReports = new HashMap<>();
    String[] monthTitle = new String[]{"январь", "февраль", "март", "апрель", "май", "июнь", "июль", "август", "сентябрь", "октябрь", "ноябрь", "декабрь"};
    HashMap<Integer, Integer> monthGain = new HashMap<>();
    HashMap<Integer, Integer> monthExpense = new HashMap<>();
    HashMap<Integer, Integer> totalGain = new HashMap<>();

    //подгрузка отчетов
    private void readYearlyReports() throws IOException {
        ArrayList<YearlyRecord> yearlyRecords = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("resources/y.2021.csv"));
        String line = null;
        int iteration = 0;
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
        yearlyReports.put("2021", yearlyRecords);
    }

    //загрузка отчетов
    public void downloadYearReport() throws IOException {
        if(yearlyReports.isEmpty()){
            readYearlyReports();
            System.out.println("Годовой отчет успешно загружен \n");
        } else {
            System.out.println("Данные о годовом отчете уже имеются. Нет необходимости в дополнительной загрузке");
        }
    }

    private void meanGainPerYear(){
        for(String year : yearlyReports.keySet()){
            ArrayList<YearlyRecord> yearlyRecord = yearlyReports.get(year);
            int sum = 0;
            int size = 0;
            for(YearlyRecord el : yearlyRecord){
                if(!el.IS_EXPENSE){
                    size++;
                    sum += el.AMOUNT;
                    monthGain.put(el.MONTH, el.AMOUNT);
                }
            }
            int average = sum / size;
            System.out.println("Средняя прибыль за " + year + " равняется: " + average);
        }
    }

    private void meanExpensePerYear(){
        for(String year : yearlyReports.keySet()){
            ArrayList<YearlyRecord> yearlyRecord = yearlyReports.get(year);
            int sum = 0;
            int size = 0;
            for(YearlyRecord el : yearlyRecord){
                if(el.IS_EXPENSE){
                    size++;
                    sum += el.AMOUNT;
                    monthExpense.put(el.MONTH, el.AMOUNT);
                }
            }
            int average = sum / size;
            System.out.println("Средний расход за " + year + " равняется: " + average);
        }
    }

    public void showMonthGain(){
        for(Integer month : monthGain.keySet()){
            int gain = monthGain.get(month);
            int expense = monthExpense.get(month);
            int total = gain - expense;
            totalGain.put(month, total);
            System.out.println("Прибыль за " + monthTitle[month-1] + " равняется: " + total);
        }
    }

    public void printYearStatistic(){
        if(yearlyReports.isEmpty()){
            System.out.println("Для вывода статистики необходимо подгрузить годовой отчет");
        } else {
            System.out.println("Статистика за 2021 год \n");
            meanExpensePerYear();
            meanGainPerYear();
            System.out.println();
            showMonthGain();
        }
    }
}