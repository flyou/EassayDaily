package com.hazz.kotlinmvp.base


interface IBaseView {

    fun showLoading()

    fun dismissLoading()

    fun showError(error:String,errorCode:Int)

}
