package com.mengxyz.example.firebase_admin.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mengxyz.example.firebase_admin.db.response.BasicResponse
import java.lang.reflect.Type

class FirebaseResponse {
    companion object {

        private val error = mapOf(
            "auth/email-already-exists" to "ผิดพลาด อีเมลซ้ำ",
            "auth/user-created" to "เพิ่มผู้ใช้สำเร็จ",
            "auth/You not have permission" to "ผิดพลาด คุณไม่มีสิทธิ์เข้าถึง"
        )

        fun getValue(res: String): String? =
            getMapValue(
                res
            )

        private fun getMapValue(res: String): String? {
            val type: Type = object : TypeToken<BasicResponse>() {}.type
            val resObj = Gson().fromJson<BasicResponse>(res, type)
            return  if(error[resObj.code] != null) error[resObj.code] else resObj.code
        }
    }
}