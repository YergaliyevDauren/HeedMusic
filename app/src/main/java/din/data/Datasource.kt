package din.data

import din.model.Album

class Datasource {
    fun loadSampleAlbums(): List<Album> {
        return listOf(
            Album("Graduation", "Kanye West", ""),
            Album("Avalanches", "Since I Left You", ""),
            Album("The Beatles", "Abbey Road", ""),
            Album("Dawn FM", "The Weeknd", "https://upload.wikimedia.org/wikipedia/en/b/b9/The_Weeknd_-_Dawn_FM.png"),
            Album("DAMN.", "Kendrick Lamar", ""),
            Album("Blonde", "Frank Ocean", ""),
            Album("IGOR", "Tyler, the Creator", ""),
            Album("Some Artist", "Some Song", ""),
            Album("Some Artist", "Some Song", ""),
            Album("Some Artist", "Some Song", ""),
            Album("Some Artist", "Some Song", "")
        )
    }
}