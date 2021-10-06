package com.android1.android2_mvp_les2

import com.android1.android2_mvp_les2.interactors.strings_interactor.StringsInteractor
import com.android1.android2_mvp_les2.views.MainView

class MainPresenter(
    private val view: MainView,
    private val stringsInteractor: StringsInteractor
) {

    fun startPresenter() {
        view.setOnClickForBtn()
    }

    fun onBtnClicked() {
        view.setTextToTextView(stringsInteractor.textAfterClick )
    }
}