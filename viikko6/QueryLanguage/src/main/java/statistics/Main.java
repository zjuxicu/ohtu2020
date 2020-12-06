package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));
          
        /*Matcher m = new And( new HasAtLeast(5, "goals"),
                             new HasAtLeast(5, "assists"),
                             new PlaysIn("PHI")
        );
        */
        Matcher m = new And( 
        new Not( new HasAtLeast(1, "goals") ), 
        new PlaysIn("NYR")
        );
        System.out.println(stats.matches(new All()).size());
        for (Player player : stats.matches(m)) {
            System.out.println(player);
        }
    }
}
