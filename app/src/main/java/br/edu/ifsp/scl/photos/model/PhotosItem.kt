package br.edu.ifsp.scl.photos.model

data class PhotosItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
) {
    override fun toString(): String {
        return title
    }
}
