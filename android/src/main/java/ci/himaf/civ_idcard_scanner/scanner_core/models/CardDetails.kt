package ci.himaf.civ_idcard_scanner.scanner_core.models

import android.os.Parcel
import android.os.Parcelable

//@author himaf created on 27,June,2020

data class CardDetails(
        val cardNumber: String,
        val cardFirstName: String = "",
        val cardLastName: String = "",
        val expiryDate: String = "") : Parcelable {

  constructor(parcel: Parcel) : this(
          parcel.readString() ?: "",
      parcel.readString() ?: "",
      parcel.readString() ?: "",
          parcel.readString() ?: "")

  fun toMap(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    map["cardNumber"] = cardNumber
      map["cardFirstName"] = cardFirstName
      map["cardLastName"] = cardLastName
    map["expiryDate"] = expiryDate
    return map.toMap()
  }


  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(cardNumber)
      parcel.writeString(cardFirstName)
      parcel.writeString(cardLastName)
    parcel.writeString(expiryDate)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<CardDetails> {
    override fun createFromParcel(parcel: Parcel): CardDetails {
      return CardDetails(parcel)
    }

    override fun newArray(size: Int): Array<CardDetails?> {
      return arrayOfNulls(size)
    }
  }
}