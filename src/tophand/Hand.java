package tophand;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;;

public class Hand {

	private boolean twopair = false;
	private Card[] hand = new Card[5]; // array of Card objects
	public String faces = "";
	private String suits = "";
	private String[] cardFace = {"A", "2", "3", "4", "5", "6", "7", "8", "9",
			"T", "J", "Q", "K"};
	private String[] cardSuit = {"C", "D", "H", "S"};

	// Hand constructor (uses deck object)
	public Hand(DeckOfCards deck) {
		// deal the hand, store in hand object
		for (int i = 0; i < hand.length; i++) {
			hand[i] = deck.dealCard();
		}
	}

	// check for Royal Flush
	public boolean isRoyalFlush(String faces, String suits) {
		return (faces.equals("TJQKA") && isFlush(suits));
	}

	// check for Straight Flush
	public boolean isStraightFlush(String faces, String suits) {
		return isStraight(faces) && isFlush(suits);
	}

	// check for 4 of a kind
	public boolean isQuads(String faces) {
		int[] count = new int[13];
		count = cardCount(faces);
		return (count[0] == 4) || (count[1] == 4) || (count[2] == 4)
				|| (count[3] == 4) || (count[4] == 4) || (count[5] == 4)
				|| (count[6] == 4) || (count[7] == 4) || (count[8] == 4)
				|| (count[9] == 4) || (count[10] == 4) || (count[11] == 4)
				|| (count[12] == 4);
	}

	public boolean isFullHouse(String faces) {
		return (isTrips(faces) && isPair(faces));
	}

	// check for regular flush
	public boolean isFlush(String suits) {
		int count = 0;
		for (int i = 0; i < 4; i++) {
			count = StringUtils.countMatches(suits, cardSuit[i]);
			if (count == 5)
				return true;

		}
		return false;
	}

	// check for regular Straight
	public boolean isStraight(String faces) {

		return (faces.contains("A2345")) || (faces.contains("23456"))
				|| (faces.contains("23456")) || (faces.contains("34567"))
				|| (faces.contains("45678")) || (faces.contains("56789"))
				|| (faces.contains("6789T")) || (faces.contains("789TJ"))
				|| (faces.contains("89TJQ")) || (faces.contains("9TJQK"))
				|| (faces.contains("TJQKA"));
	}

	// check for 3 of a kind
	public boolean isTrips(String faces) {
		int[] count = new int[13];
		count = cardCount(faces);
		return (count[0] == 3) || (count[1] == 3) || (count[2] == 3)
				|| (count[3] == 3) || (count[4] == 3) || (count[5] == 3)
				|| (count[6] == 3) || (count[7] == 3) || (count[8] == 3)
				|| (count[9] == 3) || (count[10] == 3) || (count[11] == 3)
				|| (count[12] == 3);
	}

	public boolean isTwoPair(String faces) {
		cardCount(faces);
		
		return getTwoPair();
	}

	// check for pair
	public boolean isPair(String faces) {
		int[] count = new int[13];
		count = cardCount(faces);
		return (count[0] == 2) || (count[1] == 2) || (count[2] == 2)
				|| (count[3] == 2) || (count[4] == 2) || (count[5] == 2)
				|| (count[6] == 2) || (count[7] == 2) || (count[8] == 2)
				|| (count[9] == 2) || (count[10] == 2) || (count[11] == 2)
				|| (count[12] == 2);
	}

	// sort the face values using Ace as a low card
	public String sortAceHigh(String faces) {
		// since I can't sort number and letters together, i must change the
		// face cards to regular letters.
		faces = faces.replaceAll("T", "B");
		faces = faces.replaceAll("J", "C");
		faces = faces.replaceAll("Q", "D");
		faces = faces.replaceAll("K", "E");
		faces = faces.replaceAll("A", "F");
		// convert to char array, sort and then store sorted cards in variable
		// sortedfacesAcehigh
		char[] myFaces = new char[5];
		myFaces = faces.toCharArray();
		Arrays.sort(myFaces);
		String sortedFacesAceHigh = "";
		sortedFacesAceHigh = new String(myFaces);
		// after sorting is complete, switch the letters back to their original
		// face values
		sortedFacesAceHigh = sortedFacesAceHigh.replaceAll("B", "T");
		sortedFacesAceHigh = sortedFacesAceHigh.replaceAll("C", "J");
		sortedFacesAceHigh = sortedFacesAceHigh.replaceAll("D", "Q");
		sortedFacesAceHigh = sortedFacesAceHigh.replaceAll("E", "K");
		sortedFacesAceHigh = sortedFacesAceHigh.replaceAll("F", "A");
		return sortedFacesAceHigh;
	} // end sortedFacesHigh

	// identical except sorting with Ace high
	public String sortAceLow(String faces) {
		faces = faces.replaceAll("A", "1");
		faces = faces.replaceAll("T", "B");
		faces = faces.replaceAll("J", "C");
		faces = faces.replaceAll("Q", "D");
		faces = faces.replaceAll("K", "E");
		char[] myFaces = new char[5];
		faces.getChars(0, 5, myFaces, 0);
		Arrays.sort(myFaces);
		String sortedFacesLow = "";
		sortedFacesLow = new String(myFaces);
		sortedFacesLow = sortedFacesLow.replaceAll("B", "T");
		sortedFacesLow = sortedFacesLow.replaceAll("C", "J");
		sortedFacesLow = sortedFacesLow.replaceAll("D", "Q");
		sortedFacesLow = sortedFacesLow.replaceAll("E", "K");
		sortedFacesLow = sortedFacesLow.replaceAll("1", "A");
		return sortedFacesLow;
	} // end sortFacesLow

	public boolean getTwoPair() {
		return twopair;
	}

	public void setTwoPair(boolean twopair) {
		this.twopair = twopair;
	}

	// parse the hand object to get just the card suits and store in the
	// variable suits
	public String getSuits() {
		for (int i = 0; i < hand.length; i++) {
			suits = suits + hand[i].toString().substring(1, 2);
		}
		return suits;
	}// end getSuits

	// parse the hand object to get just the card faces and store in the
	// variable faces
	public String getFaces() {
		for (int i = 0; i < hand.length; i++) {
			faces = faces + hand[i].toString().substring(0, 1);
		}
		return faces;
	} // end getFaces

	public int[] cardCount(String faces) {
		int count[] = new int[13];
		int i = 0;
		int numbPairs = 0;
		for (String value : cardFace) {
			count[i] = StringUtils.countMatches(faces, value);
			if (count[i] == 2) {
				numbPairs++;
			}
			i++;
		}
		if (numbPairs == 2) {
			setTwoPair(true);
		}
		return count;
	}// end cardCount
}// end class
