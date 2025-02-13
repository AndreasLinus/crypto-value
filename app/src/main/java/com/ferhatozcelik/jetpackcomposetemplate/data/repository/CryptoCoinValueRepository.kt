package com.ferhatozcelik.jetpackcomposetemplate.data.repository

import com.ferhatozcelik.jetpackcomposetemplate.data.remote.CryptoCoinValueApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class CryptoCoinValueRepository @Inject constructor(val cryptoCoinApi: CryptoCoinValueApi)