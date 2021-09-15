package com.alialfayed.mviarchitecturedesign.core.domain.action

import com.alialfayed.mviarchitecturedesign.core.room.WishListJob

sealed class JobAction : Action{
    object GetHomeAction : JobAction()
    object GetAllJobsAction : JobAction()
    object DeleteJobsAction : JobAction()
    data class InsertJobAction (var job : WishListJob) : JobAction()
}

