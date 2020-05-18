public class Player {
	private final String name;
	private final Sport sport;

	public Player(String name, Sport sport) {
		this.name = name;
		this.sport = sport;
	}

	public String getPlayerName() {
		return name;
	}

	public Sport getSport() {
		return sport;
	}
}
