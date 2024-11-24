package dt;

public class MancalaGame {
	private Player player1;
	private Player player2;
	private Player currPlayer;

	public static final String ANSI_RESET = "\u001B[0m"; // Regular text
	public static final String ANSI_PINK = "\u001B[95m"; // Pink -- for player1
	public static final String ANSI_TEAL = "\u001B[36m"; // Teal -- for player2

	public MancalaGame(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
	}

	public void initialiseGame() {

	}

	public boolean makeMove(int pitIndex, int playerNumber) {
		Player otherPlayer;
		if (playerNumber == 1) {
			currPlayer = player1;
			otherPlayer = player2;
		} else {
			currPlayer = player2;
			otherPlayer = player1;
		}

		int stones = currPlayer.getPit(pitIndex).getStonesCount();
		currPlayer.getPit(pitIndex).removeAllStones();
		// update player side
		int currPitIndex = pitIndex + 1;

		while (stones > 0) {
			while (currPitIndex < 5 && stones > 1) {
				currPlayer.getPit(currPitIndex).addStones(1);
				currPitIndex++;
				stones--;
			}
			// don't want to increase index the last time.
			if (currPitIndex == 5 && stones > 0) {
				currPlayer.getPit(currPitIndex).addStones(1);
				stones--;
			}
			// Stealing logic
			if (stones == 0 && currPlayer.getPit(currPitIndex).getStonesCount() == 1) {
				int oppositePitIndex = 5 - currPitIndex;
				int stolenStones = otherPlayer.getPit(oppositePitIndex).getStonesCount();
				otherPlayer.getPit(oppositePitIndex).removeAllStones();
				currPlayer.getMancala().addStones(stolenStones);
			}
			currPitIndex = 0;
			if (stones > 0) {
				currPlayer.getMancala().addStones(1);
				stones--;
				if (stones == 0) {
					printBoard();
					return true;
				}
				while (currPitIndex < 6 && stones > 0) {
					otherPlayer.getPit(currPitIndex).addStones(1);
					currPitIndex++;
					stones--;
				}

			}
		}

		printBoard();
		return false;
	}

	public boolean checkWinCondition() {
		boolean player1finish = true;
		boolean player2finish = true;
		for (int i = 0; i < 6; i++) {
			if (player1.getPit(i).getStonesCount() > 0) {
				player1finish = false;
			}
			if (player2.getPit(i).getStonesCount() > 0) {
				player2finish = false;
			}
		}
		return (player1finish || player2finish);
	}

	public void printBoard() {
		System.out.println();
		System.out.println(ANSI_PINK + "\t\t Player 1");
		for (int i = 0; i < 6; i++) {
			System.out.print("   " + i);
		}
		System.out.println();
		for (int i = 0; i < 6; i++) {
			System.out.print("   " + player1.getPit(i).getStonesCount());
		}
		System.out.print(ANSI_TEAL + "\n" + player2.getMancala().getStonesCount() + "\t\t\t  " + ANSI_PINK
				+ player1.getMancala().getStonesCount() + "\n");
		for (int i = 5; i > -1; i--) {
			System.out.print("   " + ANSI_TEAL + player2.getPit(i).getStonesCount());
		}
		System.out.println();
		for (int i = 5; i > -1; i--) {
			System.out.print("   " + i);
		}
		System.out.println();
		System.out.println("\t Player 2" + ANSI_RESET);
		System.out.println();
	}

	public int winner() {
		int p1total = player1.getMancala().getStonesCount();
		int p2total = player2.getMancala().getStonesCount();
		if (p1total > p2total) {
			return 1;
		} else if (p1total < p2total) {
			return 2;
		} else {
			return 3;
		}
	}
}
