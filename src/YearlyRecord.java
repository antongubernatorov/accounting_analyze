import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class YearlyRecord {
    final int MONTH;
    final int AMOUNT;
    final boolean IS_EXPENSE;

    public YearlyRecord(int MONTH, int AMOUNT, boolean IS_EXPENSE) {
        this.MONTH = MONTH;
        this.AMOUNT = AMOUNT;
        this.IS_EXPENSE = IS_EXPENSE;
    }
}
