package cs5800.sofwareEngineering.hw4.model.q2.song;

import java.util.List;

public interface SongService {
    Song searchById(Integer songId);
    List<Song> searchByTitle(String songTitle);
    List<Song> searchByAlbum(String songAlbum);
}
