
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private String birthday;
    private String nationality;
    private int goals;
    private int assists;
    private int points;
    private int penalties;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setNationality(String nationality){
        this.nationality = nationality;
    }
    public String getNationality(){
        return nationality;
    }
    public void setTeam(String team){
        this.team = team;
    }
    public String getTeam(){
        return team;
    }
    public void setGoals(int goals){
        this.goals = goals;
    }
    public int getGoals(){
        return goals;
    }
    public void setAssists(int assists){
        this.assists = assists;
    }
    public int getAssists(){
        return assists;
    }

    public int getPoints(){
        return goals + assists;
    }
    public void setPenalties(int penalties){
        this.penalties = penalties;
    }
    public int getPenalties(){
        return penalties;
    }
    public int compareTo(Player p){
        if(p.getPoints() - this.getPoints() == 0){
        return p.goals - this.goals; 
        }
        return p.getPoints() - this.getPoints();
    }

    @Override
    public String toString() {      
        return String.format("%-20s",name) + " (" + nationality + ") " + team + " " + String.format("%2d",goals) + " + " 
                + String.format("%2d",assists) + " = " + getPoints();
    }
}
