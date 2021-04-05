package org.wit.example.beautycentral.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ClientModel(
        var id: Long=0,
        var name:String="",
        var phoneNo:String="",
        var service:String="",
        var notes:String=""
        ) : Parcelable
