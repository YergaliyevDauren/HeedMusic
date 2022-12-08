package din.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Artists")
data class Artist(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "artistId")
    val artistId: Long = 0L,
    @ColumnInfo(name = "artistName")
    val artistName: String,
    @ColumnInfo(name = "artistImage")
    val artistImage: String
)