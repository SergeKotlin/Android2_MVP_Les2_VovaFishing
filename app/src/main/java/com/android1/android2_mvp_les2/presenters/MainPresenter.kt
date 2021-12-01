package com.android1.android2_mvp_les2

import com.android1.android2_mvp_les2.interactors.strings_interactor.StringsInteractor
import com.android1.android2_mvp_les2.presenters.BasePresenter
import com.android1.android2_mvp_les2.views.MainView

class MainPresenter(
    viewContract: MainView,
    private val stringsInteractor: StringsInteractor
) : BasePresenter<MainView>(viewContract) {

    override fun startPresenter() {
        super.startPresenter()
        view?.setOnClickForBtn()
    }

    fun onBtnClicked() {
        view?.setTextToTextView(stringsInteractor.textAfterClick )
    }
}