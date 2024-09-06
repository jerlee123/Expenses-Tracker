package com.example.expensestracker.profile;

//import static com.google.android.gms.cast.framework.media.ImagePicker.*;
import com.example.expensestracker.LoginActivity;
import com.github.dhaval2404.imagepicker.ImagePicker;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensestracker.R;
import com.google.firebase.auth.FirebaseAuth;
//import com.google.android.gms.cast.framework.media.ImagePicker;

public class Profile extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    ImageView profilePic;
    EditText usernameInput;
    EditText phoneInput;
    Button updateProfileBtn;
    TextView logoutBtn;

    userModel currentUserModel;
    ActivityResultLauncher<Intent> imagePickLauncher;
    Uri selectedImageUri;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePic = findViewById(R.id.profile_image_view);
        usernameInput = findViewById(R.id.profile_username);
        //phoneInput = findViewById(R.id.profile_phone);
        updateProfileBtn = findViewById(R.id.profle_update_btn);
        //progressBar = findViewById(R.id.profile_progress_bar);
        mFirebaseAuth = FirebaseAuth.getInstance();
        logoutBtn = findViewById(R.id.logout_btn);
        // Set the logout button click listener
        logoutBtn.setOnClickListener(v -> logout());

        // Image picker launcher for selecting profile picture
        imagePickLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        if(data != null && data.getData() != null){
                            selectedImageUri = data.getData();
                            ProfileUtil.setProfilePic(this, selectedImageUri, profilePic);  // Correct method call
                        }
                    }
                }
        );

        // Update profile button click listener
        updateProfileBtn.setOnClickListener(v -> updateBtnClick());

        // Profile picture click listener for changing profile picture
        profilePic.setOnClickListener(v -> {
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512, 512)
                    .createIntent(intent -> {
                        imagePickLauncher.launch(intent);
                        return null;
                    });
        });
    }

    void updateBtnClick() {
        String newUsername = usernameInput.getText().toString();
        if(newUsername.isEmpty() || newUsername.length() < 3) {
            usernameInput.setError("Username length should be at least 3 chars");
            return;
        }
        currentUserModel.setUsername(newUsername);
        setInProgress(true);

        if(selectedImageUri != null) {
            FirebaseUtil.getCurrentProfilePicStorageRef().putFile(selectedImageUri)
                    .addOnCompleteListener(task -> {
                        // After image is uploaded, update other fields
                        updateToFirestore();
                    });
        } else {
            updateToFirestore();
        }
    }

    void logout() {
        mFirebaseAuth.signOut();

        // Redirect to LoginActivity
        Intent intent = new Intent(Profile.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    // Method to update the user details in Firestore
    void updateToFirestore() {
        FirebaseUtil.currentUserDetails().set(currentUserModel)
                .addOnCompleteListener(task -> {
                    setInProgress(false);
                    if(task.isSuccessful()) {
                        ProfileUtil.showToast(this, "Updated successfully");
                    } else {
                        ProfileUtil.showToast(this, "Update failed");
                    }
                });
    }

    // Method to show or hide progress
    void setInProgress(boolean inProgress) {
        if(inProgress) {
            //progressBar.setVisibility(View.VISIBLE);
            updateProfileBtn.setVisibility(View.GONE);
        } else {
            //progressBar.setVisibility(View.GONE);
            updateProfileBtn.setVisibility(View.VISIBLE);
        }
    }
}
