package com.example.footballclub.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Test;

import com.example.footballclub.domain.Club;
import com.example.footballclub.domain.Player;

public class FootballClubTest {

	ClubManager clubManager = new ClubManager();
	private final static String CLUB_NAME_1 = "Arsenal Londyn";
	private final static int CLUB_FOUNDED_1 = 1889;
	private final static String CLUB_LEAGUE_1 = "Premiere League";

	private final static String CLUB_NAME_2 = "Lech Poznań";
	private final static int CLUB_FOUNDED_2 = 1926;
	private final static String CLUB_LEAGUE_2 = "Polska Ekstraklasa";

	PlayerManager playerManager = new PlayerManager();
	private final static String NAME_1 = "Grzegorz";
	private final static String LASTNAME_1 = "Niciński";
	private final static String POSITION_1 = "Striker";
	private final static int AGE_1 = 35;

	private final static String NAME_2 = "Krzysztof";
	private final static String LASTNAME_2 = "Sobieraj";
	private final static String POSITION_2 = "Defender";
	private final static int AGE_2 = 31;

	@Test
	public void checkClubConnection() {
		assertNotNull(clubManager.getConnection());
	}

	@Test
	public void checkPLayerConnection() {
		assertNotNull(playerManager.getConnection());
	}

	@Test
	public void checkClubAdding() {
		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		clubManager.clearClubs();
		playerManager.clearPlayers();
		assertEquals(1, clubManager.addClub(club));
		// assertEquals(1, clubManager.addClub(club));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertEquals(CLUB_NAME_1, retrievedClub.getName());
		assertEquals(CLUB_FOUNDED_1, retrievedClub.getFounded());
		assertEquals(CLUB_LEAGUE_1, retrievedClub.getLeague());

	}

	@Test
	public void checkPlayerAdding() {
		clubManager.clearClubs();
		playerManager.clearPlayers();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertNotNull(retrievedClub.getId());

		final long clubID = retrievedClub.getId();

		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1,
				clubID);

		playerManager.clearPlayers();
		assertEquals(1, playerManager.addPlayer(player));

		List<Player> players = playerManager.getAllPlayers();
		Player personRetrieved = players.get(0);

		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(LASTNAME_1, personRetrieved.getLastName());
		assertEquals(POSITION_1, personRetrieved.getPosition());
		assertEquals(AGE_1, personRetrieved.getAge());
		assertEquals(clubID, personRetrieved.getClubID());

	}

	@Test
	public void checkPlayerDeleting() {
		clubManager.clearClubs();
		playerManager.clearPlayers();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));
		// assertEquals(1, clubManager.addClub(club));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertNotNull(retrievedClub.getId());

		final long clubID = retrievedClub.getId();

		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1,
				clubID);
		assertEquals(1, playerManager.addPlayer(player));

		List<Player> players = playerManager.getAllPlayers();
		Player pr = players.get(0);

		assertSame(1, playerManager.deletePlayer(pr));
	}

	@Test
	public void checkClubDeleting() {
		clubManager.clearClubs();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertSame(1, clubManager.deleteClub(retrievedClub));
	}

	@Test
	public void checkAllPlayersDeleting() {
		clubManager.clearClubs();
		playerManager.clearPlayers();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));
		// assertEquals(1, clubManager.addClub(club));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertNotNull(retrievedClub.getId());

		final long clubID = retrievedClub.getId();

		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1,
				clubID);
		assertEquals(1, playerManager.addPlayer(player));
		assertEquals(1, playerManager.addPlayer(player));
		assertEquals(1, playerManager.addPlayer(player));

		Player player2 = new Player(NAME_2, LASTNAME_2, POSITION_2, AGE_2,
				clubID);
		assertEquals(1, playerManager.addPlayer(player2));
		assertEquals(1, playerManager.addPlayer(player2));
		assertEquals(1, playerManager.addPlayer(player2));

		List<Player> players = playerManager.getAllPlayers();

		assertSame(6, players.size());

		playerManager.clearPlayers();

		players = playerManager.getAllPlayers();

		assertSame(0, players.size());
	}

	@Test
	public void checkAllClubsDeleting() {
		playerManager.clearPlayers();
		clubManager.clearClubs();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));
		assertEquals(1, clubManager.addClub(club));
		assertEquals(1, clubManager.addClub(club));

		Club club2 = new Club(CLUB_NAME_2, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club2));
		assertEquals(1, clubManager.addClub(club2));
		assertEquals(1, clubManager.addClub(club2));

		List<Club> clubs = clubManager.getAllClubs();
		assertSame(6, clubs.size());

		clubManager.clearClubs();

		clubs = clubManager.getAllClubs();

		assertSame(0, clubs.size());
	}

	@Test
	public void checkPlayerUpdate() {

		clubManager.clearClubs();
		playerManager.clearPlayers();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));

		Club club2 = new Club(CLUB_NAME_2, CLUB_FOUNDED_2, CLUB_LEAGUE_2);
		assertEquals(1, clubManager.addClub(club2));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertNotNull(retrievedClub.getId());

		final long clubID = retrievedClub.getId();

		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1,
				clubID);

		assertEquals(2, clubs.size());

		Club retrievedClub2 = clubs.get(1);
		assertNotNull(retrievedClub2.getId());

		final long clubID2 = retrievedClub2.getId();

		assertEquals(1, playerManager.addPlayer(player));

		List<Player> players = playerManager.getAllPlayers();
		Player personRetrieved = players.get(0);

		assertEquals(clubID, personRetrieved.getClubID());

		assertEquals(1, playerManager.updatePlayer(personRetrieved, clubID2));

		players = playerManager.getAllPlayers();
		Player pr2 = players.get(0);

		assertEquals(clubID2, pr2.getClubID());

	}

	@Test
	public void getClubPlayers() {

		clubManager.clearClubs();
		playerManager.clearPlayers();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));

		Club club2 = new Club(CLUB_NAME_2, CLUB_FOUNDED_2, CLUB_LEAGUE_2);
		assertEquals(1, clubManager.addClub(club2));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);
		assertNotNull(retrievedClub.getId());

		Club retrievedClub2 = clubs.get(1);
		assertNotNull(retrievedClub2.getId());

		final long clubID = retrievedClub.getId();
		final long clubID2 = retrievedClub2.getId();

		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1,
				clubID);
		Player player2 = new Player(NAME_2, LASTNAME_2, POSITION_1, AGE_1,
				clubID);
		Player player3 = new Player(NAME_1, LASTNAME_2, POSITION_2, AGE_2,
				clubID);
		Player otherClubPLayer = new Player(NAME_1, LASTNAME_2, POSITION_2,
				AGE_2, clubID2);

		playerManager.clearPlayers();

		assertEquals(1, playerManager.addPlayer(player));
		assertEquals(1, playerManager.addPlayer(player2));
		assertEquals(1, playerManager.addPlayer(player3));
		assertEquals(1, playerManager.addPlayer(otherClubPLayer));

		List<Player> players = playerManager.getAllPlayers();

		assertEquals(4, players.size());

		List<Player> clubPlayers = playerManager.getClubPlayers(clubID);

		assertEquals(3, clubPlayers.size());

		for (Player p : clubPlayers) {
			assertEquals(clubID, p.getClubID());
		}
	}

	@Test
	public void checkSelectOne() {
		clubManager.clearClubs();
		playerManager.clearPlayers();

		Club club = new Club(CLUB_NAME_1, CLUB_FOUNDED_1, CLUB_LEAGUE_1);
		assertEquals(1, clubManager.addClub(club));

		List<Club> clubs = clubManager.getAllClubs();
		Club retrievedClub = clubs.get(0);

		assertNotNull(retrievedClub.getId());
		final long clubID = retrievedClub.getId();

		Player player = new Player(NAME_1, LASTNAME_1, POSITION_1, AGE_1,
				clubID);

		assertEquals(1, playerManager.addPlayer(player));

		List<Player> players = playerManager.getAllPlayers();
		Player pr = players.get(0);

		long id = pr.getId();

		assertNotNull(playerManager.getPlayer(id));

		Player personRetrieved = playerManager.getPlayer(id);

		assertEquals(NAME_1, personRetrieved.getName());
		assertEquals(LASTNAME_1, personRetrieved.getLastName());
		assertEquals(POSITION_1, personRetrieved.getPosition());
		assertEquals(AGE_1, personRetrieved.getAge());
		assertEquals(clubID, personRetrieved.getClubID());

	}

}
