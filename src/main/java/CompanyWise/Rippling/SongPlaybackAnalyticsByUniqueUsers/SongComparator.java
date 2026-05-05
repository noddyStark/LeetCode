package CompanyWise.Rippling.SongPlaybackAnalyticsByUniqueUsers;

import java.util.Comparator;

public class SongComparator implements Comparator<Song> {

    @Override
    public int compare(Song a, Song b) {

        // 1. unique users descending
        // compare(a, b) = -1 → a should come before b
        int uniqueCompare = Integer.compare(
                b.uniqueUserIds.size(),
                a.uniqueUserIds.size()
        );

        if (uniqueCompare != 0) {
            return uniqueCompare;
        }

        // 2. total play count descending
        int playCountCompare = Integer.compare(
                b.totalPlayCount,
                a.totalPlayCount
        );

        if (playCountCompare != 0) {
            return playCountCompare;
        }

        // 3. songId ascending
        return Integer.compare(a.songId, b.songId);
    }
}
