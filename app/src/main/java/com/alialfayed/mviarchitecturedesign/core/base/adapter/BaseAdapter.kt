package com.alialfayed.mviarchitecturedesign.core.base.adapter

import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    abstract fun setDataList(dataList: List<T>)

    abstract fun addDataList(dataList: List<T>)

    abstract fun navControllerInit(navController: NavController)

    abstract fun clearDataList()

}