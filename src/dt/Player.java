package dt;

public class Player {
	private Pit[] pits;
	private Mancala mancala;
	private String playerName;

	public Player(String playerName) {
		this.playerName = playerName;
		pits = new Pit[6];
		for(int i = 0; i < 6; i++) {
			pits[i] = new Pit(4);
		}
		mancala = new Mancala(0);
	}

	public String getPlayerName() {
		return playerName;
	}

	public Pit getPit(int pitIndex) {
		return pits[pitIndex];
	}

	public Mancala getMancala() {
		return mancala;
	}

}
