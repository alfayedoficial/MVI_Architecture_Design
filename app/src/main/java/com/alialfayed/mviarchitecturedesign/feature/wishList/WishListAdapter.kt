package com.alialfayed.mviarchitecturedesign.feature.wishList

import android.annotation.SuppressLint
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
import com.alialfayed.mviarchitecturedesign.core.room.WishListJob
import com.alialfayed.mviarchitecturedesign.core.utils.getBindingRow
import com.alialfayed.mviarchitecturedesign.databinding.ItemWishlistBinding

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/13/2021 - 3:28 PM
 */
class WishListAdapter: BaseAdapter<WishListJob>() {

    private var mDiffer = AsyncListDiffer(this, DiffCallBack<WishListJob>())
    private lateinit var navController: NavController
    private lateinit var fragment : WishListFragment

    override fun setDataList(dataList: List<WishListJob>) {
        mDiffer.submitList(dataList)
    }

    override fun addDataList(dataList: List<WishListJob>) {
        mDiffer.currentList.addAll(dataList)
    }

    override fun clearDataList() {
        mDiffer.currentList.clear()
    }

    override fun navControllerInit(navController: NavController) {
        this.navController = navController
    }

    fun wishListFragment(fragment : WishListFragment){
        this.fragment = fragment
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<WishListJob> {
        return ViewHolderWishList(getBindingRow(parent, R.layout.item_wishlist) as ItemWishlistBinding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: BaseViewHolder<WishListJob>, position: Int) {
        val model = mDiffer.currentList[position]
        holder.apply {
            bind(model)
            holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.ver_anim)
            holder.itemView.setOnClickListener {

                val modelX = DataItem(name = model.name , jobTitle = model.jobTitle
                ,phoneNumber = model.phoneNumber , profileImage = model.profileImage
                ,email = model.email , departmentName = model.departmentName)

                val bundle = bundleOf("model" to modelX)
                navController.navigate(R.id.action_wishListFragment_to_detailsHomeFragment, bundle)
            }

            (holder.itemRowBinding as ItemWishlistBinding).apply {

                delete.setOnClickListener {
                    fragment.deleteJobs()
                    fragment.showNoItem()
                }
            }
        }


    }

    override fun getItemCount(): Int = mDiffer.currentList.size


    class ViewHolderWishList (binding: ItemWishlistBinding): BaseViewHolder<WishListJob>(binding)  {

        override var itemRowBinding: ViewDataBinding = binding

        override fun bind(result: WishListJob) {
            itemRowBinding.setVariable(BR.model, result)
            itemRowBinding.executePendingBindings()
        }
    }


}