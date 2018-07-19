package com.flyou.eassaydaily.mvp.persenter

import com.flyou.eassaydaily.domin.Eassay
import com.flyou.eassaydaily.mvp.contract.EassayContract
import com.flyou.eassaydaily.mvp.model.EassayModel
import com.hazz.kotlinmvp.base.BasePresenter
import com.hazz.kotlinmvp.net.exception.ExceptionHandle
import io.reactivex.Observable
import java.text.SimpleDateFormat

/**
 * Created by fzl on 2018/7/18 0018.
 * VersionCode: 1
 * Desc:
 */
class EassayPersenter : BasePresenter<EassayContract.View>(), EassayContract.Presenter {
    private val eassayModel by lazy {
        EassayModel()
    }

    override fun getRandomEassay() {
        checkViewAttached()
        mRootView?.showLoading()
        operatorEassayData(eassayModel.getRandomEassay())
    }

    override fun getTodayEassay() {
        checkViewAttached()
        mRootView?.showLoading()
        operatorEassayData(eassayModel.getToDayEassay())
    }

    override fun getNextEassay(data: String) {
        checkViewAttached()
        mRootView?.showLoading()
        operatorEassayData(eassayModel.getEassayByData(data))

    }

    /**
     * 根据返回的数据拼接数据
     */
    private fun getHtmlString(eassay: Eassay): String {
        var simpleDateFormat = SimpleDateFormat("yyyyMMdd")
        var newFormat = SimpleDateFormat("yyyy年MM月dd日")
        var strBuilder = StringBuilder()
        strBuilder.run {
            append("<html> <head>")
            append("<style>@font-face {font-family: MyFont;src:url('file:///android_asset/fonts/font.ttf') format('truetype');font-weight: normal;font-style: normal} *{font-family: MyFont;font-size:16px;line-height:25px; }center{}p {color:#00343f;margin:25px auto} body{background-color:#c0c0c0;} </style> </head><body>")
        }

        with(eassay.data) {

            val formatTime = newFormat.format(simpleDateFormat.parse(date.curr).time)
            strBuilder.run {
                append("<center> <p style='font-size:22px;line-height:5px' >$title</p> </center>")
                append("<center> <p style='font-size:16px;line-height:0px' >$author</p> </center>")
                append("<p style='font-size:12px;line-height:25px; margin: 0px' align=\"right\"  >$formatTime</p> ")
                append("<hr style='background-color:#D3D3D3;border:none;height:0.5px;line-height:0px; margin: 0px;' />")
                append(content)
                append("<hr style='background-color:#D3D3D3;border:none;height:1px;line-height:0.5px; margin: 0px;' />")
                append("<center> <p style='font-size:12px;line-height:0px' >全文共${wc}字</p> </center>")
                append("</body></html>")
            }
        }
        return strBuilder.toString()
    }

    private fun operatorEassayData(operator: Observable<Eassay>) {
        val disposable = operator.map {
            getHtmlString(it)
        }.subscribe({
            mRootView?.apply {

                dismissLoading()
                showEassay(it)
            }
        }, { throwable ->
            mRootView?.apply {
                showError(ExceptionHandle.handleException(throwable), ExceptionHandle.errorCode)
            }
        })
        addSubscription(disposable)
    }
}