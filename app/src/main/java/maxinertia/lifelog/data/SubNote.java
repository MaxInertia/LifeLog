package maxinertia.lifelog.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 *
 */
public class SubNote implements Parcelable {
    private String appendContent;

    private Date appendDate;

    public SubNote(String content) {
        this.appendContent = content;
        appendDate = new Date();
    }

    protected SubNote(Parcel in) {
        appendContent = in.readString();
    }

    public static final Creator<SubNote> CREATOR = new Creator<SubNote>() {
        @Override
        public SubNote createFromParcel(Parcel in) {
            return new SubNote(in);
        }

        @Override
        public SubNote[] newArray(int size) {
            return new SubNote[size];
        }
    };

    public String getContent() {
        return appendContent;
    }

    public String getReadableDate() {
        if(appendDate==null) return "";
        return Note.formatDateToReadable(appendDate,false);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(appendContent);
    }
}
