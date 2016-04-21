package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else player2Score += 1;
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return scoreEqual();
        } else if (player1Score >= 4 || player2Score >= 4) {
            return scoreOverFour();
        } else {
            return scoreNormal();
        }
    }

    public String scoreNormal() {
        return getScoreText(player1Score) + "-" + getScoreText(player2Score);
    }

    public String getScoreText(int scoreNumber) {
        switch (scoreNumber) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            default:
                return "Forty";
        }
    }

    public String scoreOverFour() {
        int scoreDifference = player1Score - player2Score;
        if (Math.abs(scoreDifference) == 1) {
            return advantageText(scoreDifference);
        }
        return winText(scoreDifference);
    }
    
    private String winText(int scoreDifference) {
        if (scoreDifference > 1) return "Win for player1";
        return "Win for player2";
    }
    
    
    private String advantageText(int scoreDifference) {
        if (scoreDifference == 1) return "Advantage player1";
        return "Advantage player2";
    }

    public String scoreEqual() {
        if (player1Score < 4) {
            return getScoreText(player1Score) + "-All";
        } else {
            return "Deuce";
        }
    }
}
