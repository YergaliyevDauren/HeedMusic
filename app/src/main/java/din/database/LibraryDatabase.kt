package din.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import din.heed_music.database.RecentSearch

@Database(entities = [LibrarySong::class, Album::class, Artist::class, RecentSearch::class], version=1, exportSchema = false)
abstract class LibraryDatabase : RoomDatabase() {

    abstract fun librarySongsDao() : LibrarySongsDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getInstance(
            context: Context) : LibraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "music_library_db")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("database/music_library_db.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}