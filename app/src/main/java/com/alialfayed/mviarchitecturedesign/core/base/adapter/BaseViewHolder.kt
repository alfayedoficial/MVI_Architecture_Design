package com.alialfayed.mviarchitecturedesign.core.base.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.alialfayed.mviarchitecturedesign.databinding.ItemRecyclerviewBinding

abstract class BaseViewHolder<T>(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    open var itemRowBinding: ViewDataBinding = binding
    abstract fun bind(result: T)
}