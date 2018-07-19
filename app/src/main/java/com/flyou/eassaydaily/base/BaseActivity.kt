package com.flyou.eassaydaily.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.flyou.eassaydaily.widget.MultipleStatusView

/**
 * Created by fzl on 2018/7/18 0018.
 * VersionCode: 1
 * Desc:
 */
abstract class BaseActivity: AppCompatActivity() {


    /**
     * 多种状态的 View 的切换
     */
    protected var mLayoutStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeActivityLayout()
        setContentView(layoutId())
        initData()
        initView()
        startLoadData()
        initListener()

    }
    abstract fun beforeActivityLayout()

    private fun initListener() {
        mLayoutStatusView?.setOnClickListener(mRetryClickListener)
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        startLoadData()
    }


    /**
     *  加载布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化 View
     */
    abstract fun initView()

    /**
     * 加载数据
     */
    abstract fun startLoadData()



}