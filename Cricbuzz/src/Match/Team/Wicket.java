package Match.Team;

import Match.Inning.BallDetails;
import Match.Inning.OverDetails;
import Match.Team.Player.PlayerDetails;

public class Wicket {
    public WicketType wicketType;
    public PlayerDetails takeBy;
    public OverDetails overDetails;
    public BallDetails ballDetails;

    public Wicket(WicketType wicketType, PlayerDetails takeBy, OverDetails overDetails, BallDetails ballDetails) {
        this.wicketType = wicketType;
        this.takeBy = takeBy;
        this.overDetails = overDetails;
        this.ballDetails = ballDetails;
    }
}
