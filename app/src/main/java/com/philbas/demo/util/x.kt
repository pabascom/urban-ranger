package com.philbas.demo.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/*
 * I didn't end up using this utility function, but I figured I'd leave it in. I have a few
 * of these I import into projects which need them, just to make code cleaner. I think this
 * is one of the real benefits of Kotlin: if the library you're working with doesn't have
 * exactly the API you wish it did, you can just... change the API.
 */
fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutRes,this, attachToRoot)
}