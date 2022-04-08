package com.gharbia.medical.Utilities;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.NetworkRequest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;

import com.gharbia.medical.R;

import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;
import retrofit2.HttpException;

public class Constant {
    public static AlertDialog alertDialog1,alertDialog;
    public static String BASEURL = "https://www.gmg-dewan.com/";
    Context activity;

    public static boolean CheckInternet(Activity activity){
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()){
            Toast.makeText(activity, activity.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            GlobalVariables.IS_CONNECTED = false;
        }else {
            GlobalVariables.IS_CONNECTED = true;
        }

        connectivityManager.registerNetworkCallback(
                new NetworkRequest.Builder().build(),
                new ConnectivityManager.NetworkCallback() {

                    @Override
                    public void onAvailable(Network network) {
                        super.onAvailable(network);
                        if (activeNetworkInfo != null) {
                            try {
                                URL url = new URL("http://www.google.com/");
                                HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
                                urlc.setRequestProperty("User-Agent", "test");
                                urlc.setRequestProperty("Connection", "close");
                                urlc.setConnectTimeout(1000); // mTimeout is in seconds
                                urlc.connect();
                                if (urlc.getResponseCode() == 200) {
                                    GlobalVariables.IS_CONNECTED = true;
                                } else {
                                    //onLost(network);
                                }
                            } catch (IOException e) {
                                Log.i("warning", "Error checking internet connection", e);
                                //onLost(network);
                            }
                        }
                    }

                    @Override
                    public void onLost(Network network) {
                        super.onLost(network);
                        Toast.makeText(activity, activity.getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                        GlobalVariables.IS_CONNECTED = false;
                    }
                });

        return GlobalVariables.IS_CONNECTED;
    }


    public static void handleErrors(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("errorMessage");

            /*response = response.replace("{","");
            response = response.replace("}","");
            response = response.replace("\"","");
            response = response.replace("\'","");
            response = response.replace(",","\n");
            response = response.replace("details","");
            response = response.replace(":"," ");*/
            showErrorDialog(response,context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleErrorssss(Throwable throwable, Activity context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("message");

            /*response = response.replace("{","");
            response = response.replace("}","");
            response = response.replace("\"","");
            response = response.replace("\'","");
            response = response.replace(",","\n");
            response = response.replace("details","");
            response = response.replace(":"," ");*/
            showErrorDialogggg(response,context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void handleErrors12(Throwable throwable, Activity context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("message");

            /*response = response.replace("{","");
            response = response.replace("}","");
            response = response.replace("\"","");
            response = response.replace("\'","");
            response = response.replace(",","\n");
            response = response.replace("details","");
            response = response.replace(":"," ");*/
            showErrorDialog12(response,context);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void handleErrorsaa(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("details");
            String response2 = response.replace(",", "\n");
            String response3 = response2.replace("{", "");
            String response4 = response3.replace("}", "");
            String response5 = response4.replace("\"", "");

            showErrorDialog(response5,context);

           /* switch (response) {

                //Register
                case "3":
                    showErrorDialog("Server is too busy",context);
                    Log.d(TAG, "handleErrors: user id is null");
                    break;
                case "1":
                    showErrorDialog("Not Valid Model",context);
                    Log.d(TAG, "handleErrors: password is null");
                    break;
                case "4":
                    showErrorDialog("Password lenght is small",context);
                    Log.d(TAG, "handleErrors: user id incorrect");
                    break;
                case "6":
                    showErrorDialog("password and confirm password doesn't match",context);
                    Log.d(TAG, "handleErrors: can't handle or parse your model to token say to user the server is busy or try login again or some thing like that");
                    break;
                case "2":
                    showErrorDialog("User already exists",context);
                    Log.d(TAG, "handleErrors: password incorrect");
                    break;

                    //Login
                case "11":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: the old password incorrect");
                    break;
                case "12":
                    showErrorDialog("User name is empty",context);
                    Log.d(TAG, "handleErrors: the model you entered is incorrect as format");
                    break;
                case "13":
                    showErrorDialog("Password is empty",context);
                    Log.d(TAG, "handleErrors: token is null");
                    break;
                case "14":
                    showErrorDialog("User not exists",context);
                    Log.d(TAG, "handleErrors: your id code is null");
                    break;
                case "15":
                    showErrorDialog("Can't create token",context);
                    Log.d(TAG, "handleErrors: Can't create token");
                    break;
                case "16":
                    showErrorDialog("Password is incorrect",context);
                    Log.d(TAG, "handleErrors: Password is incorrect");
                    break;

                    //Change_password
                case "82":
                    showErrorDialog("Incorrect password",context);
                    Log.d(TAG, "handleErrors: Incorrect password");
                    break;
                case "70":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate token");
                    break;
                case "71":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: Model is not valid");
                    break;

                    //Forget_password
                case "23":
                    showErrorDialog("Please check your connection",context);
                    Log.d(TAG, "handleErrors: Please check your connection");
                    break;
                case "78":
                    showErrorDialog("Can't reset password",context);
                    Log.d(TAG, "handleErrors: Can't reset password");
                    break;

                    //profile
                case "72":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate this token");
                    break;
                case "770":
                    showErrorDialog("User can't be found",context);
                    Log.d(TAG, "handleErrors: User can't be found");
                    break;

                default:
                    showErrorDialog("Something is wrong",context);
                    Log.d(TAG, "handleErrors: "+throwable.getMessage());
                    break;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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


    public static void showErrorDialog(String errorMsg, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_error, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        AppCompatTextView error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        AppCompatTextView ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(v -> alertDialog1.dismiss());
    }

    public static void showErrorDialogggg(String errorMsg, Activity context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_error, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        AppCompatTextView error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        AppCompatTextView ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                context.finish();
            }
        });
    }

    public static void showErrorDialog12(String errorMsg, Activity context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_error, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.setCancelable(false);
        alertDialog1.show();
        AppCompatTextView error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        AppCompatTextView ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                context.finish();
            }
        });
    }

/*    public static void showLoadingDialog(Context activity) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View view = layoutInflater.inflate(R.layout.loading, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(activity,R.style.SheetDialog);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.setCancelable(false);
    }*/

    public static void dismissDialog(Context activity) {
        alertDialog.dismiss();
    }

/*
    public static void showCodeDialog(String code, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_code, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(code);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }
*/

/*    public static void showErrorDialog(String errorMsg, Context context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_error, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        MyTextViewCairo error = view.findViewById(R.id.error_message);
        error.setText(errorMsg);
        MyButtonBold ok;
        ok = view.findViewById(R.id.btn_error_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }

    public static void logInDialog(String errorMsg, Activity context) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_alert_login, null, false);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.ErrorDialog);
        builder.setView(view);
        alertDialog1 = builder.create();
        alertDialog1.show();
        MyTextViewCairo yes,no;
        yes = view.findViewById(R.id.yes_txt);
        no = view.findViewById(R.id.no_txt);
        AppCompatImageView close;
        close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog1.dismiss();
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                Intent intent = new Intent(context, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intent);
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
            }
        });
    }*/

/*    public static void handleErrors(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("msg");
            showErrorDialog(response,context);

           *//* switch (response) {

                //Register
                case "3":
                    showErrorDialog("Server is too busy",context);
                    Log.d(TAG, "handleErrors: user id is null");
                    break;
                case "1":
                    showErrorDialog("Not Valid Model",context);
                    Log.d(TAG, "handleErrors: password is null");
                    break;
                case "4":
                    showErrorDialog("Password lenght is small",context);
                    Log.d(TAG, "handleErrors: user id incorrect");
                    break;
                case "6":
                    showErrorDialog("password and confirm password doesn't match",context);
                    Log.d(TAG, "handleErrors: can't handle or parse your model to token say to user the server is busy or try login again or some thing like that");
                    break;
                case "2":
                    showErrorDialog("User already exists",context);
                    Log.d(TAG, "handleErrors: password incorrect");
                    break;

                    //Login
                case "11":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: the old password incorrect");
                    break;
                case "12":
                    showErrorDialog("User name is empty",context);
                    Log.d(TAG, "handleErrors: the model you entered is incorrect as format");
                    break;
                case "13":
                    showErrorDialog("Password is empty",context);
                    Log.d(TAG, "handleErrors: token is null");
                    break;
                case "14":
                    showErrorDialog("User not exists",context);
                    Log.d(TAG, "handleErrors: your id code is null");
                    break;
                case "15":
                    showErrorDialog("Can't create token",context);
                    Log.d(TAG, "handleErrors: Can't create token");
                    break;
                case "16":
                    showErrorDialog("Password is incorrect",context);
                    Log.d(TAG, "handleErrors: Password is incorrect");
                    break;

                    //Change_password
                case "82":
                    showErrorDialog("Incorrect password",context);
                    Log.d(TAG, "handleErrors: Incorrect password");
                    break;
                case "70":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate token");
                    break;
                case "71":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: Model is not valid");
                    break;

                    //Forget_password
                case "23":
                    showErrorDialog("Please check your connection",context);
                    Log.d(TAG, "handleErrors: Please check your connection");
                    break;
                case "78":
                    showErrorDialog("Can't reset password",context);
                    Log.d(TAG, "handleErrors: Can't reset password");
                    break;

                    //profile
                case "72":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate this token");
                    break;
                case "770":
                    showErrorDialog("User can't be found",context);
                    Log.d(TAG, "handleErrors: User can't be found");
                    break;

                default:
                    showErrorDialog("Something is wrong",context);
                    Log.d(TAG, "handleErrors: "+throwable.getMessage());
                    break;
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void handleErrorsaa(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("details");
            String response2 = response.replace(",", "\n");
            String response3 = response2.replace("{", "");
            String response4 = response3.replace("}", "");
            String response5 = response4.replace("\"", "");

            showErrorDialog(response5,context);

           *//* switch (response) {

                //Register
                case "3":
                    showErrorDialog("Server is too busy",context);
                    Log.d(TAG, "handleErrors: user id is null");
                    break;
                case "1":
                    showErrorDialog("Not Valid Model",context);
                    Log.d(TAG, "handleErrors: password is null");
                    break;
                case "4":
                    showErrorDialog("Password lenght is small",context);
                    Log.d(TAG, "handleErrors: user id incorrect");
                    break;
                case "6":
                    showErrorDialog("password and confirm password doesn't match",context);
                    Log.d(TAG, "handleErrors: can't handle or parse your model to token say to user the server is busy or try login again or some thing like that");
                    break;
                case "2":
                    showErrorDialog("User already exists",context);
                    Log.d(TAG, "handleErrors: password incorrect");
                    break;

                    //Login
                case "11":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: the old password incorrect");
                    break;
                case "12":
                    showErrorDialog("User name is empty",context);
                    Log.d(TAG, "handleErrors: the model you entered is incorrect as format");
                    break;
                case "13":
                    showErrorDialog("Password is empty",context);
                    Log.d(TAG, "handleErrors: token is null");
                    break;
                case "14":
                    showErrorDialog("User not exists",context);
                    Log.d(TAG, "handleErrors: your id code is null");
                    break;
                case "15":
                    showErrorDialog("Can't create token",context);
                    Log.d(TAG, "handleErrors: Can't create token");
                    break;
                case "16":
                    showErrorDialog("Password is incorrect",context);
                    Log.d(TAG, "handleErrors: Password is incorrect");
                    break;

                    //Change_password
                case "82":
                    showErrorDialog("Incorrect password",context);
                    Log.d(TAG, "handleErrors: Incorrect password");
                    break;
                case "70":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate token");
                    break;
                case "71":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: Model is not valid");
                    break;

                    //Forget_password
                case "23":
                    showErrorDialog("Please check your connection",context);
                    Log.d(TAG, "handleErrors: Please check your connection");
                    break;
                case "78":
                    showErrorDialog("Can't reset password",context);
                    Log.d(TAG, "handleErrors: Can't reset password");
                    break;

                    //profile
                case "72":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate this token");
                    break;
                case "770":
                    showErrorDialog("User can't be found",context);
                    Log.d(TAG, "handleErrors: User can't be found");
                    break;

                default:
                    showErrorDialog("Something is wrong",context);
                    Log.d(TAG, "handleErrors: "+throwable.getMessage());
                    break;
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
/*
    public static void handleErrorsProfile(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("message");
            showErrorDialog(response,context);

           */
/* switch (response) {

                //Register
                case "3":
                    showErrorDialog("Server is too busy",context);
                    Log.d(TAG, "handleErrors: user id is null");
                    break;
                case "1":
                    showErrorDialog("Not Valid Model",context);
                    Log.d(TAG, "handleErrors: password is null");
                    break;
                case "4":
                    showErrorDialog("Password lenght is small",context);
                    Log.d(TAG, "handleErrors: user id incorrect");
                    break;
                case "6":
                    showErrorDialog("password and confirm password doesn't match",context);
                    Log.d(TAG, "handleErrors: can't handle or parse your model to token say to user the server is busy or try login again or some thing like that");
                    break;
                case "2":
                    showErrorDialog("User already exists",context);
                    Log.d(TAG, "handleErrors: password incorrect");
                    break;

                    //Login
                case "11":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: the old password incorrect");
                    break;
                case "12":
                    showErrorDialog("User name is empty",context);
                    Log.d(TAG, "handleErrors: the model you entered is incorrect as format");
                    break;
                case "13":
                    showErrorDialog("Password is empty",context);
                    Log.d(TAG, "handleErrors: token is null");
                    break;
                case "14":
                    showErrorDialog("User not exists",context);
                    Log.d(TAG, "handleErrors: your id code is null");
                    break;
                case "15":
                    showErrorDialog("Can't create token",context);
                    Log.d(TAG, "handleErrors: Can't create token");
                    break;
                case "16":
                    showErrorDialog("Password is incorrect",context);
                    Log.d(TAG, "handleErrors: Password is incorrect");
                    break;

                    //Change_password
                case "82":
                    showErrorDialog("Incorrect password",context);
                    Log.d(TAG, "handleErrors: Incorrect password");
                    break;
                case "70":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate token");
                    break;
                case "71":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: Model is not valid");
                    break;

                    //Forget_password
                case "23":
                    showErrorDialog("Please check your connection",context);
                    Log.d(TAG, "handleErrors: Please check your connection");
                    break;
                case "78":
                    showErrorDialog("Can't reset password",context);
                    Log.d(TAG, "handleErrors: Can't reset password");
                    break;

                    //profile
                case "72":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate this token");
                    break;
                case "770":
                    showErrorDialog("User can't be found",context);
                    Log.d(TAG, "handleErrors: User can't be found");
                    break;

                default:
                    showErrorDialog("Something is wrong",context);
                    Log.d(TAG, "handleErrors: "+throwable.getMessage());
                    break;
            }*//*

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void handleErrors2(Throwable throwable, Context context){
        try {
            JSONObject jsonObject = new JSONObject(Objects.requireNonNull(Objects.requireNonNull(((HttpException) throwable).response()).errorBody()).string());
            String response = jsonObject.getString("details");
            showErrorDialog(response,context);

           */
/* switch (response) {

                //Register
                case "3":
                    showErrorDialog("Server is too busy",context);
                    Log.d(TAG, "handleErrors: user id is null");
                    break;
                case "1":
                    showErrorDialog("Not Valid Model",context);
                    Log.d(TAG, "handleErrors: password is null");
                    break;
                case "4":
                    showErrorDialog("Password lenght is small",context);
                    Log.d(TAG, "handleErrors: user id incorrect");
                    break;
                case "6":
                    showErrorDialog("password and confirm password doesn't match",context);
                    Log.d(TAG, "handleErrors: can't handle or parse your model to token say to user the server is busy or try login again or some thing like that");
                    break;
                case "2":
                    showErrorDialog("User already exists",context);
                    Log.d(TAG, "handleErrors: password incorrect");
                    break;

                    //Login
                case "11":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: the old password incorrect");
                    break;
                case "12":
                    showErrorDialog("User name is empty",context);
                    Log.d(TAG, "handleErrors: the model you entered is incorrect as format");
                    break;
                case "13":
                    showErrorDialog("Password is empty",context);
                    Log.d(TAG, "handleErrors: token is null");
                    break;
                case "14":
                    showErrorDialog("User not exists",context);
                    Log.d(TAG, "handleErrors: your id code is null");
                    break;
                case "15":
                    showErrorDialog("Can't create token",context);
                    Log.d(TAG, "handleErrors: Can't create token");
                    break;
                case "16":
                    showErrorDialog("Password is incorrect",context);
                    Log.d(TAG, "handleErrors: Password is incorrect");
                    break;

                    //Change_password
                case "82":
                    showErrorDialog("Incorrect password",context);
                    Log.d(TAG, "handleErrors: Incorrect password");
                    break;
                case "70":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate token");
                    break;
                case "71":
                    showErrorDialog("Model is not valid",context);
                    Log.d(TAG, "handleErrors: Model is not valid");
                    break;

                    //Forget_password
                case "23":
                    showErrorDialog("Please check your connection",context);
                    Log.d(TAG, "handleErrors: Please check your connection");
                    break;
                case "78":
                    showErrorDialog("Can't reset password",context);
                    Log.d(TAG, "handleErrors: Can't reset password");
                    break;

                    //profile
                case "72":
                    showErrorDialog("Can't generate token",context);
                    Log.d(TAG, "handleErrors: Can't generate this token");
                    break;
                case "770":
                    showErrorDialog("User can't be found",context);
                    Log.d(TAG, "handleErrors: User can't be found");
                    break;

                default:
                    showErrorDialog("Something is wrong",context);
                    Log.d(TAG, "handleErrors: "+throwable.getMessage());
                    break;
            }*//*

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/


}
