package com.streamliners.sendingobjects;

import androidx.appcompat.app.AppCompatActivity;
import com.streamliners.sendingobjects.databinding.ActivityObjectSenderBinding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

public class ObjectSender extends AppCompatActivity {
    ActivityObjectSenderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Enter Details");
        binding = ActivityObjectSenderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_object_sender);
        
        setupOnClickListenerForButton();
        setupActionListener();
        setupHideErrorForEditText();
    }

    private void setupOnClickListenerForButton() {
        binding.saveButton.setOnClickListener(v -> sendData());
    }

    private void setupActionListener() {
        binding.phoneNumberTextField.getEditText().setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendData();
            }
            return true;
        });
    }

    private void setupHideErrorForEditText() {
        // Text watcher for name text field
        binding.nameTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.nameTextField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Text watcher for mobile number text field
        binding.phoneNumberTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.phoneNumberTextField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // Text watcher for roll number text field
        binding.rollNumberTextField.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.rollNumberTextField.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    //to take student details INPUT
    private StudentDetails getInfo() {
        // Name of the student
        String name = binding.nameTextField.getEditText().getText().toString().trim();
        if (name.isEmpty()) {
            binding.nameTextField.setError("Please enter name");
            return null;
        }

        //Gender of the student
        String gender;

        //Gender chosen in the radio button
        int type = binding.genderRadioGroup.getCheckedRadioButtonId();
        if (type == binding.femaleRadioButton.getId()) {
            gender = "Female";
        } else if (type == binding.maleRadioButton.getId()) {
            gender = "Male";
        } else {
            Toast.makeText(this, "Please select gender!", Toast.LENGTH_SHORT).show();
            return null;
        }

        //Roll number of the student
        String rollNumber = binding.rollNumberTextField.getEditText().getText().toString().trim();
        if (rollNumber.isEmpty()) {
            binding.rollNumberTextField.setError("Please enter roll number");
            return null;
        } else if (!rollNumber.matches("^\\d{2}[a-zA-Z]*\\d{3}")) {
            binding.rollNumberTextField.setError("Please enter valid roll number");
            return null;
        }

        //Mobile number of the student
        String mobileNumber= binding.phoneNumberTextField.getEditText().getText().toString().trim();
        if (mobileNumber.isEmpty()) {
            binding.phoneNumberTextField.setError("Please enter mobile number");
            return null;
        } else if (!mobileNumber.matches("^\\d{10}")) {
            binding.phoneNumberTextField.setError("Please enter valid mobile number");
            return null;
        }

        StudentDetails student = new StudentDetails(name, mobileNumber, rollNumber, gender);
        return student;
    }

    public void sendData() {
        // Taking student object with entered information
        StudentDetails student = getInfo();

        // Checking student if null then returns
        if (student == null) {
            return;
        }

        Intent intent = new Intent(this, ObjectViewer.class);
        intent.putExtra(Constants.STUDENT_DETAILS, student);

        startActivity(intent);
    }
}