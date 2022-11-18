package din.data

import din.model.Album

class Datasource {
    val sampleAlbums = listOf(
        Album("Graduation", "Kanye West", "https://is3-ssl.mzstatic.com/image/thumb/Music114/v4/db/ce/e7/dbcee73f-015e-d994-abe4-58fbdfd0569c/00602577027864.rgb.jpg/600x600bb.jpg"),
        Album("Since I Left You", "Avalanches", "https://is5-ssl.mzstatic.com/image/thumb/Music124/v4/dc/38/47/dc3847df-0457-0c03-467c-cf4d1ac57896/19UMGIM71485.rgb.jpg/600x600bb.jpg"),
        Album("Abbey Road", "The Beatles", "https://is4-ssl.mzstatic.com/image/thumb/Music114/v4/f6/db/94/f6db94bd-35f8-2806-6459-dee4fbd85c68/19UMGIM68354.rgb.jpg/600x600bb.jpg"),
        Album("Dawn FM", "The Weeknd", "https://upload.wikimedia.org/wikipedia/en/b/b9/The_Weeknd_-_Dawn_FM.png"),
        Album("DAMN.", "Kendrick Lamar", "https://is2-ssl.mzstatic.com/image/thumb/Music112/v4/86/c9/bb/86c9bb30-fe3d-442e-33c1-c106c4d23705/17UMGIM88776.rgb.jpg/600x600bb.jpg"),
        Album("Blonde", "Frank Ocean", "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/bb/45/68/bb4568f3-68cd-619d-fbcb-4e179916545d/BlondCover-Final.jpg/600x600bb.jpg"),
        Album("IGOR", "Tyler, the Creator", "https://is2-ssl.mzstatic.com/image/thumb/Music125/v4/0c/06/05/0c060581-6242-6a2a-a677-20170f2cf8da/886447710180.jpg/600x600bb.jpg"),
        Album("Wildflower", "Avalanches", "https://is1-ssl.mzstatic.com/image/thumb/Music126/v4/39/11/a3/3911a3c0-0449-eef2-3646-043a8159087c/16UMGIM31859.rgb.jpg/600x600bb.jpg"),
        Album("Late Registration", "Kanye West", "https://is5-ssl.mzstatic.com/image/thumb/Music115/v4/00/68/13/006813b3-9ca1-2e6f-98df-4ef78cd6cb49/06UMGIM20452.rgb.jpg/600x600bb.jpg"),
        Album("Starboy", "The Weeknd", "https://is4-ssl.mzstatic.com/image/thumb/Music115/v4/e2/61/f8/e261f8c1-73db-9a7a-c89e-1068f19970e0/16UMGIM67863.rgb.jpg/600x600bb.jpg"),
        Album("After Hours", "The Weeknd", "https://is5-ssl.mzstatic.com/image/thumb/Music125/v4/6f/bc/e6/6fbce6c4-c38c-72d8-4fd0-66cfff32f679/20UMGIM12176.rgb.jpg/600x600bb.jpg"),
        Album("Because The Internet", "Childish Gambino", "https://is1-ssl.mzstatic.com/image/thumb/Music112/v4/28/a0/7a/28a07a5e-f81b-b2ca-d87a-49541f541e14/44003173460.jpg/600x600bb.jpg"),
        Album("Camp", "Childish Gambino", "https://is4-ssl.mzstatic.com/image/thumb/Music114/v4/a4/b9/8a/a4b98af1-3162-6e1f-aaa0-f4567e6da810/892038002398_1.jpg/600x600bb.jpg"),
        Album("Jesus is King", "Kanye West", "https://is5-ssl.mzstatic.com/image/thumb/Music113/v4/21/fd/d3/21fdd3d4-0c00-53ef-3903-d0569c49a812/19UMGIM89397.rgb.jpg/600x600bb.jpg"),
        Album("Man on the Moon", "Kid Cudi", "https://is2-ssl.mzstatic.com/image/thumb/Music115/v4/dd/f5/50/ddf55058-b181-4099-bc12-4862b800cf96/09UMGIM33419.rgb.jpg/600x600bb.jpg"),
        Album("MM..FOOD", "MF DOOM", "https://is4-ssl.mzstatic.com/image/thumb/Music125/v4/b6/18/db/b618dbd0-49c8-e88a-6523-0a49c55258dc/cover.jpg/600x600bb.jpg"),
        Album("Ready to Die", "The Notorious B.I.G", "https://is5-ssl.mzstatic.com/image/thumb/Features125/v4/bb/ac/73/bbac7336-a1b4-9111-31cd-0daeb278fcc8/dj.hjzzaivb.jpg/600x600bb.jpg"),
        Album("Random Access Memories", "Daft Punk", "https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/e8/43/5f/e8435ffa-b6b9-b171-40ab-4ff3959ab661/886443919266.jpg/600x600bb.jpg"),
        Album("KIDS SEE GHOSTS", "KIDS SEE GHOSTS", "https://is1-ssl.mzstatic.com/image/thumb/Music124/v4/2c/bc/1c/2cbc1cca-7881-2605-875c-536f553cc79a/00602567803881.rgb.jpg/600x600bb.jpg"),
        Album("good kid, m.A.A.d city", "Kendrick Lamar", "https://is1-ssl.mzstatic.com/image/thumb/Music122/v4/ca/5b/c0/ca5bc0b3-d81d-cc6c-0d19-54b9eedb6dbd/12UMGIM52990.rgb.jpg/600x600bb.jpg"),
        Album("Thriller", "Michael Jackson", "https://is3-ssl.mzstatic.com/image/thumb/Music115/v4/32/4f/fd/324ffda2-9e51-8f6a-0c2d-c6fd2b41ac55/074643811224.jpg/600x600bb.jpg"),
        Album("Evil Friends", "Portugal. The Man", "https://is4-ssl.mzstatic.com/image/thumb/Music125/v4/06/25/a2/0625a20d-f04a-cb89-1c45-01f44a1d8d30/075679954541.jpg/600x600bb.jpg"),
        Album("Royal Blood", "Royal Blood", "https://is1-ssl.mzstatic.com/image/thumb/Music115/v4/f6/33/20/f6332053-fba1-187e-a42d-703e902dcb77/825646240913.jpg/600x600bb.jpg"),
        Album("Hellfire", "black midi", "https://is3-ssl.mzstatic.com/image/thumb/Music126/v4/f9/ef/16/f9ef16ed-61f2-095a-ca9d-7b689854c21a/191402032178.png/600x600bb.jpg"),
        Album("Led Zeppelin IV", "Led Zeppelin", "https://is2-ssl.mzstatic.com/image/thumb/Music5/v4/16/de/e3/16dee335-173b-57ce-80b5-dadf684c0b33/dj.oblporhs.jpg/600x600bb.jpg"),
    )
    fun loadSampleAlbums(count: Int) = sampleAlbums.subList(0, count)
    fun loadSampleAlbums() = sampleAlbums
    fun loadSampleRecentlySearched() = loadSampleAlbums(12)

}