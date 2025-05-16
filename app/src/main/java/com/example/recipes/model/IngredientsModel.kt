package com.example.recipes.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class IngredientsModel(
    @StringRes val name: Int,
    @StringRes val qty: Int,
    @DrawableRes val img: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(name)
        parcel.writeInt(qty)
        parcel.writeInt(img)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IngredientsModel> {
        override fun createFromParcel(parcel: Parcel): IngredientsModel {
            return IngredientsModel(parcel)
        }

        override fun newArray(size: Int): Array<IngredientsModel?> {
            return arrayOfNulls(size)
        }
    }
}
