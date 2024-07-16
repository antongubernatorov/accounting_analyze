public class MonthlyRecord {
    final String itemName;
    final boolean isExpense;
    final int quantity;
    final int sumOfOne;

    public MonthlyRecord(String itemName, boolean isExpense, int quantity, int sumOfOne) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSum_of_one() {
        return sumOfOne;
    }



    @Override
    public String toString() {
        return
                "itemName='" + itemName + '\'' +
                ", isExpense=" + isExpense +
                ", quantity=" + quantity +
                ", sumOfOne=" + sumOfOne;
    }
}
