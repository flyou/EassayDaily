package com.flyou.eassaydaily.util

import android.content.Context
import android.widget.Toast

/**
 * Created by fzl on 2018/7/18 0018.
 * VersionCode: 1
 * Desc:扩展方法存放
 */
fun Context.showToast(str: String,duration:Int=Toast.LENGTH_SHORT) {
    Toast.makeText(this, str, duration).show()
}