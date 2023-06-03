package com.example.pj4test.data

import androidx.lifecycle.ViewModel
import java.util.Date

class ResultViewModel : ViewModel() {
    private var isPersonDetected = false
    private var isSnoringDetected = false
    private var lastDetectedTime = Date(0);

    public fun getIsPersonDetected(): Boolean {
        return isPersonDetected
    }

    public fun setIsPersonDetected(detected: Boolean) {
        isPersonDetected = detected
    }

    public fun getIsSnoringDetected(): Boolean {
        return isSnoringDetected
    }

    public fun setIsSnoringDetected(detected: Boolean) {
        isSnoringDetected = detected
    }

    public fun getLastDetectedTime(): Date {
        return lastDetectedTime
    }

    public fun setLastDetectedTime(lastTime: Date) {
        lastDetectedTime = lastTime
    }
}
