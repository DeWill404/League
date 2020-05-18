import java.util.ArrayList;
import java.util.Collections;

public class League {
	private ArrayList<Team> teams = new ArrayList<>();
	private final String name;
	private final Sport sport;

	public League(String name, Sport sport) {
		this.name = name;
		this.sport = sport;
	}

	public String getLeagueName() {
		return name;
	}

	public Sport getLeagueSport() {
		return sport;
	}

	// Check if empty
	public boolean isLeagueEmpty() {
		return teams.isEmpty();
	}

	//check if team is present
	public Team isTeamPresent(Team team) {
		// If present the return object
		for (Team item : teams)
			if (item.equals(team))
				return item;
		// If not present return null
		return null;
	}

	// List teams in list
	public void listTeam() {
		int temp=1;
		if (!isLeagueEmpty()) {  // If teams are there
			Collections.sort(teams);
			for (Team item : teams)
				System.out.println(temp+". "+item.getTeamName()+" ("+item.score()+")");
		} else {  // If list is empty
			System.out.println("\"\"There is no Teams in This League \"\"");
		}
	}

	// Add player
	public boolean addTeam(Team team) {
		if (isTeamPresent(team) == null) {
			teams.add(team);
			return true;
		}
		return false;
	}

	// Delete player
	public boolean deleteTeam(Team team) {
		Team index = isTeamPresent(team);  // Get object
		if (index != null) {
			teams.remove(index);
			return true;
		}
		return false;
	}

	// Make match between 2 team
	public void match(Team team1, Team team2, int score1, int score2) {
		team1.matchResult(team2, score1, score2);
	}

}
