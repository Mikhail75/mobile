package ru.iandreyshev.model.repository

data class Song(
    override val id: Long,
    override val title: String,
    override val poster: Int,
    override val resource: Int
) : ISong
