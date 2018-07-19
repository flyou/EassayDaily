package com.flyou.eassaydaily.mvp.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter

/**
 * Created by fzl on 2018/7/18 0018.
 * VersionCode: 1
 * Desc:
 */
interface EassayContract {

    interface View : IBaseView {
        /**
         * 显示文章
         */
        fun showEassay(htmlEassay: String)
    }

    interface Presenter : IPresenter<View> {
        /**
         * 获取所随机文章
         */
        fun getRandomEassay()

        /**
         * 获取今天的文章
         */
        fun getTodayEassay()

        /**
         * 根据日期获取文章，yyyymmdd
         */
        fun getNextEassay(data: String)
    }
}