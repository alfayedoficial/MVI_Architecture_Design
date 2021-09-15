package com.alialfayed.mviarchitecturedesign.feature.wishList

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
import com.alialfayed.mviarchitecturedesign.core.data.viewModel.WishListViewModelTest
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob
import com.alialfayed.mviarchitecturedesign.databinding.FragmentWishListBinding
import kotlinx.android.synthetic.main.base_recyclerview.view.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.ArrayList

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class WishListFragment(override val layoutResourceLayout: Int = R.layout.fragment_wish_list) : BaseFragment<FragmentWishListBinding>() {

    private var param1: String? = null
    private var param2: String? = null
    private val wishListViewModel: WishListViewModelTest by viewModel()
    private val adapterWishList : WishListAdapter by lazy { WishListAdapter() }
    private lateinit var navController: NavController

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WishListFragment().apply {
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
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentWishListBinding) {
        dataBinder.apply {
            fragment = this@WishListFragment
            lifecycleOwner = this@WishListFragment
        }

        navController = findNavController()
    }

    override fun setUpViewModelStateObservers() {
//        wishListViewModel.models.observe(this, {

//        })
//

        wishListViewModel.getJobsViewStateLocal().observe(this, {
            when {
                it.data != null -> {
                    Log.i("TESTGet" , "status -> 1")
                    if (!it.data.isNullOrEmpty()){
                        showMainLayout(it.data as ArrayList<WishListJob>)
                    }else{
                        showNoItem()
                    }
                }
                it.isLoading -> {
                    Log.i("TESTGet" , "status -> 2")
                }
                it.errorString != null ->{
                    Log.i("TESTGet" , "status -> 3")
                }
                else -> {
                    Log.i("TESTGet" , "status -> 4")
                }
            }
        })

    }

    fun finish() = backFragment()

    /**
     * layout of No Item
     */
    fun showNoItem(){
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(baseNoItem.root)
            baseNoItem.cardTryAgain.setOnClickListener {
                finish()
            }
        }
    }

    /**
     * layout of RecyclerView
     */
    private fun showMainLayout(models: ArrayList<WishListJob>) {
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(baseRecycler.rootView)

            adapterWishList.setDataList(models)
            adapterWishList.wishListFragment(this@WishListFragment)
            adapterWishList.navControllerInit(navController)

            baseRecycler.recyclerview_base.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                adapter = adapterWishList
            }
        }
    }

    fun deleteJobs() {
        wishListViewModel.deleteJobsViewStateLocal().observe(this, {
            when {
                it.data != null -> {
                    Log.i("TESTGet" , "status -> 1")
                }
                it.isLoading -> {
                    Log.i("TESTGet" , "status -> 2")
                }
                it.errorString != null ->{
                    Log.i("TESTGet" , "status -> 3")
                }
                else -> {
                    Log.i("TESTGet" , "status -> 4")
                }
            }
        })
    }


}