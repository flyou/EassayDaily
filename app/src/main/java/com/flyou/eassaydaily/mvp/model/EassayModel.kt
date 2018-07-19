package com.flyou.eassaydaily.mvp.model

import com.flyou.eassaydaily.domin.Eassay
import com.flyou.eassaydaily.net.RetrofitManager
import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import io.reactivex.Observable

/**
 * Created by fzl on 2018/7/18 0018.
 * VersionCode: 1
 * Desc:
 */
class EassayModel {

    /**
     * 获得今天的文章
     */
    fun  getToDayEassay(): Observable<Eassay> {
      return  RetrofitManager.service.getTodayEassay().compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获得随机文章
     */
    fun getRandomEassay(): Observable<Eassay> {
      return  RetrofitManager.service.getRandomEassay().compose(SchedulerUtils.ioToMain())
    }

    /**
     * 根据日期获取文章,yyyymmdd
     */
    fun  getEassayByData(date:String): Observable<Eassay>{
        return  RetrofitManager.service.getEassayByDate(date).compose(SchedulerUtils.ioToMain())
    }
}