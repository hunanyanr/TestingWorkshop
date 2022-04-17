/*
 * (c) 2021 Picsart, Inc.  All rights reserved.
 */

package com.m2f.testingworkshop

import android.annotation.SuppressLint
import io.kotest.core.config.AbstractProjectConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain

@SuppressLint("RestrictedApi")
/**
 * This class, by extending [AbstractProjectConfig] it's automatically injected and ran into the
 * test environment.
 * This configuration sets:
 * - The Main dispatcher as a [TestCoroutineDispatcher] for all the unit tests.
 * @author Marc MF
 */
object ProjectConfiguration : AbstractProjectConfig() {

    override suspend fun beforeProject() {
        super.beforeProject()
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    override suspend fun afterProject() {
        super.afterProject()
        Dispatchers.resetMain()
    }
}
