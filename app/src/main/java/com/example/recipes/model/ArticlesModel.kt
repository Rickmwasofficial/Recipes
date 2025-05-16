package com.example.recipes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import android.os.Parcel
import android.os.Parcelable


data class ArticlesModel(
    @StringRes val name: Int,
    @DrawableRes val img1: Int,
    @StringRes val desc: Int,
    @StringRes val content: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(name)
        parcel.writeInt(img1)
        parcel.writeInt(desc)
        parcel.writeInt(content)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ArticlesModel> {
        override fun createFromParcel(parcel: Parcel): ArticlesModel {
            return ArticlesModel(parcel)
        }

        override fun newArray(size: Int): Array<ArticlesModel?> {
            return arrayOfNulls(size)
        }
    }
}