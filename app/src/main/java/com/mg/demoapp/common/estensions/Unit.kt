package com.mg.demoapp.common.estensions

import android.os.Handler

fun withDelay(delay : Long, block : () -> Unit) {
    Handler().postDelayed(Runnable(block), delay)
}
