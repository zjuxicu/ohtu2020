package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }

    public String tie(int i) {
        String s = "";
        switch (i) {
            case 0:
                s = "Love-All";
                break;
            case 1:
                s = "Fifteen-All";
                break;
            case 2:
                s = "Thirty-All";
                break;
            case 3:
                s = "Forty-All";
                break;
            default:
                s = "Deuce";
                break;

        }
        return s;
    }

    public String tieBreaker(int m_score1, int m_score2) {
        String s = "";
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1)
            s = "Advantage player1";
        else if (minusResult == -1)
            s = "Advantage player2";
        else if (minusResult >= 2)
            s = "Win for player1";
        else
            s = "Win for player2";

        return s;
    }

    public String getScore() {
        String score = "";
        int tempScore = 0;
        if (m_score1 == m_score2) {
            score = tie(m_score1);
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            score = tieBreaker(m_score1, m_score2);
        } else {
            for (int i = 1; i < 3; i++) {
                if (i == 1)
                    tempScore = m_score1;
                else {
                    score += "-";
                    tempScore = m_score2;
                }
                switch (tempScore) {
                    case 0:
                        score += "Love";
                        break;
                    case 1:
                        score += "Fifteen";
                        break;
                    case 2:
                        score += "Thirty";
                        break;
                    case 3:
                        score += "Forty";
                        break;
                }
            }
        }
        return score;
    }
}