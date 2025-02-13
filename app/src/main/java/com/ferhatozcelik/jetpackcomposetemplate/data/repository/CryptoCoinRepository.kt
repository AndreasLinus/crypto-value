package com.ferhatozcelik.jetpackcomposetemplate.data.repository

import com.ferhatozcelik.jetpackcomposetemplate.data.dao.ExampleDao
import com.ferhatozcelik.jetpackcomposetemplate.data.remote.AppApi
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
open class CryptoCoinRepository @Inject constructor(val cryptoCoinApi: AppApi, val exampleDao: ExampleDao)