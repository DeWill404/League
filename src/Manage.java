import java.util.ArrayList;

public class Manage {
	// Arraylist for all Class
	private ArrayList<Sport> sports = new ArrayList<>();
	private ArrayList<Player> players = new ArrayList<>();
	private ArrayList<Team> teams = new ArrayList<>();
	private ArrayList<League> leagues = new ArrayList<>();


	//////////////////////// SPORT SECTION  /////////////////////////
	// check if sport is present
	public Sport isPresentInSport(String name) {
		// If present the return object
		for (Sport item : sports)
			if (item.getName().equals(name))
				return item;
		// If not present return null
		return null;
	}

	// Check if sport object is empty
	public boolean isSportEmpty() {
		return sports.isEmpty();
	}

	// List sports in list
	public void listSport() {
		if (!isSportEmpty()) {  // If sports are there
			for (Sport item : sports)
				System.out.println("*" + item.getName());
		} else {  // If list is empty
			System.out.println("\"\"There is no SPORT in ENTRY \"\"");
		}
	}

	// Add sport
	public boolean addSport(String s) {
		if (isPresentInSport(s) == null) {
			sports.add(new Sport(s));
			return true;
		}
		return false;
	}

	// Delete sport
	public boolean deleteSport(String s) {
		Sport index = isPresentInSport(s);  // Get object
		if (index != null) {
			sports.remove(index);
			return true;
		}
		return false;
	}


	//////////////////////// PLAYER SECTION /////////////////////////
	//check if player is present
	public Player isPresentInPlayer(String name, Sport sport) {
		// If present the return object
		for (Player item : players)
			if (item.getPlayerName().equals(name) && item.getSport().equals(sport))
				return item;
		// If not present return null
		return null;
	}

	// Check if player object is empty
	public boolean isPlayerEmpty() {
		return players.isEmpty();
	}

	// List players in list
	public void listPlayer() {
		if (!isPlayerEmpty()) {  // If players are there
			for (Player item : players)
				System.out.println("*" + item.getPlayerName() + " (" + item.getSport().getName() + ")");
		} else {  // If list is empty
			System.out.println("\"\"There is no PLAYERS in ENTRY \"\"");
		}
	}

	// Add player
	public boolean addPlayer(String s, Sport sport) {
		if (isPresentInPlayer(s, sport) == null) {
			players.add(new Player(s, sport));
			return true;
		}
		return false;
	}

	// Delete player
	public boolean deletePlayer(String s, Sport sport) {
		Player index = isPresentInPlayer(s, sport);  // Get object
		if (index != null) {
			players.remove(index);
			return true;
		}
		return false;
	}


	//////////////////////// TEAM SECTION   /////////////////////////
 	//check if team is present
	public Team isTeamPresent(String name, Sport sport) {
		// If present the return object
		for (Team item : teams)
			if (item.getTeamName().equals(name) && item.getTeamSport().equals(sport))
				return item;
		// If not present return null
		return null;
	}

	// If team list is empty
	public boolean isNoTeam() {
		return teams.isEmpty();
	}

	// Add team
	public boolean addNewTeam(String name, Sport sport) {
		if (isTeamPresent(name, sport) == null) {   // If new team is entered
			teams.add(new Team(name, sport));
			return true;
		} else {  // If team is already present
			return false;
		}
	}

	// remove team
	public boolean deletePrevTeam(String name, Sport sport) {
		Team index = isTeamPresent(name, sport);
		if (index != null) {   // If team is exist
			teams.remove(index);
			return true;
		} else {  // If team is not present
			return false;
		}
	}

	// List all teams
	public void listAllTeam() {
		if (!isNoTeam()) {  // If teams are there
			for (Team item : teams)
				System.out.println("*" + item.getTeamName() + " (" + item.getTeamSport().getName() + ")");
		} else {  // If list is empty
			System.out.println("\"\"There is no Teams in ENTRY \"\"");
		}
	}

	//Manage: Check if team object is empty
	public boolean isTeamEmpty(Team team) {
		return team.isTeamEmpty();
	}

	//Manage: List teams in list
	public void listPlayerInTeam(Team team) {
		team.listPlayer();
	}

	//Manage: Add team
	public boolean addPlayerInTeam(Team team, Player player) {
		// Check if team and player have same sport
		if (team.getTeamSport().equals(player.getSport())) {
			return team.addPlayer(player);
		} else {
			return false;
		}
	}

	//Manage:  Delete team
	public boolean deletePlayerFromTeam(Team team, Player player) {
		return team.deletePlayer(player);
	}


	//////////////////////// LEAGUE SECTION /////////////////////////
	//check if league is present
	public League isLeaguePresent(String name, Sport sport) {
		// If present the return object
		for (League item : leagues)
			if (item.getLeagueName().equals(name) && item.getLeagueSport().equals(sport))
				return item;
		// If not present return null
		return null;
	}

	// If league list is empty
	public boolean isNoLeague() {
		return leagues.isEmpty();
	}

	// Add league
	public boolean addNewLeague(String name, Sport sport) {
		if (isTeamPresent(name, sport) == null) {   // If new league is entered
			leagues.add(new League(name, sport));
			return true;
		} else {  // If league is already present
			return false;
		}
	}

	// remove league
	public boolean deletePrevLeague(String name, Sport sport) {
		League index = isLeaguePresent(name, sport);
		if (index != null) {   // If league is exist
			leagues.remove(index);
			return true;
		} else {  // If league is not present
			return false;
		}
	}

	// List all leagues
	public void listAllLeague() {
		if (!isNoLeague()) {  // If leagues are there
			for (League item : leagues)
				System.out.println("*" + item.getLeagueName() + " (" + item.getLeagueSport().getName() + ")");
		} else {  // If list is empty
			System.out.println("\"\"There is no Teams in ENTRY \"\"");
		}
	}

	//Manage: Check if league object is empty
	public boolean isLeagueEmpty(Team league) {
		return league.isTeamEmpty();
	}

	//Manage: List leagues in list
	public void listTeamInLeague(League league) {
		league.listTeam();
	}

	//Manage: Add league
	public boolean addTeamInLeague(League league, Team team) {
		// Check if league and team have same sport
		if (league.getLeagueSport().equals(team.getTeamSport())) {
			return league.addTeam(team);
		} else {
			return false;
		}
	}

	//Manage:  Delete league
	public boolean deleteTeamFromLeague(League league, Team team) {
		return league.deleteTeam(team);
	}

	//Manage: make match
	public int makeMatch(League league, Team team1, Team team2, int s1, int s2) {
		league.match(team1, team2, s1, s2);
		if (s1 > s2) {
			return 1;
		} else if (s2 < s1) {
			return -1;
		} else {
			return 0;
		}
	}
}
