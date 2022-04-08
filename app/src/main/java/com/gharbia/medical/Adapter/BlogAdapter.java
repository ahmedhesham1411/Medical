package com.gharbia.medical.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.DataModel.BlogResponse;
import com.gharbia.medical.R;
import com.gharbia.medical.databinding.BlogItemBinding;
import com.gharbia.medical.databinding.NotificationsItemBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;

import java.util.List;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {

    Activity activity;
    int num;
    private String currentVideoId;
    private final List<String> videoIds;
    private final Lifecycle lifecycle;
    List<BlogResponse.BlogResponse2> blogResponse2s;

    public BlogAdapter(List<String> videoIds, Lifecycle lifecycle, List<BlogResponse.BlogResponse2> blogResponse2s,Activity activity) {
        this.videoIds = videoIds;
        this.lifecycle = lifecycle;
        this.activity = activity;
        this.blogResponse2s = blogResponse2s;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BlogItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.blog_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.binding.createdAt.setText(blogResponse2s.get(position).getCraetedOn());
            holder.binding.title.setText(blogResponse2s.get(position).getTitle());
            holder.binding.disc.setText(blogResponse2s.get(position).getDescription());
            //Picasso.get().load(Constant.BASEURL+blogResponse2s.get(position).getImageUrl()).placeholder(activity.getDrawable(R.drawable.eewweeww)).into(holder.binding.img);
            holder.cueVideo(videoIds.get(position));
    }



    @Override
    public int getItemCount() {
        return videoIds.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final BlogItemBinding binding;
        private YouTubePlayer youTubePlayer;
        String currentVideoId;

        private ViewHolder(BlogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                @Override
                public void onReady(@NonNull YouTubePlayer initializedYouTubePlayer) {
                    youTubePlayer = initializedYouTubePlayer;
                    youTubePlayer.cueVideo(currentVideoId, 0);
                }
            });

        }

        void cueVideo(String videoId) {
            currentVideoId = videoId;
            if(youTubePlayer == null)
                return;
            }

    }
}
