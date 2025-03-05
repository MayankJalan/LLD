package Match;

public class T20Match implements MatchType{
    @Override
    public int noOfOver() {
        return 20;
    }

    @Override
    public int maxOversCountBowlers() {
        return 4;
    }
}
