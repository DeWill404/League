import java.util.ArrayList;

public class Team implements Comparable<Team> {
	private ArrayList<Player> players = new ArrayList<>();
	private final String name;
	private final Sport sport;
	private int win=0, loss=0, tied=0;

	public Team(String name, Sport sport) {
		this.name = name;
		this.sport = sport;
	}

	// Getters
	public String getTeamName() {
		return name;
	}
	public Sport getTeamSport() {
		return sport;
	}

	// Check if empty
	public boolean isTeamEmpty() {
		return players.isEmpty();
	}

	//check if player is present
	public Player isPlayerPresent(Player player) {
		// If present the return object
		for (Player item : players)
			if (item.equals(player))
				return item;
		// If not present return null
		return null;
	}

	// List players in list
	public void listPlayer() {
		if (!isTeamEmpty()) {  // If players are there
			for (Player item : players)
				System.out.println(item.getPlayerName());
		} else {  // If list is empty
			System.out.println("\"\"There is no PLAYERS in This team \"\"");
		}
	}

	// Add player
	public boolean addPlayer(Player player) {
		if (isPlayerPresent(player) == null) {
			players.add(player);
			return true;
		}
		return false;
	}

	// Delete player
	public boolean deletePlayer(Player player) {
		Player index = isPlayerPresent(player);  // Get object
		if (index != null) {
			players.remove(index);
			return true;
		}
		return false;
	}

	// Play a match
	public void matchResult(Team opponent, int ourScore, int theirScore) {
		// Increment win, loss, tied
		if (ourScore > theirScore) {
			win++;
		} else if (theirScore > ourScore) {
			loss++;
		} else {
			tied++;
		}
		// Update opponent result
		if (opponent != null) {
			// null to ensure no infinite looping
			opponent.matchResult(null, theirScore, ourScore);
		}
	}

	// Get score
	public int score() {
		return 2*win-loss+tied;
	}

	// Compare score to get rank
	@Override
	public int compareTo(Team team) {
		if (this.score() > team.score()) {
			return -1;
		} else if (this.score() < team.score()) {
			return 1;
		} else {
			return 0;
		}
	}
}
