package com.takseha.data.exception

import android.database.sqlite.SQLiteConstraintException
import android.database.sqlite.SQLiteException
import com.google.gson.JsonParseException
import com.google.gson.JsonSyntaxException
import com.takseha.domain.model.Failure
import retrofit2.HttpException
import java.io.PrintWriter
import java.io.StringWriter
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class ExceptionToFailureMapper {
    companion object {
        fun map(e: Throwable): Failure {
            /*
            에러 메세지 Ex)
            ErrorType: 서버 연결 실패
            ErrorUrl: https://gb-on.co.kr/kiosk/privacy
            Exception: e의 fullStackTrace
             */
            var errorDesc = ""
            val errorUrl = if (e is HttpException) {
                e.response()?.raw()?.request()?.url()?.toString() ?: ""
            } else {
                ""
            }
            val exceptionMsg = getFullStackTrace(e)

            return when (e) {
                is ConnectException, is SocketTimeoutException, is HttpException -> {
                    errorDesc = "서버 관련 오류"
                    Failure.ServerFailure(getErrorMessage(errorDesc, errorUrl, exceptionMsg), e)
                }

                is TimeoutException, is UnknownHostException -> {
                    errorDesc = "인터넷 연결 실패"
                    Failure.NetworkFailure(getErrorMessage(errorDesc, errorUrl, exceptionMsg), e)
                }

                is JsonSyntaxException, is JsonParseException, is SQLiteConstraintException, is SQLiteException -> {
                    errorDesc = "DB 오류"
                    Failure.DatabaseFailure(getErrorMessage(errorDesc, errorUrl, exceptionMsg), e)
                }

                else -> {
                    errorDesc = "기타 Data Layer 오류"
                    Failure.UnexpectedDataFailure(getErrorMessage(errorDesc, errorUrl, exceptionMsg), e)
                }
            }
        }

        private fun getErrorMessage(
            errorDesc: String,
            errorUrl: String,
            exceptionMsg: String
        ): String {
            return """
            ErrorType: $errorDesc
            ErrorUrl: $errorUrl
            Exception: $exceptionMsg
        """.trimIndent()
        }

        private fun getFullStackTrace(exception: Throwable): String {
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            exception.printStackTrace(pw)

            return sw.toString()
        }
    }
}
