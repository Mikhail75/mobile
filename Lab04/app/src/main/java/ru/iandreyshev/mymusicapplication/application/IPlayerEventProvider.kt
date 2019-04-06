package ru.iandreyshev.mymusicapplication.application

import ru.iandreyshev.model.player.IPlayerPresenter

interface IPlayerEventProvider {
    fun subscribe (observer: IPlayerPresenter)
    fun unsubscribe (observer: IPlayerPresenter)
}