import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	private static int minimumCost = 0;

	static int getMinimumCost(int numberOfFriends, int[] originalPricesOfFlowers) {

		int numberOfPurchasesOfMoreThanOneFlower = originalPricesOfFlowers.length - numberOfFriends;

		/**
		 * If the number of friends is greater than, or equal to, the number of flowers
		 * then the minimum cost is equal to the sum of all original prices.
		 */
		if (numberOfPurchasesOfMoreThanOneFlower <= 0) {
			return minimumCost;
		}

		Arrays.sort(originalPricesOfFlowers);

		/**
		 * If the number of purchases of more than one flower is less than the number of
		 * friends, some or all of the friends will buy exactly two flowers. In this
		 * case the iteration have to start from the lowest price since there is only
		 * one addition to the purchasing price that equals 1.
		 */
		if (numberOfPurchasesOfMoreThanOneFlower <= numberOfFriends) {
			int indexOfAdditionalPurchase = 0;
			while (numberOfPurchasesOfMoreThanOneFlower > 0) {
				minimumCost -= originalPricesOfFlowers[indexOfAdditionalPurchase];
				minimumCost += (1 + 1) * originalPricesOfFlowers[indexOfAdditionalPurchase];
				numberOfPurchasesOfMoreThanOneFlower--;
				indexOfAdditionalPurchase++;
			}
			return minimumCost;
		}

		/**
		 * If the number of purchases of more than one flower is greater than the number
		 * of friends, some or all of the friends will buy more than two flowers. In
		 * this case the iteration have to start from the highest price, thus the
		 * smaller additions to the price for more than one purchase are coupled with
		 * the highest prices.
		 */
		int indexOfAdditionalPurchase = numberOfPurchasesOfMoreThanOneFlower - 1;
		int peoplePurchasingMoreThanOnce = 0;
		int additionForNextPurchase = 1;
		while (numberOfPurchasesOfMoreThanOneFlower > 0) {

			int currentPrice = (additionForNextPurchase + 1) * originalPricesOfFlowers[indexOfAdditionalPurchase];
			minimumCost -= originalPricesOfFlowers[indexOfAdditionalPurchase];
			minimumCost += currentPrice;

			numberOfPurchasesOfMoreThanOneFlower--;
			indexOfAdditionalPurchase--;
			peoplePurchasingMoreThanOnce++;

			if (peoplePurchasingMoreThanOnce == numberOfFriends) {
				peoplePurchasingMoreThanOnce = 0;
				additionForNextPurchase += 1;
			}
		}
		return minimumCost;
	}

	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		int numberOfFlowers = reader.nextInt();
		int numberOfFriends = reader.nextInt();
		int[] originalPricesOfFlowers = new int[numberOfFlowers];

		for (int i = 0; i < numberOfFlowers; i++) {
			int originalPrice = reader.nextInt();
			originalPricesOfFlowers[i] = originalPrice;
			minimumCost += originalPrice;
		}
		reader.close();

		int minimumCost = getMinimumCost(numberOfFriends, originalPricesOfFlowers);
		System.out.println(minimumCost);
	}
}
