package com.venkatesh.dynamiclistdemo.items.repository.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("color")
    val color: String,
    @SerializedName("criteria")
    val criteria: List<Criteria>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("tag")
    val tag: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        TODO("criteria"),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    data class Criteria(
        @SerializedName("text")
        val text: String,
        @SerializedName("type")
        val type: String,
        @SerializedName("variable")
        val variable: HashMap<String, Any>?
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(color)
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(tag)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}