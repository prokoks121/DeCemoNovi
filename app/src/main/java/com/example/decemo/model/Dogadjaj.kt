package com.example.decemo.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Dogadjaj(
        val datum: String,
        val id: Int,
        val id_lokala: Int,
        val imeLokala: String,
        val koPeva: String,
        val slika: String
):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readInt(),
            parcel.readInt(),
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readString()!!){
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(datum)
        parcel.writeInt(id)
        parcel.writeInt(id_lokala)
        parcel.writeString(imeLokala)
        parcel.writeString(koPeva)
        parcel.writeString(slika)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dogadjaj> {
        override fun createFromParcel(parcel: Parcel): Dogadjaj {
            return Dogadjaj(parcel)
        }

        override fun newArray(size: Int): Array<Dogadjaj?> {
            return arrayOfNulls(size)
        }
    }
}