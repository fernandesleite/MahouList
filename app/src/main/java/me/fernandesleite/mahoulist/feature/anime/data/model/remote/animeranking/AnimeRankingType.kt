package me.fernandesleite.mahoulist.feature.anime.data.model.remote.animeranking

enum class AnimeRankingType(val type: String, val title: String) {
    ALL("all", "All"),
    AIRING("airing", "Currently Airing"),
    UPCOMING("upcoming", "Upcoming"),
    TV("tv", "TV"),
    OVA("ova", "OVA"),
    MOVIE("movie", "Movie"),
    SPECIAL("special", "Special"),
    BY_POPULARITY("bypopularity", "Popular"),
    FAVORITE("favorite", "Favorite"),
    SUGGESTED("suggested", "Suggested"),
}