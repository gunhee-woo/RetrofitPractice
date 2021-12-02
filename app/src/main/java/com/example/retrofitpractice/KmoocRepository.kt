package com.example.retrofitpractice

class KmoocRepository {

    private val serviceKey: String = "LwG%2BoHC0C5JRfLyvNtKkR94KYuT2QYNXOT5ONKk65iVxzMXLHF7SMWcuDqKMnT%2BfSMP61nqqh6Nj7cloXRQXLA%3D%3D"
    private val mobile: Int = 1

    suspend fun getRepository() = KmoocService.client?.getCourseList(serviceKey, mobile)
}