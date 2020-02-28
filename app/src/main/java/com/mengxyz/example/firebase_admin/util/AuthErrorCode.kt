package com.mengxyz.example.firebase_admin.util

class AuthErrorCode {
    companion object{
        private val errorCode = mapOf(
            "ERROR_USER_DISABLED" to "บัญชีผู้ใช้ถูกปิดใช้งานโดยผู้ดูแลระบบ",
            "ERROR_INVALID_EMAIL" to "รูปแบบอีเมลไม่ถูกต้อง",
            "ERROR_INVALID_PASSWORD" to "รหัสผ่านไม่ถูกต้องหรือผู้ใช้ไม่มีรหัสผ่าน",
            "ERROR_WRONG_PASSWORD" to "รหัสผ่านไม่ถูกต้อง"
        )

        fun getErrorCode(code:String): String = errorCode[code] ?: code
    }
}
//INVALID_PASSWORD: The password is invalid or the user does not have a password.
//INVALID_EMAIL: The email address is badly formatted.
//TOKEN_EXPIRED: The user's credential is no longer valid. The user must sign in again.
//USER_DISABLED: The user account has been disabled by an administrator.
//USER_NOT_FOUND: The user corresponding to the refresh token was not found. It is likely the user was deleted.
//API key not valid. Please pass a valid API key. (invalid API key provided)
//INVALID_REFRESH_TOKEN: An invalid refresh token is provided.
//Invalid JSON payload received. Unknown name \"refresh_tokens\": Cannot bind query parameter. Field 'refresh_tokens' could not be found in request message.
//INVALID_GRANT_TYPE: the grant type specified is invalid.
//MISSING_REFRESH_TOKEN: no refresh token provided.