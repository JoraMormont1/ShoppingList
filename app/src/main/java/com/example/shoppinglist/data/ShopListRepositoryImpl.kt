package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopItem.Companion.UNDEFINED_ID
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException
import javax.inject.Inject
import kotlin.random.Random

class ShopListRepositoryImpl  @Inject constructor(
    private val shopListDao: ShopListDao,
    private val mapper: ShopListMapper
): ShopListRepository {



    override fun getShopList(): LiveData<List<ShopItem>> = Transformations.map(
        shopListDao.getShopList()
    ){
        mapper.mapListDbModelToListEntity(it)
    }

    override suspend fun getShopItem(shopItemId: Int): ShopItem {
        return mapper.mapDbModelToEntity(shopListDao.getShopItem(shopItemId))
    }

    override suspend fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override suspend fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override suspend fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }


}