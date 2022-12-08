package din.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Albums")
data class Album(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "albumId")
    val albumId: Long = 0L,
    @ColumnInfo(name = "albumName")
    val albumName: String,
    @ColumnInfo(name = "artistName")
    val artistName: String,
    @ColumnInfo(name = "coverPath")
    val coverPath: String
)