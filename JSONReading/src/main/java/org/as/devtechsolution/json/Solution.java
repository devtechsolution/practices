package org.as.devtechsolution.json;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Interval {
    int buy, sell;
}
class Result {

    /*
     * Complete the 'stockmax' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts INTEGER_ARRAY prices as parameter.
     */



    private static boolean[] findSellingDays(long[] stockPrices, int days) {

        boolean[] sellOn = new boolean[days]; // an array to keep track on days on which we can sell
        Arrays.fill(sellOn, false); // fill with false

        long localMax = Long.MIN_VALUE; // initialize the local max with minimum value possible

        // traverse the array from back and if there mark selling days for local maximum
        for (int i = days - 1; i >= 0; i--) {
            if (localMax < stockPrices[i]) { // found better local maximum
                localMax = stockPrices[i]; // update local maximum
                sellOn[i] = true; // sell all existing shares on this day
            }
        }
        return sellOn;
    }

    public static long stockmax(List<Integer> prices) {
        boolean[] sellOn = findSellingDays(prices.stream().mapToLong(l->l).toArray(), prices.size());
        long maxProfit = 0, totalCost = 0;
        int numShares = 0;
        //for (int i = prices.size()-1; i >= 0; i--){
        for (int i = 0; i < prices.size(); i++){
            if (sellOn[i] == false) { // buy shares
                totalCost += prices.get(i);
                numShares++;
            } else if (totalCost != 0) { // not a buying day and if we have something to sell then sell it
                maxProfit += numShares * prices.get(i) - totalCost; // calculate the profit
                // we sold everything so reset these variables
                totalCost = 0;
                numShares = 0;
            }
        }

        // The loop starts from 1
        // as its comparing with the previous
//        for (int i = 1; i < prices.size(); i++)
//            if (prices.get(i) > prices.get(i-1))
//                maxProfit += prices.get(i) - prices.get(i-1);
        return maxProfit;

    }
    public static void stockmax1(List<Integer> pricess) {// Prices must be given for at least two days
        int n= pricess.size();
        final var price = pricess.stream().mapToLong(l -> l).toArray();

        if (n == 1)
            return;

        int count = 0;

        // solution array
        ArrayList<Interval> sol = new ArrayList<Interval>();

        // Traverse through given price array
        int i = 0;
        while (i < n - 1) {
            // Find Local Minima. Note that the limit is
            // (n-2) as we are comparing present element to
            // the next element.
            while ((i < n - 1)
                    && (price[i + 1] <= price[i]))
                i++;

            // If we reached the end, break as no further
            // solution possible
            if (i == n - 1)
                break;

            Interval e = new Interval();
            e.buy = i++;
            // Store the index of minima

            // Find Local Maxima.  Note that the limit is
            // (n-1) as we are comparing to previous element
            while ((i < n) && (price[i] >= price[i - 1]))
                i++;

            // Store the index of maxima
            e.sell = i - 1;
            sol.add(e);

            // Increment number of buy/sell
            count++;
        }

        // print solution
        if (count == 0)
            System.out.println(
                    "There is no day when buying the stock "
                            + "will make profit");
        else
            for (int j = 0; j < count; j++)
                System.out.println(
                        "Buy on day: " + sol.get(j).buy
                                + "        "
                                + "Sell on day : " + sol.get(j).sell);

        return;

    }


}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> prices = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                    .map(Integer::parseInt)
                    .collect(toList());

                long result = Result.stockmax(prices);

                bufferedWriter.write(String.valueOf(result));
                bufferedWriter.newLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}



