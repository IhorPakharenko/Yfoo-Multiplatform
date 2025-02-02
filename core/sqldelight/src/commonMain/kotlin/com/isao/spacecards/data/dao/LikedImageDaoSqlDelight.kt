package com.isao.spacecards.data.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.isao.spacecards.core.db.dao.LikedImageDao
import com.isao.spacecards.core.db.model.LikedImageCached
import com.isao.spacecards.data.LikedImageCachedQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

// TODO consider mapping autogenerated data classes to domain models
class LikedImageDaoSqlDelight(private val queries: LikedImageCachedQueries) : LikedImageDao {
  override fun getLikedImages(): Flow<List<LikedImageCached>> = queries
    .selectAllLikedImages(::mapToData)
    .asFlow()
    .mapToList(Dispatchers.IO)

  override fun getLikedImages(
    shouldSortAscending: Boolean,
    limit: Int,
    offset: Int,
  ): Flow<List<LikedImageCached>> {
    val sortAscending = if (shouldSortAscending) 1L else 0L
    return queries
      .selectLikedImages(
        sortAscending,
        limit.toLong(),
        offset.toLong(),
        ::mapToData,
      ).asFlow()
      .mapToList(Dispatchers.IO)
  }

  override suspend fun saveLikedImage(item: LikedImageCached) {
    withContext(Dispatchers.IO) {
      queries.upsertLikedImage(
        id = item.id,
        imageId = item.imageId,
        source = item.source,
        dateAdded = item.dateAdded.toEpochMilliseconds(),
      )
    }
  }

  override suspend fun deleteLikedImage(id: String) {
    withContext(Dispatchers.IO) {
      queries.deleteLikedImage(id)
    }
  }

  private fun mapToData(
    id: String,
    imageId: String,
    source: String,
    dateAdded: Long,
  ) = LikedImageCached(
    id = id,
    imageId = imageId,
    source = source,
    dateAdded = Instant.fromEpochMilliseconds(dateAdded),
  )
}
