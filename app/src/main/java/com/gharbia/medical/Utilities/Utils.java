package com.gharbia.medical.Utilities;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gharbia.medical.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Utils {

    public static void hideKeyboard(Activity activity) {
        try {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
            //Find the currently focused view, so we can grab the correct window token from it.
            View view = activity.getCurrentFocus();
            //If no view currently has focus, create a new one, just so we can grab a window token from it
            if (view == null) {
                view = new View(activity);
            }
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Activity getActivity() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        Class activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
        Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
        activitiesField.setAccessible(true);

        Map<Object, Object> activities = (Map<Object, Object>) activitiesField.get(activityThread);
        if (activities == null)
            return null;

        for (Object activityRecord : activities.values()) {
            Class activityRecordClass = activityRecord.getClass();
            Field pausedField = activityRecordClass.getDeclaredField("paused");
            pausedField.setAccessible(true);
            if (!pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                return activity;
            }
        }

        return null;
    }

    public static void MyToastError(Activity activity, String message) {
        Animation scaleAnimation;
        scaleAnimation = AnimationUtils.loadAnimation(activity, R.anim.scale_animation);
        // Get your custom_toast.xml ayout
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_error,
                (ViewGroup) activity.findViewById(R.id.color_toast_view));
        // set a message
        AppCompatTextView title = (AppCompatTextView) layout.findViewById(R.id.color_toast_text);
        AppCompatTextView text = (AppCompatTextView) layout.findViewById(R.id.color_toast_description);
        title.setText(activity.getString(R.string.failed));
        text.setText(message);
        AppCompatImageView imageView = layout.findViewById(R.id.color_toast_image);
        imageView.startAnimation(scaleAnimation);
        // Toast...
        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void MyToastSuccess(Activity activity, String message) {
        Animation scaleAnimation;
        scaleAnimation = AnimationUtils.loadAnimation(activity, R.anim.scale_animation);
        // Get your custom_toast.xml ayout
        LayoutInflater inflater = activity.getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_success,
                (ViewGroup) activity.findViewById(R.id.color_toast_view));
        // set a message
        AppCompatTextView title = (AppCompatTextView) layout.findViewById(R.id.color_toast_text);
        AppCompatTextView text = (AppCompatTextView) layout.findViewById(R.id.color_toast_description);
        title.setText(activity.getString(R.string.success));
        if (message.equals("")){
            text.setVisibility(View.GONE);
        }else {
            text.setText(message);
        }
        AppCompatImageView imageView = layout.findViewById(R.id.color_toast_image);
        imageView.startAnimation(scaleAnimation);
        // Toast...
        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }

    public static void runAnimation2(LinearLayoutCompat recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 1) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_scale_from_center);
            //controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_rise_up);
        } else if (type == 2){
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_right);
        }
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    public static void runAnimationRelativeLay(RelativeLayout recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 1) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_scale_from_center);
            //controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_rise_up);
        } else if (type == 2){
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_right);
        }
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    public static void runAnimation3(FrameLayout recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 1) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_scale_from_center);
            //controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_rise_up);
        } else if (type == 2){
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_right);
        }
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }

    public static void runAnimation(RecyclerView recyclerView, int type) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = null;
        if (type == 1) {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_rise_up);
        } else if (type==3){
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_scale_from_center);
        }
        else {
            controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_slide_from_right);
        }
        recyclerView.setLayoutAnimation(controller);
        recyclerView.scheduleLayoutAnimation();
    }
}
