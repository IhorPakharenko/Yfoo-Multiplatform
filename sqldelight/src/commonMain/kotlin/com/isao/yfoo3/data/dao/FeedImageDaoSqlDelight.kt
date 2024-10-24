package com.isao.yfoo3.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.isao.yfoo3.data.FeedImageCachedQueries
import com.isao.yfoo3.data.model.FeedImageCached
import com.isao.yfoo3.data.model.ImageSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FeedImageDaoSqlDelight(
    private val queries: FeedImageCachedQueries,
) : FeedImageDao {

    override fun getFeedImages(): Flow<List<FeedImageCached>> {
        return queries.selectAllFeedImages(::mapToData)
            .asFlow()
            .mapToList(Dispatchers.IO)
    }

    override fun getFeedImage(id: String): Flow<FeedImageCached> {
        return queries.selectFeedImage(id, mapper = ::mapToData)
            .asFlow()
            .mapToOne(Dispatchers.IO)
    }

    override suspend fun saveFeedImage(item: FeedImageCached): Long {
        return withContext(Dispatchers.IO) {
            queries.upsertFeedImage(item.id, item.imageId, item.source.name)
            1L // Returning 1L to indicate success, like Room does
        }
    }

    override suspend fun deleteFeedImage(id: String) {
        withContext(Dispatchers.IO) {
            queries.deleteFeedImage(id)
        }
    }

    private fun mapToData(
        id: String,
        imageId: String,
        source: String
    ) = FeedImageCached(
        id,
        imageId,
        ImageSource.valueOf(source)
    )
}
