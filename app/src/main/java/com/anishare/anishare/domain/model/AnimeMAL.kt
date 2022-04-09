package com.anishare.anishare.domain.model

data class AnimeMAL(
    val id: Int,
    val title: String,
    val main_picture: MainPicture? = null,
    val alternative_titles: AlternateTitles? = null,
    val genres: List<Genres>,
    val status: String,
    val num_episodes: Int,
    val related_anime: List<RelatedAnime>
) {
    companion object {
        fun mock() = AnimeMAL(
            id = 123,
            title = "Attack on titan",
            main_picture = MainPicture.mock(),
            genres = emptyList(),
            num_episodes = 24,
            related_anime = emptyList(),
            status = "Ongoing"
        )
    }
}

data class MainPicture(
    val large: String? = null,
    val medium: String
) {
    companion object {
        fun mock() = MainPicture(
            medium = "https://cdn.myanimelist.net/images/anime/1105/119414l.jpg"
        )
    }
}

data class AlternateTitles(
    val synonyms: List<String>? = null,
    val en: String,
    val ja: String?
)

data class Genres(
    val id : Int,
    val name: String
)

data class RelatedAnime(
    val node: Node,
    val relation_type: String,
    val relation_type_formatted: String
)

data class Node(
    val id: Int,
    val title: String,
    val main_picture: MainPicture
)