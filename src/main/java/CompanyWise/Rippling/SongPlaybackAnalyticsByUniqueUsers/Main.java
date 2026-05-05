package CompanyWise.Rippling.SongPlaybackAnalyticsByUniqueUsers;

import java.util.HashMap;
import java.util.List;

public class Main {

    static void main() {
        SongPlaybackAnalyticsByUniqueUsers system = new SongPlaybackAnalyticsByUniqueUsers();

        system.addSong(1, "Hello");
        system.addSong(2, "NewYork");
        system.addSong(3, "UpTown");
        system.addSong(4, "GoodBye");
        system.addSong(5, "Paradise");

        system.playSong(911, 4);
        system.playSong(911, 5);


        system.playSong(911, 5);
        system.playSong(911, 5);
        system.playSong(911, 5);
        system.playSong(911, 5);
        system.playSong(911, 5);


        system.playSong(123, 1);
        system.playSong(123, 1);

        system.playSong(123, 2);
        system.playSong(123, 3);

        system.playSong(456, 2);
        system.playSong(456, 3);

        system.playSong(789, 3);
        system.playSong(789, 1);

        HashMap<Integer, Song> songIdToSongMap = system.songIdToSongMap;
        System.out.println("songIdToSongMap = " + songIdToSongMap);

        List<Song> analytics = system.getAnalytics();

        for (Song song : analytics) {
            System.out.println(song);
        }


        System.out.println("Top 3 Songs = " + system.getTopKSongs(3));

        System.out.println("TreeSet songs = " + system.getAnalyticsUsingTreeSet());


        System.out.println(system.getTopSongsLast24h(System.currentTimeMillis()));


    }
}
