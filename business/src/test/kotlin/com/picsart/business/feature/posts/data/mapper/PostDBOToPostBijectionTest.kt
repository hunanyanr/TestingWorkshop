package com.picsart.business.feature.posts.data.mapper

import com.picsart.business.generators.dboPost
import com.picsart.business.laws.BijectionLaws
import com.picsart.business.laws.testLaws
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.Arb

class PostDBOToPostBijectionTest : FunSpec({

    testLaws(
        BijectionLaws.laws(PostDBOToPostBijection, Arb.dboPost())
    )

})
