package com.alialfayed.mviarchitecturedesign.feature.getStated

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.view.BaseFragment
import com.alialfayed.mviarchitecturedesign.databinding.FragmentGetStartedBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class GetStartedFragment(override val layoutResourceLayout: Int = R.layout.fragment_get_started) : BaseFragment<FragmentGetStartedBinding>() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var navController: NavController

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GetStartedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentGetStartedBinding) {
        navController = findNavController()
        dataBinder.apply {
            fragment = this@GetStartedFragment
            lifecycleOwner = this@GetStartedFragment
        }
    }

    override fun setUpViewModelStateObservers() {
        print("setUpViewModel")
    }

    fun getStarted() = navController.navigate(R.id.action_getStartedFragment_to_homeFragment)



}