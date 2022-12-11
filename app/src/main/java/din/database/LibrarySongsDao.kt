package din.database

import androidx.lifecycle.LiveData
import androidx.room.*
import din.database.Artist
import din.database.LibrarySong
import din.heed_music.database.RecentSearch

@Dao
interface LibrarySongsDao {
    @Insert
    suspend fun insert(song: LibrarySong)

    @Insert
    fun insertData(data: List<LibrarySong>)

    @Update
    suspend fun update(song: LibrarySong)

    @Query("SELECT * FROM LibrarySongs WHERE songId = :key")
    suspend fun get(key: Long): LibrarySong

    @Query("SELECT * FROM LibrarySongs ORDER BY songId DESC")
    fun getAllLibSongs(): LiveData<List<LibrarySong>>

    @Query("SELECT * FROM Albums ORDER BY albumId DESC")
    fun getAllAlbums(): LiveData<List<Album>>

    @Query("SELECT * FROM LibrarySongs s, Albums a WHERE s.albumName = a.albumName AND a.albumId = :albumId")
    fun getAlbumSongs(albumId: Long): LiveData<List<LibrarySong>>

    @Query("SELECT * FROM Albums WHERE albumId = :albumId")
    fun getAlbumById(albumId: Long): LiveData<Album>

    @Query("SELECT * FROM Artists ORDER BY artistName")
    fun getAllLibArtists(): LiveData<List<Artist>>

    @Query("SELECT a.albumId, a.albumName, a.artistName, a.coverPath " +
            "FROM Albums a, LibrarySongs s WHERE s.albumName = a.albumName " +
            "GROUP BY s.albumName ORDER BY songId DESC")
    fun getRecentSongsByAlbums(): LiveData<List<Album>>

    @Query("SELECT * FROM LibrarySongs WHERE songTitle LIKE :pattern LIMIT 20")
    fun getSongsLikePattern(pattern: String?) : List<LibrarySong>

    @Query("SELECT * FROM RecentSearches ORDER BY searchId")
    fun getRecentSearches() : LiveData<List<RecentSearch>>

    @Query("INSERT INTO RecentSearches VALUES (NULL,:search)")
    suspend fun insertRecentSearch(search: String)
}