package com.mengxyz.example.firebase_admin.db.repository

import android.util.Log
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.coroutines.awaitObjectResponse
import com.github.kittinunf.fuel.coroutines.awaitStringResponse
import com.github.kittinunf.fuel.gson.gsonDeserializerOf
import com.github.kittinunf.fuel.gson.jsonBody
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.mengxyz.example.firebase_admin.db.response.User

val TAG = AdminRepository::class.java.simpleName

class AdminRepository {

    suspend fun getAllUser(): Array<User> {
        val (_, _, result) = "/user"
                .httpGet()
                .awaitObjectResponse(gsonDeserializerOf(Array<User>::class.java))
        Log.e(TAG, "getAllUser: ${result.size}")
        return result
    }

    suspend fun createUser(email: String, password: String, token: String): String {
        val userObject: RequestUserObject = RequestUserObject(
                email = email,
                password = password,
                token = token
        )
        Log.e(TAG, "UserJson: $userObject")
        return try {
            val (_, _, result) = "/user"
                    .httpPost()
                    .jsonBody(userObject)
                    .awaitStringResponse()
            result
        } catch (e: FuelError) {
            Log.e(TAG, "OnError: ${e.response.body().asString("application/json")}")
            e.json()
        }
    }

    suspend fun createAnonymous(): String {
        return try {
            val userObject = RequestUserObject()
            val (_, _, result) = "/user/anonymous"
                    .httpPost()
                    .jsonBody(userObject)
                    .awaitStringResponse()
            result
        } catch (e: FuelError) {
            e.json()
        }
    }

    suspend fun updateUser(uid: String): String {
        return try {
            val(_,_,result) = "/user/$uid"
                    .httpPut()
                    .jsonBody(RequestUserObject(
                            email = "hello@123.com",
                            password = "111111"
                    ))
                    .awaitStringResponse()
            result
        }catch (e:FuelError){
            e.json()
        }
    }

    private data class RequestUserObject(
            val email: String? = null,
            val password: String? = null,
            val token: String? = null,
            val name: String = "Anantasak2",
            val status: String = "user"
    )

}

private fun FuelError.json(): String {
    return try {
        response.body().asString("application/json")
    } catch (e: Error) {
        response.body().asString("text/plain; charset=UTF-8")
    }
}


