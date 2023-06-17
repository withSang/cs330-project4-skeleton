package com.example.pj4test.data

import androidx.lifecycle.ViewModel
import java.util.Date

class ResultViewModel : ViewModel() {
    private var isPersonDetected = false
    private var isSnoringDetected = false
    private var lastDetectedTime = Date(0);
    private var audioFragment: AudioFragment? = null

    interface AudioFragment {
        fun getIsRunning(): Boolean
        fun onResume()
        fun onPause()
    }

    fun initializeAudioFragment(_audioFragment: AudioFragment) {
        audioFragment = _audioFragment
    }

    fun getIsPersonDetected(): Boolean {
        return isPersonDetected
    }

    fun setIsPersonDetected(detected: Boolean) {
        isPersonDetected = detected
        if (isPersonDetected) {
            if (audioFragment != null && !audioFragment!!.getIsRunning()) {
                audioFragment!!.onResume();
            }
        }
        else {
            if (audioFragment != null && audioFragment!!.getIsRunning()) {
                audioFragment!!.onPause();
            }
        }
    }

    fun getIsSnoringDetected(): Boolean {
        return isSnoringDetected
    }

    fun setIsSnoringDetected(detected: Boolean) {
        isSnoringDetected = detected
    }

    fun getLastDetectedTime(): Date {
        return lastDetectedTime
    }

    fun setLastDetectedTime(lastTime: Date) {
        lastDetectedTime = lastTime
    }
}
