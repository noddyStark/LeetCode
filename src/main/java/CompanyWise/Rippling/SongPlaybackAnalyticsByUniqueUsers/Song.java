package CompanyWise.Rippling.SongPlaybackAnalyticsByUniqueUsers;

import java.util.HashSet;
import java.util.Set;

public class Song {

    int songId;
    String name;

    int totalPlayCount;
    Set<Integer> uniqueUserIds;

    public Song(int songId, String name) {
        this.songId = songId;
        this.name = name;
        this.totalPlayCount = 0;
        this.uniqueUserIds = new HashSet<>();
    }

    @Override
    public String toString() {
        return "Song{" +
                "songId=" + songId +
                ", name='" + name + '\'' +
                ", totalPlayCount=" + totalPlayCount +
                ", uniqueUserIds=" + uniqueUserIds +
                '}';
    }
}
