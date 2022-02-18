package com.tare.authentication

import com.tare.authentication.network.RestClient
import com.tare.authentication.pojo.request.RequestInitial
import com.tare.authentication.pojo.response.ResponseBaseUrl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val services = RestClient.create()

    @Test
    fun `GET OTP`(){
        services.getBaseUrl(RequestInitial("12837192739127392","0"))
            .subscribeOn(TestScheduler())
            .test()
            .await()
            .assertComplete()
    }
}