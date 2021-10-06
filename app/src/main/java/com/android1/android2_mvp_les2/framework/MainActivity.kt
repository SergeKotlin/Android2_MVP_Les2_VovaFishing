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

    override fun onStart() {
        super.onStart()
        presenter.onViewStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onViewStop()
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

// С урока Владимира.
// С BasePresenter мы устранили проблему падения из-за пустого обращения к выключенной view.
// Но при повороте экрана по-прежнему всё обнуляется.
// Есть средство Moxy - но оно не подходит для принципа Single-Activity с множ-вом фрагментов.
// Суть Moxy в повторении одних и тех же действий при повороте дисплея.
// Т.о. Владимир вообще не имеет решения этой проблеме на MVP) Только использовать MVVM.
//
// Преимущество MVP - что может напрямую что-то сказать делать вью. А также имеет чёткую логику разделения
// MVVM работает по подпискам.. слушателям. Но она умеет переживать поворот экрана, приучена к отключению view