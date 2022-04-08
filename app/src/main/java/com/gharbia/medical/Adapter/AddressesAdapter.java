package com.gharbia.medical.Adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.AddressModel;
import com.gharbia.medical.Interfaces.AddressClick;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.AddressesItemBinding;
import com.gharbia.medical.databinding.AppointmentsItemBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class AddressesAdapter extends RecyclerView.Adapter<AddressesAdapter.ViewHolder> implements OnMapReadyCallback {

    Activity activity;
    List<AddressModel> addressModels;
    Bundle savedInstanceState2;
    public GoogleMap mMap;
    double lat,lng;
    AddressClick addressClick;
    int aa = 0;

    public AddressesAdapter(Activity activity, List<AddressModel> addressModels,Bundle savedInstanceState2,AddressClick addressClick) {
        this.activity = activity;
        this.addressModels = addressModels;
        this.savedInstanceState2 = savedInstanceState2;
        this.addressClick = addressClick;
    }

    @NonNull
    @Override
    public AddressesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AddressesItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.addresses_item, parent, false);
        return new AddressesAdapter.ViewHolder(binding);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull AddressesAdapter.ViewHolder holder, int position) {
        if (addressModels.get(position).isSelected())
            holder.binding.selectedImage.setImageDrawable(activity.getDrawable(R.drawable.radio_full));
        else
            holder.binding.selectedImage.setImageDrawable(activity.getDrawable(R.drawable.radio_empty2));

        holder.binding.selectedImage.setOnClickListener(view -> {
            addressClick.click(position);
            aa = 1;
        });

        holder.binding.address.setText(addressModels.get(position).getAddress());

        if (aa==0){
            lat = addressModels.get(position).getLatitude();
            lng = addressModels.get(position).getLongitude();
            holder.binding.map.onCreate(savedInstanceState2);
            holder.binding.map.getMapAsync(this);
            holder.binding.map.onResume();

            MapsInitializer.initialize(activity);
        }

        holder.binding.itemClick.setOnClickListener(view -> {
            try {
                goToLocationOnMap(activity,
                        addressModels.get(position).getLatitude(),
                        addressModels.get(position).getLongitude());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public static void goToLocationOnMap(Activity activity, double lat, double lng) {
        if (lat != -99999 && lng != -99999) {
            String strUri = "http://maps.google.com/maps?q=loc:" + lat + "," + lng + " (" + "Location" + ")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strUri));
            intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
            activity.startActivity(intent);
        }
    }
    @Override
    public int getItemCount() {
        return addressModels.size();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        String myMapStyle = "[{\"featureType\":\"all\",\"elementType\":\"labels.text.fill\",\"stylers\":[{\"color\":\"#7c93a3\"},{\"lightness\":\"-10\"}]},{\"featureType\":\"administrative.country\",\"elementType\":\"geometry\",\"stylers\":[{\"visibility\":\"on\"}]},{\"featureType\":\"administrative.country\",\"elementType\":\"geometry.stroke\",\"stylers\":[{\"color\":\"#c2d1d6\"}]},{\"featureType\":\"landscape\",\"elementType\":\"geometry.fill\",\"stylers\":[{\"color\":\"#dde3e3\"}]},{\"featureType\":\"road.highway\",\"elementType\":\"geometry.fill\",\"stylers\":[{\"color\":\"#c2d1d6\"}]},{\"featureType\":\"road.highway\",\"elementType\":\"geometry.stroke\",\"stylers\":[{\"color\":\"#a9b4b8\"},{\"lightness\":\"0\"}]},{\"featureType\":\"water\",\"elementType\":\"geometry.fill\",\"stylers\":[{\"color\":\"#a3c7df\"}]}]";
        MapStyleOptions style = new MapStyleOptions(myMapStyle);
        mMap.setMapStyle(style);
        mMap.getUiSettings().setZoomControlsEnabled(false);
    }

    public Bitmap getMarkerBitmapFromView(boolean isHome) {

        @SuppressLint("InflateParams") View customMarkerView = ((LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_view_home_marker, null);

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


    public class ViewHolder extends RecyclerView.ViewHolder{
        private AddressesItemBinding binding;

        private ViewHolder(AddressesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
