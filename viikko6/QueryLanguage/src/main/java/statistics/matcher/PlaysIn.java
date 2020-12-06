
package statistics.matcher;

import statistics.Player;

public class PlaysIn implements Matcher {
    private String team;

    public PlaysIn(String team) {
        this.team = team;
    }        
    
    public PlaysIn(Matcher matcher, String string) {
        matcher = new And(matcher);
        this.team = team;
	}

	@Override
    public boolean matches(Player p) {
        return p.getTeam().contains(team);
    }
    
}
