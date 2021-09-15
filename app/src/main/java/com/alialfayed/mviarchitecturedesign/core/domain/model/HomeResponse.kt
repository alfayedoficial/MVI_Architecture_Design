package com.alialfayed.mviarchitecturedesign.core.domain.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class HomeResponse(

	@field:Json(name="IsDeleted")
	val isDeleted: Any? = null,

	@field:Json(name="AsyncActionType")
	val asyncActionType: Any? = null,

	@field:Json(name="PageCount")
	val pageCount: Any? = null,

	@field:Json(name="Message")
	val message: String? = null,

	@field:Json(name="ArabicMessage")
	val arabicMessage: Any? = null,

	@field:Json(name="ServiceName")
	val serviceName: String? = null,

	@field:Json(name="GUId")
	val gUId: Any? = null,

	@field:Json(name="Data")
	val data: List<DataItem>? = null,

	@field:Json(name="BaseUrl")
	val baseUrl: Any? = null,

	@field:Json(name="Code")
	val code: Int? = null,

	@field:Json(name="Success")
	val success: Boolean? = null,

	@field:Json(name="LastUpdateDate")
	val lastUpdateDate: Any? = null
)

@Parcelize
data class DataItem(

	@field:Json(name="CountryID")
	val countryID: Int? = null,

	@field:Json(name="IsAdmin")
	val isAdmin: Boolean? = null,

	@field:Json(name="Email")
	val email: String? = null,

	@field:Json(name="JobTitle")
	val jobTitle: String? = null,

	@field:Json(name="Name")
	val name: String? = null,

	@field:Json(name="JobDescription")
	val jobDescription: String? = null,

	@field:Json(name="UserStateID")
	val userStateID: Int? = null,

	@field:Json(name="ProfileImage")
	val profileImage: String? = null,

	@field:Json(name="IsStaff")
	val isStaff: Boolean? = null,

	@field:Json(name="DepartmentID")
	val departmentID: Int? = null,

	@field:Json(name="ActivationCode")
	val activationCode: String? = null,

	@field:Json(name="MemberCode")
	val memberCode: String? = null,

	@field:Json(name="ManagerID")
	val managerID: Int? = null,

	@field:Json(name="IsUnderCompany")
	val isUnderCompany: Boolean? = null,

	@field:Json(name="FireBaseToken")
	val fireBaseToken: String? = null,

	@field:Json(name="IsDeleted")
	val isDeleted: Boolean? = null,

	@field:Json(name="AccountID")
	val accountID: Int? = null,

	@field:Json(name="UserStateMessage")
	val userStateMessage: String? = null,

	@field:Json(name="LastChatMessage")
	val lastChatMessage: String? = null,

	@field:Json(name="IsBusinessHead")
	val isBusinessHead: Boolean? = null,

	@field:Json(name="GroupId")
	val groupId: Long? = null,

	@field:Json(name="IsActivated")
	val isActivated: Boolean? = null,

	@field:Json(name="AsyncActionType")
	val asyncActionType: Int? = null,

	@field:Json(name="PhoneNumber")
	val phoneNumber: String? = null,

	@field:Json(name="DepartmentName")
	val departmentName: String? = null
):Parcelable
