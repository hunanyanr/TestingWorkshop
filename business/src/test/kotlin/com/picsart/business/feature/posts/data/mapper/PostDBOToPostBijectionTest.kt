package com.picsart.business.feature.posts.data.mapper

import com.picsart.business.laws.BijectionLaws
import com.picsart.business.laws.testLaws
import com.picsart.business.feature.posts.data.model.PostDBO
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string

class PostDBOToPostBijectionTest : FunSpec({

    val postDBOGenerator = arbitrary {
        val title = Arb.string().bind()
        val body = Arb.string().bind()
        val id = Arb.positiveInt().bind()
        val userId = Arb.positiveInt().bind()
        PostDBO(id, userId, title, body)
    }

    testLaws(
        BijectionLaws.laws(PostDBOToPostBijection, postDBOGenerator))

})
