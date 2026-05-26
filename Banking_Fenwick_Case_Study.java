import java.util.*;

public class Banking_Fenwick_Case_Study {

    static int[] BIT;
    static int n;

    // Update Account Balance
    static void update(int index, int value) {

        while (index <= n) {
            BIT[index] += value;
            index += index & (-index);
        }
    }

    // Get Prefix Sum
    static int query(int index) {

        int sum = 0;

        while (index > 0) {
            sum += BIT[index];
            index -= index & (-index);
        }

        return sum;
    }

    public static void main(String[] args) {

        // Customer Transactions
        int[] transactions = {0, 5000, 3000, 7000, 2000, 6000};

        n = 5;

        BIT = new int[n + 1];

        // Build Fenwick Tree
        for (int i = 1; i <= n; i++) {
            update(i, transactions[i]);
        }

        System.out.println("Banking Transaction Records");
        System.out.println("----------------------------");

        for (int i = 1; i <= n; i++) {
            System.out.println(
                "Customer " + i +
                " Transaction Amount: ₹" +
                transactions[i]
            );
        }

        System.out.println();

        // Prefix Sum Example
        System.out.println(
            "Total Transactions from Customer 1 to 3: ₹"
            + query(3)
        );
    }
}