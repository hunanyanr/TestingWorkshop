package com.picsart.business.arch.error

sealed class Failure

object ServerError: Failure()
object ConnectionError: Failure()
object DataNotFound: Failure()
object Empty: Failure()
object UnknownError: Failure()