package CompanyWise.Rippling.SongPlaybackAnalyticsByUniqueUsers;

public class PlayEvent {
    int userId;
    int songId;
    long timeStamp;

    public PlayEvent(int songId, int userId, long timeStamp) {
        this.songId = songId;
        this.userId = userId;
        this.timeStamp = timeStamp;
    }
}
