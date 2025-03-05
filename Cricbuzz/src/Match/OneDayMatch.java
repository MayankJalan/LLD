package Match;

public class OneDayMatch implements MatchType{
    @Override
    public int noOfOver() {
        return 50;
    }

    @Override
    public int maxOversCountBowlers() {
        return 10;
    }
}
