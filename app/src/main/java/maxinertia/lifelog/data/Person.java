package maxinertia.lifelog.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 *
 */
public class Person implements Parcelable {

    String name;
    int age;
    String dateOfBirth;
    String address;
    String otherContent;

    Date dateAdded;

    public Person(String name, int age, String dob, String address, String otherContent, Date dateAdded) {
        this.name = name;
        this.age = age;
        this.dateOfBirth = dob;
        this.address = address;
        this.otherContent = otherContent;
        this.dateAdded = dateAdded;
    }

    protected Person(Parcel in) {
        name = in.readString();
        age = in.readInt();
        dateOfBirth = in.readString();
        address = in.readString();
        otherContent = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getID() {
        return ""+dateAdded.getTime();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
        dest.writeString(dateOfBirth);
        dest.writeString(address);
        dest.writeString(otherContent);
    }
}
