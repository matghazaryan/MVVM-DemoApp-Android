package com.mg.demoapp.common.estensions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.getColor(@ColorRes id: Int) = ContextCompat.getColor(this, id)
fun Context.getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(this, id)