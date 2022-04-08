package com.gharbia.medical.Activities;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Base64;
import android.util.Log;

import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.databinding.ActivityEditProfileBinding;
import com.gharbia.medical.viewModel.EditProfileViewModel;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;

import static com.gharbia.medical.viewModel.RegisterViewModel.IMAGE_REQUEST;

public class EditProfileActivity extends BaseActivity {

    public ActivityEditProfileBinding binding;
    EditProfileViewModel viewModel;
    public String name,email,image,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        viewModel = new EditProfileViewModel(this);
        binding.setVerificationVM(viewModel);

        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        image = getIntent().getStringExtra("image");
        phone = getIntent().getStringExtra("phone");

        binding.usernameText.setText(name);
        binding.phoneTxt.setText(phone);
        binding.emailTxt.setText(email);
        Picasso.get().load(Constant.BASEURL+image).into(binding.profileImageeee);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null){

            Uri imageUri = data.getData();
            Bitmap bitmapImage;
            try {
                bitmapImage = getBitmapFromUri(imageUri);
                binding.profileImageeee.setImageBitmap(bitmapImage);
                viewModel.imageBase64 = (convertBitmapToBase64(bitmapImage));
                Log.d("mybase",viewModel.imageBase64);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap getBitmapFromUri(Uri uri) throws IOException {
        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(uri, "r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
        parcelFileDescriptor.close();
        return image;
    }

    public static String convertBitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP);
        //return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP).replace("\n\t", "");
    }

}