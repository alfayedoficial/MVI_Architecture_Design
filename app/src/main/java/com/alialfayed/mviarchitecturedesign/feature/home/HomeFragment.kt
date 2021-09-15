package com.alialfayed.mviarchitecturedesign.feature.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.view.BaseFragment
import com.alialfayed.mviarchitecturedesign.core.data.viewModel.HomeViewModelTest
import com.alialfayed.mviarchitecturedesign.core.domain.model.DataItem
import com.alialfayed.mviarchitecturedesign.core.domain.state.ViewStateJob
import com.alialfayed.mviarchitecturedesign.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.base_recyclerview.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment(override val layoutResourceLayout: Int = R.layout.fragment_home) : BaseFragment<FragmentHomeBinding>() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var navController: NavController
    private val homeViewModel: HomeViewModelTest by viewModel()
    private val adapterHome : HomeAdapter by lazy { HomeAdapter()}

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentHomeBinding) {
        dataBinder.apply {
            fragment = this@HomeFragment
            lifecycleOwner = this@HomeFragment

            swiper.setOnRefreshListener {
//                adapterHome.clearDataList()
                swiper.isRefreshing = false
                setUpViewModelStateObservers()
            }
        }
        navController = findNavController()

    }


    override fun setUpViewModelStateObservers() {
        if (extensions.isConnected()){
            homeViewModel.jobViewStateHome
                .observe(this, {
                    renderJobs(it)
                })
        }else{
            showNoInternet()
        }

    }

    private fun renderJobs(it: ViewStateJob?) {
        it?.let {
            when {
                it.data != null -> {
                    it.data.data?.let {list ->
                        showMainLayout(list as ArrayList<DataItem>)
                    }
                }
                it.isLoading -> {
                    showLoading()
                }
                it.error != null ->{
                    Log.i("TEST_API" , "status -> 3")
                }
                else -> {
                    Log.i("TEST_API" , "status -> 4")
                }
            }
        }
    }

    fun finish() = backFragment()


    /**
     * layout of No Internet
     */
    private fun showNoInternet(){
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(baseNoInternet.root)
            baseNoInternet.cardTryAgain.setOnClickListener {
                extensions.openWifi(requireActivity())
            }
        }
    }

    /**
     * layout of Loading
     */
    private fun showLoading(){
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(baseLoading.root)
        }
    }

    /**
     * layout of RecyclerView
     */
    private fun showMainLayout(models: ArrayList<DataItem>) {
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(baseRecycler.rootView)
            Log.i("TEST_LIST" , models.size.toString())
            adapterHome.setDataList(models)
            adapterHome.navControllerInit(navController)

            baseRecycler.recyclerview_base.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = adapterHome
            }
        }
    }


}