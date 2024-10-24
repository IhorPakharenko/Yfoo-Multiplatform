package com.isao.yfoo3.data.local.mapper

import com.isao.yfoo3.data.model.FeedImageCached
import com.isao.yfoo3.domain.model.FeedImage
import com.isao.yfoo3.domain.model.LikedImage
import kotlinx.datetime.Clock

fun FeedImageCached.toDomainModel() = FeedImage(
    id = id,
    imageId = imageId,
    source = source
)

fun FeedImage.toEntityModel() = FeedImageCached(
    id = id,
    imageId = imageId,
    source = source,
)

fun FeedImage.toLikedImage() = LikedImage(
    id = id,
    imageId = imageId,
    source = source,
    dateAdded = Clock.System.now()
)