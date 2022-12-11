package din.heed_music.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RecentSearches")
data class RecentSearch (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "searchId")
    val searchId: Long = 0L,

    @ColumnInfo(name = "searchText")
    val searchText: String
)