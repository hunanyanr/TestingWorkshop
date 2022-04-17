package com.picsart.business.laws

import com.picsart.business.arch.mapping.Bijection
import io.kotest.property.Arb
import io.kotest.property.PropertyContext
import io.kotest.property.checkAll

object BijectionLaws {

    fun <F, P> laws(
        B: Bijection<F, P>,
        GEN: Arb<F>,
        eq: (F, F) -> Boolean = { a, b -> a == b }
    ): List<Law> =
        listOf(
            Law("Bijection Laws: Reciprocity") { B.bijectionReciprocity(GEN, eq) }
        )

    private suspend fun <F, P> Bijection<F, P>.bijectionReciprocity(
        GEN: Arb<F>,
        eq: (F, F) -> Boolean
    ): PropertyContext =
        checkAll(GEN) { a ->
            revert(map(a)).equalUnderTheLaw(a, eq)
        }
}