package com.example.navigationExample;

import android.os.Parcel;
import android.os.Parcelable;

public class Money  implements Parcelable {
    private Integer amount;

    Money(Integer amount) { this.amount = amount; }

    private Money(Parcel in) {
        if (in.readByte() == 0) {
            amount = null;
        } else {
            amount = in.readInt();
        }
    }

    public static final Creator<Money> CREATOR = new Creator<Money>() {
        @Override
        public Money createFromParcel(Parcel in) {
            return new Money(in);
        }

        @Override
        public Money[] newArray(int size) {
            return new Money[size];
        }
    };

    public Integer getAmount() { return amount; }

    public void setAmount(Integer amount) { this.amount = amount; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (amount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(amount);
        }
    }
}
