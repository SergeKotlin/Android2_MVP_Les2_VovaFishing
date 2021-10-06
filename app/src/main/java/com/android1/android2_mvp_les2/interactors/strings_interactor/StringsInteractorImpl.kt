package com.android1.android2_mvp_les2.interactors.strings_interactor

import android.content.Context
import com.android1.android2_mvp_les2.R

class StringsInteractorImpl(private val context: Context) : StringsInteractor {

    override val textAfterClick: String
        get() = context.getString(R.string.text_after_click)
}