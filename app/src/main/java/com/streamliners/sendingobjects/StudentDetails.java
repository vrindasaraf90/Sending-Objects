package com.streamliners.sendingobjects;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentDetails implements Parcelable {
    private String name;
    private String mobileNumber;
    private String rollNumber;
    private String gender;


    public StudentDetails(String name, String mobileNumber, String rollNumber, String gender) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.rollNumber = rollNumber;
        this.gender = gender;
    }

    public StudentDetails(Parcel in){
        String[] data = new String[4];

        in.readStringArray(data);

        // the order needs to be the same as in writeToParcel() method
        this.name = data[0];
        this.mobileNumber = data[1];
        this.rollNumber = data[2];
        this.gender = data[3];
    }


    public static final Creator<StudentDetails> CREATOR = new Creator<StudentDetails>() {
        @Override
        public StudentDetails createFromParcel(Parcel in) {
            return new StudentDetails(in);
        }

        @Override
        public StudentDetails[] newArray(int size) {
            return new StudentDetails[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.name,
                this.mobileNumber,
                this.rollNumber,
                this.gender});
    }


    // all the getters

   //return name of student
    public String getName() {
        return name;
    }

    //return mobile number of the student
    public String getMobileNumber() {
        return mobileNumber;
    }

   //return roll no of the student
    public String getRollNumber() {
        return rollNumber;
    }

    //return gender of the student
    public String getGender() {
        return gender;
    }
}
