package com.gharbia.medical.Utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    public static void saveImageToPreference(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("image", image);
        editor.commit();
    }

    public static String getImageToPreference(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("image","default");
    }

    public static void saveToken(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Token", Token);
        editor.commit();
    }

    public static String GetToken(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Token","default");
    }

    public static void savIsFirst(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("first", Token);
        editor.commit();
    }

    public static String GetIsFirst(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("first","default");
    }

    public static void saveCityId(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("cityId", Token);
        editor.commit();
    }

    public static String GetCityName(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("cityName","noName");
    }

    public static void saveCityName(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("cityName", Token);
        editor.commit();
    }

    public static String GetCityId(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("cityId","0");
    }

    public static void saveWhatsApp(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("whats", Token);
        editor.commit();
    }

    public static String GetWhatsApp(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("whats","0");
    }

    public static void ShowIntro(Context context, String Intro){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Intro", Intro);
        editor.commit();
    }

    public static String GetShowIntro(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Intro","yes");
    }

    public static void SaveRemember(Context context, String Remember){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Remember", Remember);
        editor.commit();
    }

    public static String GetRemember(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Remember","default");
    }

    public static void saveEmail(Context context, String email){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Email", email);
        editor.commit();
    }


    public static String GetEmail(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Email","default");
    }

    public static void saveName(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", Token);
        editor.commit();
    }

    public static String getName(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("name","");
    }

    public static void saveId(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("id", Token);
        editor.commit();
    }

    public static String getId(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("id","");
    }

    public static void savePhone(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("phone", Token);
        editor.commit();
    }

    public static String getPhone(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("phone","");
    }

    public static void savePhoto(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("photo", Token);
        editor.commit();
    }

    public static String getPhoto(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("photo","");
    }

    public static void saveStatus(Context context, String Token){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Status", Token);
        editor.commit();
    }

    public static String getStatus(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Status","");
    }

    public static void saveUserState(Context context, String email){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("state", email);
        editor.commit();
    }


    public static String GetUserState(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("state","false");
    }

    public static void SaveForgetPasswordEmail(Context context, String Email){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Email", Email);
        editor.commit();
    }

    public static String GetForgetPasswordEmail(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Email","default email");
    }

    public static void SaveVerificationCode(Context context, String Ver_code){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Code", Ver_code);
        editor.commit();
    }

    public static String GetCode(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Code","default Code");
    }

    public static void SaveUname(Context context, String Uname){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Uname", Uname);
        editor.commit();
    }

    public static String GetUname(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Uname","default name");
    }

    public static void SaveUmail(Context context, String Pass){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Umail", Pass);
        editor.commit();
    }

    public static String GetUmail(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Umail","default pass");
    }

    public static void SaveUmobile(Context context, String mobile){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Umobile", mobile);
        editor.commit();
    }

    public static String GetUmobile(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Umobile","default mobile");
    }

    public static void SaveUimage(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Uimage", image);
        editor.commit();
    }

    public static String GetUimage(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("Uimage","default mobile");
    }

    public static void SaveMainCat_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("MainCat_id", id);
        editor.commit();
    }

    public static Integer GetMainCaId(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("MainCat_id",1);
    }

    public static void SaveMainCat_name(Context context, String name){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("MainCat_name", name);
        editor.commit();
    }

    public static String GetMainCaName(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("MainCat_name","a");
    }

    public static void SaveMainCat_image(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("MainCat_image", image);
        editor.commit();
    }

    public static String GetMainCaImage(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("MainCat_image","");
    }

    public static void Clear(Context context)
    {
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public static void Save_image1(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("image1", image);
        editor.commit();
    }

    public static String Get_image1(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("image1","");
    }

    public static void Save_name(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", image);
        editor.commit();
    }

    public static String Get_name(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("name","");
    }

    public static void Save_image2(Context context, String image){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("image2", image);
        editor.commit();
    }

    public static String Get_image2(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("image2","");
    }

    public static void Save_Sub_name(Context context, String name){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("subnamee", name);
        editor.commit();
    }

    public static String Get_sub_name(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("subnamee","");
    }

    public static void Save_Sub_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("subidd", id);
        editor.commit();
    }

    public static int Get_sub_id(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("subidd",0);
    }

    public static void Save_Child_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("childidd", id);
        editor.commit();
    }

    public static int Get_Child_id(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("childidd",0);
    }

    public static void Save_Child_name(Context context, String name){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("childnamee", name);
        editor.commit();
    }

    public static String Get_Child_name(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("childnamee","a");
    }

    public static void Save_company_id(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("companyid", id);
        editor.commit();
    }

    public static int Get_company_id(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("companyid",0);
    }

    public static void Save_listt(Context context, String list){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("sliderimages", list);
        editor.commit();
    }

    public static String Get_listt(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("sliderimages","a");
    }

    public static void SaveAddressId(Context context, int id){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("address_id", id);
        editor.commit();
    }

    public static int GetAddressId(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getInt("address_id",-1);
    }

    public static void SaveAddressTitle(Context context, String state){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("address", state);
        editor.commit();
    }

    public static String GetAddressTitle(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("address","");
    }

    public static void SaveAddressName(Context context, String state){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("address_name", state);
        editor.commit();
    }

    public static String GetAddressName(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("address_name","");
    }

    public static void SaveAddressPhone(Context context, String state){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("address_phone", state);
        editor.commit();
    }

    public static String GetAddressPhone(Context context){
        SharedPreferences pref = context.getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        return pref.getString("address_phone","");
    }
}
