package com.example.recipes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import android.os.Parcel
import android.os.Parcelable

data class MealModel(
    @DrawableRes val img1: Int,
    @DrawableRes val img2: Int,
    @StringRes val name: Int,
    @StringRes val shortDesc: Int,
    @StringRes val about: Int,
    @StringRes val time: Int,
    @StringRes val servings: Int,
    @StringRes val category: Int,
    val ingredients: List<IngredientsModel>,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.createTypedArrayList(IngredientsModel.CREATOR) ?: emptyList()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(img1)
        parcel.writeInt(img2)
        parcel.writeInt(name)
        parcel.writeInt(shortDesc)
        parcel.writeInt(about)
        parcel.writeInt(time)
        parcel.writeInt(servings)
        parcel.writeInt(category)
        parcel.writeTypedList(ingredients)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MealModel> {
        override fun createFromParcel(parcel: Parcel): MealModel {
            return MealModel(parcel)
        }

        override fun newArray(size: Int): Array<MealModel?> {
            return arrayOfNulls(size)
        }
    }
}
