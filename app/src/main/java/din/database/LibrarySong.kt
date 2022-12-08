package din.database

import androidx.room.*

@Entity(tableName = "LibrarySongs")
data class LibrarySong(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "songId")
    var songId: Long = 0L,

    @ColumnInfo(name="songTitle")
    val songTitle: String,

    @ColumnInfo(name="artistName")
    val artistName: String,

    @ColumnInfo(name="albumName")
    val albumName: String,

    @ColumnInfo(name="coverPath")
    val coverPath: String
)