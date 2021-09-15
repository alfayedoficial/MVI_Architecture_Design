package com.alialfayed.mviarchitecturedesign.core.di

import com.alialfayed.mviarchitecturedesign.core.data.viewModel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module { viewModel { HomeViewModelTest(get()) } }

val detailsViewModelModule = module { viewModel { DetailsViewModelTest(get()) } }

val wishListViewModelModule = module { viewModel { WishListViewModelTest(get()) } }

