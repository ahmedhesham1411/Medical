package com.gharbia.medical.viewModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.gharbia.medical.DataModel.QuestionsResponse;
import com.gharbia.medical.Network.NetworkUtil;
import com.gharbia.medical.R;
import com.gharbia.medical.Utilities.Constant;
import com.gharbia.medical.Utilities.Preferences;
import com.gharbia.medical.fragment.QuestionsFragment;

import java.util.Locale;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class QuestionViewModel extends ViewModel {

    QuestionsFragment fragment;
    @SuppressLint("StaticFieldLeak")
    Activity activity;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();
    AlertDialog alertDialog;
    String whatsApp;

    public QuestionViewModel(QuestionsFragment fragment) {
        this.fragment = fragment;
        activity = fragment.getActivity();

        GetQuestions();
        whatsApp = Preferences.GetWhatsApp(activity);
    }

    private void GetQuestions() {
        showLoadingDialog(activity);
        mSubscriptions.add(NetworkUtil.getRetrofitNoHeaderMultiPart()
                .GetQuestions(Locale.getDefault().getLanguage())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));

    }

    private void handleResponse(QuestionsResponse questionsResponse) {
        alertDialog.dismiss();
        fragment.initRec(questionsResponse);
    }

    private void handleError(Throwable throwable) {
        alertDialog.dismiss();
        Constant.handleErrors(throwable,activity);
    }

    public void whatsapp() {
        goTowhatsApp(activity, whatsApp);
    }

    public static void goTowhatsApp(Activity activity, String smsNumber) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://api.whatsapp.com/send?phone=+2"+smsNumber+"&text=")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }
}