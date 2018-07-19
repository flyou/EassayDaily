package com.flyou.eassaydaily.domin

/**
 * Created by fzl on 2018/7/16 0016.
 * VersionCode: 1
 * Desc:
 */

data class Eassay(val data: EassayData)

data class EassayData(val date: Date, val author: String, val content: String, val digest: String,
                      val title: String, val wc: String)

data class Date(val curr: String, val next: String, val prev: String)
