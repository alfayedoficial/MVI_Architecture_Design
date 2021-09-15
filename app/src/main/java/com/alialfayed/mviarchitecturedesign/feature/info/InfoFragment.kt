package com.alialfayed.mviarchitecturedesign.feature.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.view.BaseFragment
import com.alialfayed.mviarchitecturedesign.databinding.FragmentInfoBinding


class InfoFragment(override val layoutResourceLayout: Int = R.layout.fragment_info) : BaseFragment<FragmentInfoBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentInfoBinding) {
        dataBinder.apply {
            fragment = this@InfoFragment
            lifecycleOwner = this@InfoFragment
       }
    }

    override fun setUpViewModelStateObservers() {
       print("setUpViewModel")
    }

    fun finish() = backFragment()

}