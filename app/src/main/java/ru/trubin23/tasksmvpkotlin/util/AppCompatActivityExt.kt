package ru.trubin23.tasksmvpkotlin.util

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.addFragmentToActivity(fragment: Fragment, @IdRes frameId: Int) {
    supportFragmentManager.transaction {
        add(frameId, fragment)
    }
}

private fun FragmentManager.transaction(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}