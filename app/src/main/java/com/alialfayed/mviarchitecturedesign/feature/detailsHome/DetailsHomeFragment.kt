package com.alialfayed.mviarchitecturedesign.feature.detailsHome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.view.BaseFragment
import com.alialfayed.mviarchitecturedesign.core.data.viewModel.DetailsViewModelTest
import com.alialfayed.mviarchitecturedesign.core.domain.model.DataItem
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob
import com.alialfayed.mviarchitecturedesign.core.utils.setBindImage
import com.alialfayed.mviarchitecturedesign.core.utils.setBindString
import com.alialfayed.mviarchitecturedesign.databinding.FragmentDetailsHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class DetailsHomeFragment (override val layoutResourceLayout: Int = R.layout.fragment_details_home) : BaseFragment<FragmentDetailsHomeBinding>() {

    private var param1: String? = null
    private var param2: String? = null
    private lateinit var navController: NavController
    private val detailsViewModel: DetailsViewModelTest by viewModel()
    private var model : DataItem ? = null

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailsHomeFragment().apply {
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
        model = arguments?.getParcelable("model")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onFragmentCreated(dataBinder: FragmentDetailsHomeBinding) {
        dataBinder.apply {
            fragment = this@DetailsHomeFragment
            lifecycleOwner = this@DetailsHomeFragment
        }

        navController = findNavController()

        if (model != null){
            showContainerDetailsData(model!!)
        }else{
            showNoData()
        }

    }

    override fun setUpViewModelStateObservers() {
        print("")
    }

    fun finish() = backFragment()

    private fun wishList(){
        val jobs = WishListJob(name = model?.name,email = model?.email, phoneNumber = model?.phoneNumber
            ,jobTitle = model?.jobTitle , departmentName = model?.departmentName , profileImage = model?.profileImage)

        detailsViewModel.insertViewStateInsertJob(jobs)
            .observe(this,{
                when {
                    it.data != null -> {
                        Log.i("TESTInsert" , "status -> 1")
                        dataBinder.containerDetails.imgBtnWishList.setImageResource(R.drawable.ic_fav)
                    }
                    it.isLoading -> {
                        Log.i("TESTInsert" , "status -> 2")
                    }
                    it.errorString != null ->{
                        Log.i("TESTInsert" , "status -> 3")
                    }
                    else -> {
                        Log.i("TESTInsert" , "status -> 4")
                    }
                }
            })

    }

    /**
     * layout of No Data
     */
    private fun showNoData(){
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(baseNoData.root)
            baseNoData.cardTryAgain.setOnClickListener {
                finish()
            }
        }
    }

    /**
     * layout of Container Details
     */
    private fun showContainerDetailsData(model: DataItem) {
        dataBinder.apply {
            viewFlipper.displayedChild = viewFlipper.indexOfChild(containerDetails.root)
            containerDetails.apply {

                setBindImage(img, model.profileImage , itemProgress)
                setBindString(txtH1 , model.name)
                setBindString(name , model.name)
                setBindString(job , model.jobTitle)
                setBindString(phone , model.phoneNumber)
                setBindString(email , model.email)
                setBindString(department , model.departmentName)

                lyWishList.setOnClickListener { wishList() }
                imgBtnWishList.setOnClickListener { wishList() }

            }
        }
    }



}