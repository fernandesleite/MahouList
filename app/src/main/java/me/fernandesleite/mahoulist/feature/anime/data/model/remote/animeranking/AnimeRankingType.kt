package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking

enum class AnimeRankingType(val type: String) {
    ALL("all"),
    AIRING("airing"),
    UPCOMING("upcoming"),
    TV("tv"),
    OVA("ova"),
    MOVIE("movie"),
    SPECIAL("special"),
    BY_POPULARITY("bypopularity"),
    FAVORITE("favorite")
}