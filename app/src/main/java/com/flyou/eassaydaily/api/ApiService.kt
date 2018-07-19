package com.flyou.eassaydaily.api

import com.flyou.eassaydaily.domin.Eassay
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by fzl on 2018/7/16 0016.
 * VersionCode: 1
 * Desc:
 */
interface ApiService {

    /**
     * 获取今天的文章
     */
    @GET("today")
    fun getTodayEassay(): Observable<Eassay>

    /**
     * 获取指定时间的文章
     * @param date 时间yyyymmdd
     * 最小日期为20110308
     */
    @GET("day")
    fun getEassayByDate(@Query("date") data:String): Observable<Eassay>

    /**
     * 获取随机文章
     */
    @GET("random")
    fun getRandomEassay(): Observable<Eassay>


}