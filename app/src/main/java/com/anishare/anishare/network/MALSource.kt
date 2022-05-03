package com.anishare.anishare.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.anishare.anishare.domain.model.AnimeMALNode
import com.anishare.anishare.domain.repository.MALRepo

class MALSource(
    private val malRepo: MALRepo,
    private val query: String
): PagingSource<Int, AnimeMALNode>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeMALNode>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeMALNode> {
        return try {
            val nextPage = params.key ?: 0
            val searchList = malRepo.search(name = query, offset = nextPage)
            LoadResult.Page(
                data = searchList.data,
                prevKey = null,
                nextKey = if (searchList.paging.next != null && nextPage <= 40) nextPage + 10 else null
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}