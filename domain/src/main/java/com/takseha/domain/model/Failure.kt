package com.takseha.domain.model

/**
Failure: e를 인자로 받아, 해당 e를 종류별로 구분
- ValidationFailure, UiWarningFailure, BusinessFailure는 Log 저장 X (중요하지 않은 에러)
 */
sealed class Failure(
    val msg: String,
    val e: Throwable? = null
) : Throwable(msg, e) {
    class NetworkFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
    class ServerFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
    class DatabaseFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
    class BusinessFailure(alertMsg: String) : Failure(msg = alertMsg)
    class ValidationFailure(alertMsg: String) : Failure(msg = alertMsg)
    class UiWarningFailure(alertMsg: String) : Failure(msg = alertMsg)
    class UiErrorFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
    class AppCrashFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
    class UnexpectedUiFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
    class UnexpectedDataFailure(logSaveMsg: String, e: Throwable?) : Failure(logSaveMsg, e)
}