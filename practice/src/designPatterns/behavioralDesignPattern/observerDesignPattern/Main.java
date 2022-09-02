package designPatterns.behavioralDesignPattern.observerDesignPattern;

public class Main {

    public static void main(String args[]){
        AverageScoreDisplay averageScoreDisplay = new AverageScoreDisplay();
        CurrentScoreDisplay currentScoreDisplay = new CurrentScoreDisplay();
        CricketData cricketData = new CricketData(currentScoreDisplay, averageScoreDisplay);
        cricketData.dataChanged();
    }

}
