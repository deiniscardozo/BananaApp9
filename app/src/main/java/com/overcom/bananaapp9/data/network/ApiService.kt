package com.overcom.bananaapp9.data.network

import com.overcom.bananaapp9.data.model.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("api/login")
    suspend fun listUsers(@Field("email") email: String,
                          @Field("password") password: String) : Response<UserDataItem>

    @GET("api/organizations/byUser")
    suspend fun listOrganizations(): Response<OrganizationsData>

   /* @GET("api/menu/options")
    fun menu() : Call<MenuData>*/

    @GET("api/thirds")
    suspend fun listThirds(@Query("type_third") type_third: String,
                   @Query("position") position: String,
                   @Query("limit") limit: String,
                   @Query("filter") filter: String) : Response<ThirdsDataItem>

    @GET("api/third/{id}")
    suspend fun listThirdsDetail(@Path("id") id: String,
                         @Query("resources") resources: String) : Response<ThirdsDataDetailItem>

    @GET("api/users/reset/password/{email}")
    suspend fun ForgetPassword(@Path("email") email: String) : Response<ForgotPassword>

    @POST("api/signin/searchDnsAvailable")
    suspend fun validateWorkspace(@Body workspace: Workspace) : Response<ValidateWorkspace>

    @DELETE("api/logout")
    suspend fun logout() : Response<Logout>

}



