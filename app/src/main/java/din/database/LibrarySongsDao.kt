package din.database

import androidx.lifecycle.LiveData
import androidx.room.*
import din.database.Artist
import din.database.LibrarySong

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
}