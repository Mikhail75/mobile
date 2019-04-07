package ru.iandreyshev.model.repository

interface ISong {
    val id: Long
    val title: String
    val poster: Int
    val resource: Int
}
