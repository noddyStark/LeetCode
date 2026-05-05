package CompanyWise.Rippling.SongPlaybackAnalyticsByUniqueUsers;

import java.util.*;

public class SongPlaybackAnalyticsByUniqueUsers {
    HashMap<Integer, Song> songIdToSongMap = new HashMap<>();

    // More users → higher rank → comes earlier in TreeSet
    private final TreeSet<Song> rankedSongs = new TreeSet<>(new SongComparator());

    Map<Integer, Deque<PlayEvent>> songIdToEvents = new HashMap<>();

    public void addSong(int songId, String songName) {
        Song song = new Song(songId, songName);
        songIdToSongMap.put(songId, song);
        rankedSongs.add(song);
    }

    // playSong()     -> O(1)
    public void playSong(int userId, int songId) {

        long timeStamp = System.currentTimeMillis();

        Song song = songIdToSongMap.get(songId);

        rankedSongs.remove(song);

        // total play count always increases
        song.totalPlayCount++;

        // unique user count increases only if this user is new for this song
        song.uniqueUserIds.add(userId);

        rankedSongs.add(song);
        /*
        👉 Root = best song (highest priority)
         *            UpTown (3 users)
                  /
           Hello (2 users)
                \
              NewYork (2 users, less plays)
        * */

        songIdToEvents.computeIfAbsent(songId, k -> new LinkedList<>()).offerLast(new PlayEvent(userId, songId, timeStamp));
    }

    // Returns a list of all songs, sorted in descending order based on the number of unique users who have played each song.
    // If two songs have the same number of unique users, they should then be sorted by their total play count in descending order.
    // If both these metrics are equal, sort by songId in ascending order.
    // getAnalytics() -> O(S log S)
    public List<Song> getAnalytics() {

        // Step 1: Get all songs
        List<Song> songs = new ArrayList<>(songIdToSongMap.values());

        // Step 2: Sort using your custom comparator
        // songs.sort(new SongComparator());

        mergeSort(songs, 0, songs.size() - 1, new SongComparator());

        return songs;
    }

    public List<Song> getAnalyticsUsingTreeSet() {
        return new ArrayList<>(rankedSongs);
    }

    public List<Song> getTopKSongs(int K) {

        PriorityQueue<Song> minHeap = new PriorityQueue<>(K, new TopKSongsComparator());

        for (Song song : songIdToSongMap.values()) {

            minHeap.offer(song);

            if (minHeap.size() > K) {
                minHeap.poll(); // remove worst among top K
            }
        }

        // Convert to list
        List<Song> result = new ArrayList<>(minHeap);

        // Final sort (best → worst)
        result.sort(new SongComparator());

        return result;
    }


    public List<Song> getTopSongsLast24h(long currentTime) {
        long windowStart = currentTime - 24 * 60 * 60 * 1000;

        List<Song> result = new ArrayList<>();

        for (Map.Entry<Integer, Deque<PlayEvent>> entry : songIdToEvents.entrySet()) {
            int songId = entry.getKey();

            Deque<PlayEvent> events = entry.getValue();

            // Remove Old Events
            while (!events.isEmpty() && events.peekFirst().timeStamp < windowStart) {
                events.pollFirst();
            }

            // Compute stats for this window
            int playCount = events.size();

            Set<Integer> uniqueUsers = new HashSet<>();

            for (PlayEvent event : events) {
                uniqueUsers.add(event.userId);
            }

            Song song = new Song(songId, songIdToSongMap.get(songId).name);
            song.totalPlayCount = playCount;
            song.uniqueUserIds = uniqueUsers;

            result.add(song);
        }

        result.sort(new SongComparator());

        return result;
    }

    private void mergeSort(List<Song> songs, int left, int right, SongComparator comparator) {

        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(songs, left, mid, comparator);
        mergeSort(songs, mid + 1, right, comparator);
        merge(songs, left, mid, right, comparator);
    }

    private static void merge(List<Song> songs, int left, int mid, int right, SongComparator comparator) {

        int leftArrayLength = mid - left + 1;
        int rightArrayLength = right - mid;

        Song[] leftArray = new Song[leftArrayLength];
        Song[] rightArray = new Song[rightArrayLength];

        for (int i = 0; i < leftArrayLength; i++) {
            leftArray[i] = songs.get(left + i);
        }

        for (int i = 0; i < rightArrayLength; i++) {
            rightArray[i] = songs.get(mid + 1 + i);
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftArrayLength && j < rightArrayLength) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                songs.set(k, leftArray[i]);
                i++;
            } else {
                songs.set(k, rightArray[j]);
                j++;
            }

            k++;
        }


        // Copy remaining left songs
        while (i < leftArrayLength) {
            songs.set(k, leftArray[i]);
            i++;
            k++;
        }

        // Copy remaining right songs
        while (j < rightArrayLength) {
            songs.set(k, rightArray[j]);
            j++;
            k++;
        }

    }
}

/*
*
Bubble Sort    -> O(n²), bad for large data
Selection Sort -> O(n²), bad for large data
Insertion Sort -> O(n²), okay for small/nearly sorted data
Merge Sort     -> O(n log n), reliable
Quick Sort     -> average O(n log n), worst O(n²)
Heap Sort      -> O(n log n), but less intuitive to write cleanly
*
* 1. Real-time ranking (TreeSet)   ⭐⭐⭐⭐⭐
2. Time-window analytics         ⭐⭐⭐⭐
3. Concurrency                   ⭐⭐⭐⭐
4. Distributed design            ⭐⭐⭐
5. HyperLogLog (scale)           ⭐⭐
* */