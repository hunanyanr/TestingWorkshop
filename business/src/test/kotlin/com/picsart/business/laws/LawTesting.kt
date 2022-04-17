package com.picsart.business.laws

import io.kotest.core.TestConfiguration
import io.kotest.core.names.TestName
import io.kotest.core.spec.style.scopes.StringSpecScope
import io.kotest.core.test.TestCaseConfig
import io.kotest.core.test.TestType

context(TestConfiguration)
fun testLaws(vararg laws: List<Law>): Unit = laws
    .flatMap { list: List<Law> -> list.asIterable() }
    .distinctBy { law: Law -> law.name }
    .forEach { law: Law ->
        addTest(TestName(law.name), config = TestCaseConfig(), type = TestType.Test, test = {
            law.test(StringSpecScope(coroutineContext, testCase))
        })
    }