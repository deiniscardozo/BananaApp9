package com.overcom.bananaapp9.data.dataacces

import android.content.Context

class Preferences (val context : Context){

    val SHARED_WORKSPACE = "workspace"
    val SHARED_EMAIL = "email"
    val SHARED_TOKEN = "token"
    val SHARED_USERID = "userId"
    val SHARED_USERNAME = "userName"
    val SHARED_USERIMAGE = "userImage"
    val SHARED_CLIENT = "client"
    val SHARED_ORGID = "orgId"
    val SHARED_ORGTEXT = "orgText"
    val SHARED_THIRDSID = "thirdsId"
    val SHARED_THIRDSTYPE = "thirdsType"
    val SHARED_FIRSTRUN = "firstRun"
    val SHARED_POSITION = "position"

    val storage = context.getSharedPreferences("PREFERENCE_NAME", 0)

    fun saveWorkspace(workspace:String){
        storage.edit().putString(SHARED_WORKSPACE, workspace).apply()
    }

    fun saveEmail(email:String){
        storage.edit().putString(SHARED_EMAIL, email).apply()
    }

    fun saveToken(token:String){
        storage.edit().putString(SHARED_TOKEN, token).apply()
    }

    fun saveUserID(userId:String){
        storage.edit().putString(SHARED_USERID, userId).apply()
    }

    fun saveUserName(userName:String){
        storage.edit().putString(SHARED_USERNAME, userName).apply()
    }

    fun saveUserImage(userImage:String){
        storage.edit().putString(SHARED_USERIMAGE, userImage).apply()
    }

    fun saveClient(client:String){
        storage.edit().putString(SHARED_CLIENT, client).apply()
    }

    fun saveOrgId(orgId: String){
        storage.edit().putString(SHARED_ORGID, orgId).apply()
    }

    fun saveOrgText(orgText:String){
        storage.edit().putString(SHARED_ORGTEXT, orgText).apply()
    }

    fun saveThirdsID(thirdsId: String){
        storage.edit().putString(SHARED_THIRDSID, thirdsId).apply()
    }

    fun saveThirdsType(thirdsType: String){
        storage.edit().putString(SHARED_THIRDSTYPE, thirdsType).apply()
    }

    fun saveFirstRun(firstRun: Boolean){
        storage.edit().putBoolean(SHARED_FIRSTRUN, firstRun).apply()
    }

    fun savePosition(position: Int){
        storage.edit().putInt(SHARED_POSITION, position).apply()
    }

    fun getWorkspace():String{
        return storage.getString(SHARED_WORKSPACE, "")!!
    }

    fun getEmail():String{
        return storage.getString(SHARED_EMAIL, "")!!
    }

    fun getToken():String{
        return storage.getString(SHARED_TOKEN, "")!!
    }

    fun getUserID():String{
        return storage.getString(SHARED_USERID, "")!!
    }

    fun getUserName():String{
        return storage.getString(SHARED_USERNAME, "")!!
    }

    fun getUserImage():String{
        return storage.getString(SHARED_USERIMAGE, "")!!
    }

    fun getClient():String{
        return storage.getString(SHARED_CLIENT, "")!!
    }

    fun getOrgID():String{
        return storage.getString(SHARED_ORGID, "")!!
    }

    fun getOrgText():String{
        return storage.getString(SHARED_ORGTEXT, "")!!
    }

    fun getThirdsID():String{
        return storage.getString(SHARED_THIRDSID, "")!!
    }

    fun getThirdsType():String{
        return storage.getString(SHARED_THIRDSTYPE, "customer_prospect")!!
    }

    fun getFirstRun():Boolean{
        return storage.getBoolean(SHARED_FIRSTRUN, false)
    }

    fun getPosition():Int {
        return storage.getInt(SHARED_POSITION, 0)
    }

    fun logout(){
        storage.edit().clear()
    }
}