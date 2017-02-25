package maxinertia.lifelog.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

/**
 *
 */
public class Note implements Comparable<Note>, Parcelable {
    private String title;
    private String content;

    //private Date creationDate;
    private Date submissionDate;

    LinkedList<SubNote> subNotes;

    public Note(String title, String content, Date creationDate, Date submissionDate) {
        this.title = title;
        this.content = content;
        this.submissionDate = submissionDate;
        subNotes = new LinkedList<>();
    }

    protected Note(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(content);
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getReadableDate() {
        if(submissionDate==null) return "";
        return formatDateToReadable(submissionDate,true);
    }

    public String getID() {
        return ""+submissionDate.getTime();
    }

    public LinkedList<SubNote> getSubNotes() {
        if(subNotes== null) subNotes = new LinkedList<>();
        return subNotes;
    }

    public void addSubNote(String content) {
        if(subNotes==null) subNotes = new LinkedList<>();
        subNotes.add(new SubNote(content));
    }

    public boolean hasSubNotes() {
        return subNotes!=null && subNotes.size()>0;
    }

    @Override
    public int compareTo(Note otherNote) {
        if(this.submissionDate.getTime()<otherNote.submissionDate.getTime()) {
            return 1;
        } else {
            return -1;
        }
    }

    static String formatDateToReadable(Date date, boolean tof) {
        SimpleDateFormat formatter;
        if(tof) formatter = new SimpleDateFormat("HH:mm a\nMMM d, yyyy");
        else formatter = new SimpleDateFormat("MMM d, yyyy, HH:mm a");
        return formatter.format(date);
    }
}
