package com.example.pj4test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pj4test.ProjectConfiguration
import com.example.pj4test.audioInference.SnapClassifier
import com.example.pj4test.data.ResultViewModel
import com.example.pj4test.databinding.FragmentAudioBinding

public class AudioFragment: Fragment(), SnapClassifier.DetectorListener, ResultViewModel.AudioFragment {
    private val TAG = "AudioFragment"

    private var _fragmentAudioBinding: FragmentAudioBinding? = null
    private val fragmentAudioBinding
        get() = _fragmentAudioBinding!!

    // classifiers
    lateinit var snapClassifier: SnapClassifier

    // viewModels
    lateinit private var viewModel: ResultViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity()).get(ResultViewModel::class.java)
        viewModel.initializeAudioFragment(this);

        _fragmentAudioBinding = FragmentAudioBinding.inflate(inflater, container, false)

        return fragmentAudioBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        snapClassifier = SnapClassifier()
        snapClassifier.initialize(requireContext())
        snapClassifier.setDetectorListener(this)
    }

    override fun onPause() {
        super.onPause()
        viewModel.setIsSnoringDetected(false);
        snapClassifier.stopInferencing()
    }

    override fun onResume() {
        super.onResume()
        snapClassifier.startInferencing()
    }

    override fun getIsRunning() : Boolean {
        return snapClassifier.getIsRunningInferencing()
    }

    override fun onResults(score: Float) {
        activity?.runOnUiThread {
            val isSnoringDetected = score > SnapClassifier.THRESHOLD
            viewModel.setIsSnoringDetected(isSnoringDetected)
        }
    }
}