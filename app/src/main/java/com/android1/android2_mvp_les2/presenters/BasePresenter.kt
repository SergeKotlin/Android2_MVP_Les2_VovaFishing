package com.android1.android2_mvp_les2.presenters

import androidx.annotation.CallSuper

open class BasePresenter<T>(viewContract: T) { // на входе подгружаем интерфес действий при активности/нет view
    /* Этот класс сделан для решения проблемы MVP - когда view умерла, чтобы Presenter
    не продолжал обращаться к ней, вызывая критические ошибки и роняя приложение. */
    private var isViewActive = false

    @CallSuper // эта аннотация заставляет вызывать у наследуемых от неё классов "super. ..."
    open fun startPresenter() { // на старте view всегда активна, т.к вызываем через onCreate
        isViewActive = true
    }

    protected var view: T? = null // тип интерфейса
        get() {
            // При попытки постучаться к view, проверяем на её активность
            return if (isViewActive) field // да - возвращем саму переменную
            else null // view неактивна - вернём null
        }
        private set

    init {
        view = viewContract
    }

    @CallSuper
    open fun onViewStart() {
        isViewActive = true
    }

    @CallSuper
    open fun onViewStop() {
        isViewActive = false
    }
}