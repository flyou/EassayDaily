package com.flyou.eassaydaily.ui

import android.os.Build
import android.view.View.GONE
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.flyou.eassaydaily.R.layout
import com.flyou.eassaydaily.base.BaseActivity
import com.flyou.eassaydaily.mvp.contract.EassayContract
import com.flyou.eassaydaily.mvp.persenter.EassayPersenter
import com.flyou.eassaydaily.util.showToast
import com.hazz.kotlinmvp.net.exception.ErrorStatus
import kotlinx.android.synthetic.main.activity_main.fabNext
import kotlinx.android.synthetic.main.activity_main.fabPrev
import kotlinx.android.synthetic.main.activity_main.fabRandom
import kotlinx.android.synthetic.main.activity_main.menu_add
import kotlinx.android.synthetic.main.activity_main.multipleStatusView
import kotlinx.android.synthetic.main.activity_main.webView
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.abs



private var offset: Int = 0
private val mPresenter by lazy { EassayPersenter() }
private val format by lazy { SimpleDateFormat("yyyyMMdd") }
private var firstTime: Long = 0

class MainActivity : BaseActivity(), EassayContract.View {

    override fun beforeActivityLayout() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun layoutId() = layout.activity_main

    override fun initData() {
        mPresenter.attachView(this)

    }

    override fun initView() {
        mLayoutStatusView = multipleStatusView

        webView.settings.javaScriptEnabled = true
        webView.settings.cursiveFontFamily
        webView.webViewClient = MyWebClient()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING
        } else {
            webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL
        }

        menu_add.setClosedOnTouchOutside(true)
        fabRandom.setOnClickListener {
            mPresenter.getRandomEassay()
            menu_add.close(true)
            offset=0
        }
        fabNext.setOnClickListener {

            if (offset+1>0) {
                showToast("明天的文章还没有更新哦")
               offset=0
            } else {
                var date = getDate(true)
                mPresenter.getNextEassay(date)
                menu_add.close(true)
            }
        }
        fabPrev.setOnClickListener {
            var date = getDate(false)
            mPresenter.getNextEassay(date)
            menu_add.close(true)
        }



    }

    class MyWebClient : WebViewClient(){
        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            view?.pageUp(true)
        }
    }

    override fun startLoadData() {
        mPresenter.getTodayEassay()
    }

    override fun showEassay(htmlEassay: String) {

        webView.loadDataWithBaseURL(null, htmlEassay, "text/html;charset=utf-8", "utf-8", null)

    }

    override fun showLoading() {
        mLayoutStatusView?.showLoading()
    }

    override fun dismissLoading() {
        webView.visibility
        mLayoutStatusView?.showContent()
    }

    override fun showError(error: String, errorCode: Int) {
        showToast(error)
        webView.visibility = GONE
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    /**
     * 获取昨天和明天
     */
    fun getDate(isNext: Boolean): String {

        var time = Date().time

        return if (isNext) {
            offset++
            format.format(time.plus(86400000*  offset)).apply {

            }
        } else {
            offset--
            format.format(time.minus(86400000* abs(offset))).apply {

            }
        }


    }



    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
