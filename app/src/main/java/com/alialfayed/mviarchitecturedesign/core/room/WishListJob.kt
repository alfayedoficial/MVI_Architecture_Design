package com.alialfayed.mviarchitecturedesign.core.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by ( Eng Ali Al Fayed)
 * Class do :
 * Date 9/14/2021 - 3:20 PM
 */

@Entity(tableName = "job_table")
data class WishListJob(

    @ColumnInfo(name = "Name")
    val name: String? = null,

    @ColumnInfo(name = "Email")
    val email: String? = null,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String? = null,

    @ColumnInfo(name = "JobTitle")
    val jobTitle: String? = null,

    @ColumnInfo(name = "departmentName")
    val departmentName: String? = null,

    @ColumnInfo(name = "ProfileImage")
    val profileImage: String? = null,

    ){
    // PrimaryKey annotation to set idItem is unique [if you want that id autoGenerate set @field:PrimaryKey(autoGenerate = true)]
    @field:PrimaryKey(autoGenerate = true)
    var id : Long  = 0
}

