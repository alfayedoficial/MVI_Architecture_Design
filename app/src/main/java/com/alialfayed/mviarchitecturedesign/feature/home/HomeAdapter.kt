package com.alialfayed.mviarchitecturedesign.feature.home

import android.app.Activity
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.recyclerview.widget.AsyncListDiffer
import com.alialfayed.mviarchitecturedesign.BR
import com.alialfayed.mviarchitecturedesign.R
import com.alialfayed.mviarchitecturedesign.core.base.adapter.BaseAdapter
import com.alialfayed.mviarchitecturedesign.core.base.adapter.BaseViewHolder
import com.alialfayed.mviarchitecturedesign.core.base.adapter.DiffCallBack
import com.alialfayed.mviarchitecturedesign.core.domain.model.DataItem
import com.alialfayed.mviarchitecturedesign.core.utils.getBindingRow
import com.alialfayed.mviarchitecturedesign.databinding.ItemRecyclerviewBinding

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 3:28 PM
 */
class HomeAdapter: BaseAdapter<DataItem>() {

    private var mDiffer = AsyncListDiffer(this, DiffCallBack<DataItem>())
    private lateinit var navController: NavController

    override fun setDataList(dataList: List<DataItem>) {
        mDiffer.submitList(dataList)
    }

    override fun addDataList(dataList: List<DataItem>) {
        mDiffer.currentList.addAll(dataList)
    }

    override fun clearDataList() {
        mDiffer.currentList.clear()
    }

    override fun navControllerInit(navController: NavController) {
        this.navController = navController
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<DataItem> {
        return ViewHolderHome(getBindingRow(parent, R.layout.item_recyclerview) as ItemRecyclerviewBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<DataItem>, position: Int) {
        val model = mDiffer.currentList[position]
        holder.apply {
            bind(model)
            holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.ver_anim)
            holder.itemView.setOnClickListener {
                val bundle = bundleOf("model" to model)
                navController.navigate(R.id.action_homeFragment_to_detailsHomeFragment, bundle)
            }
        }


    }

    override fun getItemCount(): Int = mDiffer.currentList.size


    class ViewHolderHome (binding: ItemRecyclerviewBinding): BaseViewHolder<DataItem>(binding)  {

        override var itemRowBinding: ViewDataBinding = binding

        override fun bind(result: DataItem) {
            itemRowBinding.setVariable(BR.model, result)
            itemRowBinding.executePendingBindings()
        }
    }


}