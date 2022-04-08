package com.gharbia.medical.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import com.gharbia.medical.Activities.IntroActivity;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.IntroItemBinding;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;
import java.util.List;

public class IntroAdapter extends SliderViewAdapter<IntroAdapter.SliderAdapterVH> {

    IntroActivity context;
    List<Integer> imagesList;
    SliderView sliderView;

    public IntroAdapter(IntroActivity context, List<Integer> imagesList,SliderView sliderView) {
        this.context = context;
        this.imagesList = imagesList;
        this.sliderView = sliderView;
    }


    @Override
    public IntroAdapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        IntroItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.intro_item, parent, false);
        return new SliderAdapterVH(binding);
    }

    @Override
    public void onBindViewHolder(IntroAdapter.SliderAdapterVH viewHolder, int position) {

        viewHolder.binding.imageHome.setEnabled(false);
        viewHolder.binding.imageHome.setClickable(false);
        List<Integer> imageslist1 = imagesList;
        for (int j=0; j < imageslist1.size(); j++){
            int image = imageslist1.get(position);
            viewHolder.binding.imageHome.setImageResource(image);
        }
        context.sliderView.setCurrentPageListener(currentPosition -> {
            if (currentPosition==0){
                context.binding.line1.setVisibility(View.VISIBLE);
                context.binding.line2.setVisibility(View.GONE);
                context.binding.line3.setVisibility(View.GONE);
            }else if (currentPosition==1){
                context.binding.line1.setVisibility(View.GONE);
                context.binding.line2.setVisibility(View.VISIBLE);
                context.binding.line3.setVisibility(View.GONE);
            }else {
                context.binding.line1.setVisibility(View.GONE);
                context.binding.line2.setVisibility(View.GONE);
                context.binding.line3.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getCount() {
        return imagesList.size();
    }

    static class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        private final IntroItemBinding binding;

        private SliderAdapterVH(IntroItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
