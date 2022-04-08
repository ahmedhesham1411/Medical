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
import com.gharbia.medical.databinding.ActivityRegisterBinding;
import com.gharbia.medical.viewModel.RegisterViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;

import static com.gharbia.medical.viewModel.RegisterViewModel.IMAGE_REQUEST;

public class RegisterActivity extends BaseActivity {

    public ActivityRegisterBinding binding;
    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        viewModel = new RegisterViewModel(this);
        binding.setSignUpVM(viewModel);
        binding.backFrame.setOnClickListener(view -> finish());

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
                viewModel.imageBase64 = viewModel.encodeImage(bitmapImage);
                //encode image to base64 string
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        return Base64.encodeToString(outputStream.toByteArray(), Base64.NO_WRAP).replace("\n\t", "");
    }

}