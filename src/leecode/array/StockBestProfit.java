package leecode.array;

/**
 * Created by chris on 5/13/18.
 */
public class StockBestProfit {

    public static int maxProfit(int[] prices) {
        if(prices.length == 0){return 0;}

        int accumulated = 0;
        for(int i=0; i<prices.length - 1; i++){
            if(prices[i+1] > prices[i]){
                accumulated += prices[i+1] - prices[i];
            }
        }
        return accumulated;
    }

    public static void main(String[] args){
        int[] a = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(a));

    }
}
