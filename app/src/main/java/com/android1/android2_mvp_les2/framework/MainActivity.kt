package com.android1.android2_mvp_les2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android1.android2_mvp_les2.databinding.ActivityMainBinding
import com.android1.android2_mvp_les2.interactors.strings_interactor.StringsInteractorImpl
import com.android1.android2_mvp_les2.views.MainView

class MainActivity : AppCompatActivity(), MainView {

    //! Перетаскивать по папкам Владимир советует без студии, просто в файловом проводнике

    private lateinit var binding: ActivityMainBinding // ссылке уже известно, на какой layout ссылаемся
    // Но свяжется это через inflate (ниже)

    private lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

        // Связали и передили:
        binding = ActivityMainBinding.inflate(layoutInflater) // просто layoutInflater вместо getLayoutInflater() в Java
        setContentView(binding.root)

        presenter = MainPresenter(this,
            StringsInteractorImpl(applicationContext)) // пробрасываем контекст (до onCreate() Activity)
        presenter.startPresenter()
    }

    override fun setOnClickForBtn() = with(binding) { // with - ключевое слово Kotlin, означает, что содержимое относится к binding (вместо повторений вызовов binding.)
        button.setOnClickListener {
            presenter.onBtnClicked()
        }
    }

    override fun setTextToTextView(text: String) = with(binding) {
        textView.text = text
    }
}