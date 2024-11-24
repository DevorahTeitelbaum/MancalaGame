package dt;

import java.util.Scanner;

public class Main {
	public static final String ANSI_RESET = "\u001B[0m"; // Regular text
	public static final String ANSI_PINK = "\u001B[95m"; // Pink -- for player1
	public static final String ANSI_TEAL = "\u001B[36m"; // Teal -- for player2
	public static final String ANSI_PURPLE = "\u001B[35m"; // Purple -- for tie

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		Player player1 = null;
		Player player2 = null;
		player1 = initialisePlayer(keyboard, 1);
		player2 = initialisePlayer(keyboard, 2);

		MancalaGame game = new MancalaGame(player1, player2);
		game.initialiseGame();
		game.printBoard();
		
		playGame(game, keyboard, player1, player2);
		keyboard.close();
	}

	public static Player initialisePlayer(Scanner keyboard, int playerNum) {
		if (playerNum == 1) {
			System.out.print(ANSI_PINK + "Player 1, enter your name: " + ANSI_RESET);
		} else {
			System.out.print(ANSI_TEAL + "Player 2, enter your name: " + ANSI_RESET);
		}
		String name = keyboard.nextLine();
		Player player = new Player(name);
		return player;
	}

	public static void playGame(MancalaGame game, Scanner keyboard, Player player1, Player player2) {
		boolean hasWinner = game.checkWinCondition();
		boolean switchPlayer = false;
		while (!hasWinner) {
			Player player;
			if (!switchPlayer) {
				player = player1;
			} else {
				player = player2;
			}
			System.out.printf("Player %s: Choose pit # to move: %n", player.getPlayerName());
			int pitNum = keyboard.nextInt();
			keyboard.nextLine();
			while (pitNum > 5 || pitNum < 0) {
				System.out.println("Invalid pit... try again!");
				pitNum = keyboard.nextInt();
			}
			if (!switchPlayer) {
				boolean again = game.makeMove(pitNum, 1);
				while (again) {
					System.out.println("going again!");
					System.out.printf("Player %s: Choose pit # to move: %n", player.getPlayerName());
					pitNum = keyboard.nextInt();
					while (pitNum > 5 || pitNum < 0) {
						System.out.println("Invalid pit... try again!");
						pitNum = keyboard.nextInt();
					}
					again = game.makeMove(pitNum, 1);
				}
			} else {
				boolean again = game.makeMove(pitNum, 2);
				while (again) {
					System.out.println("going again!");
					System.out.printf("Player %s: Choose pit # to move: %n", player.getPlayerName());
					pitNum = keyboard.nextInt();
					while (pitNum > 5 || pitNum < 0) {
						System.out.println("Invalid pit... try again!");
						pitNum = keyboard.nextInt();
					}
					again = game.makeMove(pitNum, 2);
				}
			}
			// flip switchPlayer
			if (!switchPlayer) {
				switchPlayer = true;
			} else {
				switchPlayer = false;
			}
			hasWinner = game.checkWinCondition();
		}
		finishGame(game);
	}

	public static void finishGame(MancalaGame game) {
		int winner = game.winner();
		if (winner == 3) {
			System.out.println(ANSI_PURPLE + "Congragulations! It's a tie!" + ANSI_RESET);
		} else if (winner == 1) {
			System.out.println(ANSI_PINK + "Congragulations! Player 1 won!" + ANSI_RESET);
		} else {
			System.out.println(ANSI_TEAL + "Congragulations! Player 2 won!" + ANSI_RESET);
		}
	}
}

/*AI GENERATED UML 
 * class Pit { - stonesCount: int + addStones(numStones: int) +
 * removeAllStones() } class Mancala { - inherits from Pit + scoringLogic() }
 * class Player { - pits: Pit[] - store: Mancala + getPit(pitIndex: int): Pit +
 * getMancala(): Mancala } class MancalaGame { - player1: Player - player2:
 * Player - currentPlayer: Player + initializeGame() + makeMove(pitIndex: int) +
 * checkWinCondition(): boolean } class Main { + main(args: String[]) } Pit --|>
 * Mancala Player "6 pits" --* Pit Player "1 Mancala" -- Mancala MancalaGame -->
 * Player
 * 
 */