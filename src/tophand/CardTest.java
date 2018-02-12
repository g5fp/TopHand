package tophand;
//import java.util.Scanner;

public class CardTest {

	public static void main(String[] args) {

		String faces = "";
		String suits = "";
		Boolean result = false;
		int[] handCounts = new int[10];
		// Scanner input = new Scanner(System.in);
		// setting the number of hands dealt
		// System.out.println("Enter the number of hands to deal:");
		// int handsToDeal= input.nextInt();
		// for (int j = 0; j < handsToDeal; j++) {
		for (int j = 0; j < 1000000; j++) {
			System.out.println("Hands Dealt:" + (j + 1));
			// create deck of cards from DeckOfCards class
			DeckOfCards myDeckOfCards = new DeckOfCards();
			for (int i = 0; i < 10; i++) {
				myDeckOfCards.shuffle();
			}
			Hand myHand = new Hand(myDeckOfCards);

			suits = myHand.getSuits();
			faces = myHand.getFaces();
			// uncomment the two line below if you want to test a particular
			// hand
			// also set the loop to only deal 1 hand.
			// faces = "2A543";
			// suits = "SSHSS";

			// sort the card high to low and low to high
			String sortedCardsAceHigh = myHand.sortAceHigh(faces);
			String sortedCardsAceLow = myHand.sortAceLow(faces);

			// check hand rankings
			do {
				result = myHand.isRoyalFlush(sortedCardsAceHigh, suits);
				if (result) {
					System.out.println("High Hand is: Royal Flush");
					handCounts[0]++;
					break;
				}
				result = myHand.isStraightFlush(sortedCardsAceHigh, suits);
				if (result) {
					System.out.println("High Hand is: Straight Flush");
					handCounts[1]++;
					break;
				}
				result = myHand.isQuads(faces);
				if (result) {
					System.out.println("High Hand is: Quads");
					handCounts[2]++;
					break;
				}
				result = myHand.isFullHouse(faces);
				if (result) {
					System.out.println("High Hand is: Full House");
					handCounts[3]++;
					break;
				}
				result = myHand.isFlush(suits);
				if (result) {
					System.out.println("High Hand is: Flush");
					handCounts[4]++;
					break;
				}
				result = myHand.isStraight(sortedCardsAceHigh);
				if (result) {
					System.out.println("High hand is: Straight");
					handCounts[5]++;
					break;
				}
				result = myHand.isStraight(sortedCardsAceLow);
				if (result) {
					System.out.println("High hand is: Straight");
					handCounts[5]++;
					break;
				}
				result = myHand.isTrips(faces);
				if (result) {
					System.out.println("High Hand is: Trips");
					handCounts[6]++;
					break;
				}
				result = myHand.isTwoPair(faces);
				if (result) {
					System.out.println("High Hand is: Two Pair");
					handCounts[7]++;
					break;
				}
				result = myHand.isPair(faces);
				if (result) {
					System.out.println("High Hand is: Pair");
					handCounts[8]++;
					break;
				}

				// if none of the above are true then the default is high hand
				System.out.println("High Hand is: High Card");
				handCounts[9]++;
				break;
			} while (result);

			// printing the hand. Combining the suits and faces back together
			for (int i = 0; i < 5; i++) {
				System.out.print(faces.substring(i, i + 1)
						+ suits.substring(i, i + 1) + " ");
			}
			System.out.println();
		}

		System.out.println("Hand Counts:");
		System.out.println("Royal Flush:" + handCounts[0]);
		System.out.println("Straight Flush:" + handCounts[1]);
		System.out.println("Quads:" + handCounts[2]);
		System.out.println("Full House:" + handCounts[3]);
		System.out.println("Flush:" + handCounts[4]);
		System.out.println("Straight:" + handCounts[5]);
		System.out.println("Trips:" + handCounts[6]);
		System.out.println("Two Pair:" + handCounts[7]);
		System.out.println("One Pair:" + handCounts[8]);
		System.out.println("High Card:" + handCounts[9]);
	}
}
