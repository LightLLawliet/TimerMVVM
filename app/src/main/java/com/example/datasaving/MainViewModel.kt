package com.example.datasaving

class MainViewModel(
    private val repository: Repository,
    private val secondsToHours: SecondsToHours
) : Observe {

    private var callback: UiStateCallback = UiStateCallback.Empty()


    override fun observe(callback: UiStateCallback) {
        this.callback = callback
    }

    fun clear() {
        callback = UiStateCallback.Empty()
    }

    fun startTrackingTime() {
        callback.post(secondsToHours.map(repository.time()))
        repository.startTracking()
    }

    fun stopTrackingTime() {
        repository.stopTracking()
    }


}

interface Observe {
    fun observe(callback: UiStateCallback)
}

interface UiStateCallback {

    fun post(message: String)

    class Empty : UiStateCallback {
        override fun post(message: String) = Unit
    }
}