package com.example.marsphotos.fake

import com.example.marsphotos.data.NetworkMarsPhotoRepository
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class NetworkMarsPhotosRepositoryTest {
    @Test
    fun networkMarsPhotosRepository_getMarsPhotos_verifyPhotosList() = runTest {
//        val repository = NetworkMarsPhotoRepository(
//            marsApiService = FakeMarsApiService()
//        )
//        assertEquals(FakeDataSource.photosList, repository.getMarsPhotos())
    }
}