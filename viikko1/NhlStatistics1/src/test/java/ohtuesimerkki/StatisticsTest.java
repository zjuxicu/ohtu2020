package ohtuesimerkki;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class StatisticsTest {

    Reader readerStub = new Reader() {

        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri", "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };

    Statistics stats;

    @Before
    public void setUp() {
        // luodaan Statistics-olio joka k‰ytt‰‰ "stubia"
        stats = new Statistics(readerStub);
    }

    @Test
    public void kukaaneiTampasta() {
        assertEquals(stats.team("TBL"), new ArrayList<Player>());

    }

    @Test
    public void lemieuxPIT() throws Exception {
        assertEquals(stats.team("PIT").size(), 1);
    }

    @Test
    public void hakuToimii() {
        assertNull(stats.search("Peltonen"));
        assertNotNull(stats.search("Kurri"));
    }

    @Test
    public void porssiSkulaa() {
        assertNotNull(stats.topScorers(1));
    }

}
