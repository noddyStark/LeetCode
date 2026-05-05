package CompanyWise.Rippling.SongPlaybackAnalyticsByUniqueUsers;

import java.util.Comparator;

public class TopKSongsComparator implements Comparator<Song> {

    @Override
    public int compare(Song a, Song b) {

        int uniqueCompare = Integer.compare(a.uniqueUserIds.size(), b.uniqueUserIds.size());

        if (uniqueCompare != 0) return uniqueCompare;

        int playCompare = Integer.compare(a.totalPlayCount, b.totalPlayCount);

        if (playCompare != 0) return playCompare;

        return Integer.compare(b.songId, a.songId);
    }
}
