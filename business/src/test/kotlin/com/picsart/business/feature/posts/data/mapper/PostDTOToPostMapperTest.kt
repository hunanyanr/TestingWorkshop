package com.picsart.business.feature.posts.data.mapper

import com.picsart.business.arch.mapping.Mapper
import com.picsart.business.arch.mapping.biject
import com.picsart.business.feature.posts.data.model.PostDTO
import com.picsart.business.feature.posts.model.Post
import com.picsart.business.laws.BijectionLaws
import com.picsart.business.laws.testLaws
import io.kotest.core.spec.style.FunSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.arbitrary
import io.kotest.property.arbitrary.positiveInt
import io.kotest.property.arbitrary.string

class PostDTOToPostMapperTest : FunSpec({

    val postDTO = arbitrary {
        val title = Arb.string().bind()
        val body = Arb.string().bind()
        val id = Arb.positiveInt().bind()
        val userId = Arb.positiveInt().bind()
        PostDTO(id, userId, title, body)
    }

    //if we create the transformation back to the original
    val postToDto = Mapper<Post, PostDTO> { with(it) { PostDTO(id, userId, title, body) } }

    //we can biject the mapper
    val postDtoBijection = PostDTOToPostMapper.biject(postToDto)

    //then we can run the bijection laws and test for consistent transformation
    testLaws(
        BijectionLaws.laws(postDtoBijection, postDTO)
    )
})

