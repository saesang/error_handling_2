package com.takseha.presentation.exception

import android.hardware.camera2.CameraAccessException
import com.takseha.domain.model.Failure
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import java.nio.BufferOverflowException

class ExceptionToFailureMapper {
    companion object {
        fun map(e: Throwable): Failure {
            /*
            에러 메세지 Ex)
            ErrorType: UI 상태 에러
            Exception: e의 fullStackTrace
             */
            var errorDesc = ""
            val exceptionMsg = getFullStackTrace(e)

            return when (e) {
                is IllegalStateException, is NullPointerException, is IllegalArgumentException, is OutOfMemoryError -> {
                    errorDesc = "App Crash 발생"
                    Failure.AppCrashFailure(getErrorMessage(errorDesc, exceptionMsg), e)
                }

                is IndexOutOfBoundsException, is BufferOverflowException, is IOException -> {
                    errorDesc = "데이터 접근 실패"
                    Failure.UiErrorFailure(getErrorMessage(errorDesc, exceptionMsg), e)
                }

                is SecurityException, is CameraAccessException -> {
                    val alertMsg = "카메라 연결 에러"
                    Failure.UiWarningFailure(alertMsg) // log 저장 X
                }

                else -> {
                    errorDesc = "기타 Presentation layer 오류"
                    Failure.UnexpectedUiFailure(getErrorMessage(errorDesc, exceptionMsg), e)
                }
            }
        }

        private fun getErrorMessage(
            errorDesc: String,
            exceptionMsg: String
        ): String {
            return """
            ErrorType: $errorDesc
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
