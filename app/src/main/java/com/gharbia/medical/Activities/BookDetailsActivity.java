package com.gharbia.medical.Activities;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.gharbia.medical.Adapter.ImageItemResultAdapter;
import com.gharbia.medical.Adapter.ReviewNewAdapter;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.BaseActivity;
import com.gharbia.medical.databinding.ActivityBookDetailsBinding;
import com.gharbia.medical.viewModel.BookDetailsViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class BookDetailsActivity extends BaseActivity implements OnMapReadyCallback {

    ImageItemResultAdapter adapter;
    public ActivityBookDetailsBinding binding;
    BookDetailsViewModel viewModel;
    public String bookId = "0";
    public String status = "0";
    ReviewNewAdapter reviewNewAdapter;
    public GoogleMap mMap;
    public double lat,lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_book_details);
        viewModel = new BookDetailsViewModel(this);
        binding.setVerificationVM(viewModel);
        binding.map.onCreate(savedInstanceState);

    }

    public void initRecImages(List<String> images){
        adapter = new ImageItemResultAdapter(this,images);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.imagesRecycler.setLayoutManager(linearLayoutManager);
        binding.imagesRecycler.setHasFixedSize(true);
        binding.imagesRecycler.setAdapter(adapter);
    }

    public void initRec(ReviewResponse reviewResponse){
        reviewNewAdapter = new ReviewNewAdapter(this,reviewResponse.getData());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.reviewsRecycler.setLayoutManager(linearLayoutManager);
        binding.reviewsRecycler.setHasFixedSize(true);
        binding.reviewsRecycler.setAdapter(reviewNewAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("RETURN", "return");
            return;
        }

        try {
            setUpMap();

            Log.d("latA",String.valueOf(lat));
            LatLng latLng = new LatLng(lat,lng);

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(latLng).zoom(14).build();
            mMap.animateCamera(CameraUpdateFactory
                    .newCameraPosition(cameraPosition));

            mMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .anchor(0.5f, 0.5f)
                    .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(true))));

            Log.e("cameraMidLatLng", latLng.latitude + "\t" + latLng.longitude);
        }catch (Exception e){
            Log.e("cameraMidLatLng2", "qqqqqqq");
        }
    }

    private void setUpMap() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String myMapStyle = "[{\"featureType\":\"all\",\"elementType\":\"labels.text.fill\",\"stylers\":[{\"color\":\"#7c93a3\"},{\"lightness\":\"-10\"}]},{\"featureType\":\"administrative.country\",\"elementType\":\"geometry\",\"stylers\":[{\"visibility\":\"on\"}]},{\"featureType\":\"administrative.country\",\"elementType\":\"geometry.stroke\",\"stylers\":[{\"color\":\"#c2d1d6\"}]},{\"featureType\":\"landscape\",\"elementType\":\"geometry.fill\",\"stylers\":[{\"color\":\"#dde3e3\"}]},{\"featureType\":\"road.highway\",\"elementType\":\"geometry.fill\",\"stylers\":[{\"color\":\"#c2d1d6\"}]},{\"featureType\":\"road.highway\",\"elementType\":\"geometry.stroke\",\"stylers\":[{\"color\":\"#a9b4b8\"},{\"lightness\":\"0\"}]},{\"featureType\":\"water\",\"elementType\":\"geometry.fill\",\"stylers\":[{\"color\":\"#a3c7df\"}]}]";
        MapStyleOptions style = new MapStyleOptions(myMapStyle);
        mMap.setMapStyle(style);
        mMap.getUiSettings().setZoomControlsEnabled(false);
    }

    public Bitmap getMarkerBitmapFromView(boolean isHome) {

        @SuppressLint("InflateParams") View customMarkerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_view_home_marker, null);

        LinearLayoutCompat title_lin = customMarkerView.findViewById(R.id.title_lin);
        AppCompatImageView icon_img = customMarkerView.findViewById(R.id.icon_img2);

        title_lin.setVisibility(View.GONE);
        icon_img.setImageResource(R.drawable.placeholder2);
        customMarkerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        customMarkerView.layout(0, 0, customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight());
        customMarkerView.buildDrawingCache();
        Bitmap returnedBitmap = Bitmap.createBitmap(customMarkerView.getMeasuredWidth(), customMarkerView.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);

        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = customMarkerView.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        customMarkerView.draw(canvas);
        return returnedBitmap;
    }
}