package com.gharbia.medical.Network;

import com.gharbia.medical.DataModel.BlogResponse;
import com.gharbia.medical.DataModel.BookDetailsResponse;
import com.gharbia.medical.DataModel.BookNowRequest;
import com.gharbia.medical.DataModel.CitiesResponse;
import com.gharbia.medical.DataModel.ClinicProfileResponse;
import com.gharbia.medical.DataModel.ClinicsList;
import com.gharbia.medical.DataModel.ContactUsResponse;
import com.gharbia.medical.DataModel.DayTimeResponse;
import com.gharbia.medical.DataModel.DepartmentsResponse;
import com.gharbia.medical.DataModel.EditProfileRequest;
import com.gharbia.medical.DataModel.FCMRequest;
import com.gharbia.medical.DataModel.HospitalsResponse;
import com.gharbia.medical.DataModel.ListOfApp;
import com.gharbia.medical.DataModel.LoginRequest;
import com.gharbia.medical.DataModel.LoginResponse;
import com.gharbia.medical.DataModel.NotiResponse;
import com.gharbia.medical.DataModel.QuestionsResponse;
import com.gharbia.medical.DataModel.RateItemRequest;
import com.gharbia.medical.DataModel.RegisterRequest;
import com.gharbia.medical.DataModel.RegisterResponse;
import com.gharbia.medical.DataModel.ResetPasswordRequest;
import com.gharbia.medical.DataModel.ReviewResponse;
import com.gharbia.medical.DataModel.SliderResponse;
import com.gharbia.medical.DataModel.VerificationRequest;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

public interface NetworkInterface {

    @POST("/api/Account/Register")
    Observable<RegisterResponse> Register(@Body RegisterRequest registerRequest);

    @POST("/api/Account/Verify")
    Observable<LoginResponse> Verify(@Body VerificationRequest registerRequest);

    @POST("/api/Account/Login")
    Observable<LoginResponse> Login(@Body LoginRequest loginRequest);

    @GET("/api/city")
    Observable<CitiesResponse> GetCities();

    @GET("/api/Slider")
    Observable<SliderResponse> GetSlider();

    @PUT("/api/account/ForgetPassword")
    Observable<LoginResponse> ForgetPassword(@Query("Phone") String phone);

    @PUT("/api/account/ChangePassword")
    Observable<LoginResponse> ResetPassword(@Body ResetPasswordRequest resetPasswordRequest);

    @GET("/api/QuestionAndAnswer")
    Observable<QuestionsResponse> GetQuestions(@Query("Lang") String lang);

    @GET("/api/Account/GetProfile")
    Observable<LoginResponse> GetProfile();

    @GET("/api/Blog")
    Observable<BlogResponse> GetBlog(@Query("PageIndex") int PageIndex, @Query("PageSize") int PageSize);

    @PUT("/api/Account/UpdateProfile")
    Observable<LoginResponse> EditProfile(@Body EditProfileRequest editProfileRequest);

    @GET("/api/MedicalDepartment/General")
    Observable<DepartmentsResponse> GetDepartments(@Query("Lang") String lang, @Query("Type") int type);

    @GET("/api/Clinic/Pagination")
    Observable<ClinicsList> GetClinics(@Query("Lang") String lang, @Query("PageIndex") int PageIndex, @Query("DepartmentId") int DepartmentId, @Query("CityId") int CityId, @Query("Sponser") boolean Sponser, @Query("PageSize") int PageSize);

    @GET("/api/doctor")
    Observable<ClinicsList> GetClinics2(@Query("Lang") String lang, @Query("DepartmentId") int DepartmentId,@Query("MedicalProfileId") int MedicalProfileId);

    @GET("/api/Hospital/Pagination")
    Observable<HospitalsResponse> GetHospitals(@Query("Lang") String lang, @Query("PageIndex") int PageIndex, @Query("CityId") int CityId, @Query("Sponser") boolean Sponser);

    @GET("api/AnalasisLab/Pagination")
    Observable<HospitalsResponse> GetAnalysis(@Query("Lang") String lang, @Query("PageIndex") int PageIndex, @Query("CityId") int CityId, @Query("Sponser") boolean Sponser);

    @GET("api/pharmacy/Pagination")
    Observable<HospitalsResponse> GetPharmacies(@Query("Lang") String lang, @Query("PageIndex") int PageIndex, @Query("CityId") int CityId, @Query("Sponser") boolean Sponser);

    @GET("api/XRay/Pagination")
    Observable<HospitalsResponse> GetXRay(@Query("Lang") String lang, @Query("PageIndex") int PageIndex, @Query("CityId") int CityId, @Query("Sponser") boolean Sponser);

    @GET("api/MedicalCenter/Pagination")
    Observable<HospitalsResponse> GetMedicalCenters(@Query("Lang") String lang, @Query("PageIndex") int PageIndex, @Query("CityId") int CityId, @Query("Sponser") boolean Sponser);

    @GET("/api/Clinic/Profile")
    Observable<ClinicProfileResponse> GetClinicsDetails(@Query("ClinicId") int ClinicId, @Query("Lang") String lang);

    @GET("api/Hospital/Profile")
    Observable<ClinicProfileResponse> GetHospitalDetails(@Query("ClinicId") int ClinicId, @Query("Lang") String lang);

    @GET("api/XRay/Profile")
    Observable<ClinicProfileResponse> GetXRayDetails(@Query("ClinicId") int ClinicId, @Query("Lang") String lang);

    @GET("/api/MedicalCenter/Profile")
    Observable<ClinicProfileResponse> GetMedicalCenterDetails(@Query("ClinicId") int ClinicId, @Query("Lang") String lang);

    @GET("/api/AnalasisLab/Profile")
    Observable<ClinicProfileResponse> GetAnalysisDetails(@Query("ClinicId") int ClinicId, @Query("Lang") String lang);

    @GET("/api/Pharmacy/Profile")
    Observable<ClinicProfileResponse> GetPharmacyDetails(@Query("ClinicId") int ClinicId, @Query("Lang") String lang);

    @GET("api/Review/")
    Observable<ReviewResponse> GetReviews(@Query("MedicalProfileId") int ClinicId);

    @GET("api/Booking/AvailableDate")
    Observable<DayTimeResponse> GetDayTime(@Query("BranchId") int BranchId);

    @GET("api/Order/current/")
    Observable<ListOfApp> GetCurrentAppointments(@Query("Lang") String Lang);

    @GET("api/Order/previous/")
    Observable<ListOfApp> GetPreviousAppointments(@Query("Lang") String Lang);

    @GET("/api/ContactUs")
    Observable<ContactUsResponse> GetContactUs();

    @GET("/api/PushTokens/get")
    Observable<NotiResponse> GetNotifications();

    @GET("/api/Order/Details")
    Observable<BookDetailsResponse> GetBookDetails(@Query("BookId") int BookId, @Query("lang") String lang);

    @GET("/api/ContactUsSlider")
    Observable<SliderResponse> GetContactUsSlider();

    @POST("api/Booking/Book")
    Observable<ContactUsResponse> BookNow(@Body BookNowRequest bookNowRequest);

    @POST("api/Review")
    Observable<ContactUsResponse> RateItem(@Body RateItemRequest rateItemRequest);

    @GET("/api/Booking/cancel")
    Observable<SliderResponse> CancelOrder(@Query("BookId") int BookId);

    @GET("/api/Clinic/Search")
    Observable<ClinicsList> Search(@Query("Lang") String Lang,@Query("PageIndex") int PageIndex,@Query("DepartmentId") int DepartmentId,
                                   @Query("CityId") int CityId,@Query("name") String name,@Query("PageSize") int PageSize,
                                   @Query("Sort") int Sort,@Query("Rate") int Rate,@Query("Degree") int Degree);

    @GET("/api/Clinic/Search")
    Observable<ClinicsList> Search1(@Query("Lang") String Lang,@Query("PageIndex") int PageIndex,@Query("DepartmentId") int DepartmentId,
                                   @Query("CityId") int CityId,@Query("PageSize") int PageSize,
                                   @Query("Sort") int Sort,@Query("Rate") int Rate,@Query("Degree") int Degree);

    @POST("/api/PushTokens/Add")
    Observable<SliderResponse> UpdateFCM(@Body FCMRequest fcmRequest);

    /*@GET("Category/Hotels")
    Observable<CategoryItemsResponse> GetHotels(@Query("page") int page);*/
}
