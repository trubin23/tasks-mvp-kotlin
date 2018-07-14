package ru.trubin23.tasksmvpkotlin.addedittask

import ru.trubin23.tasksmvpkotlin.BasePresenter
import ru.trubin23.tasksmvpkotlin.BaseView

interface AddEditTaskContract {

    interface View : BaseView<Presenter> {
    }

    interface Presenter : BasePresenter {
    }
}