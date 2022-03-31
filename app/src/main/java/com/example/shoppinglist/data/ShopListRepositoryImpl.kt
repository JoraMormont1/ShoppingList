package com.example.shoppinglist.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shoppinglist.domain.ShopItem
import com.example.shoppinglist.domain.ShopItem.Companion.UNDEFINED_ID
import com.example.shoppinglist.domain.ShopListRepository
import java.lang.RuntimeException
import kotlin.random.Random

class ShopListRepositoryImpl(
    application: Application
): ShopListRepository {

    private val shopListDao = AppDatabase.getInstance(application).shopListDao()
    val mapper = ShopListMapper()

    override fun getShopList(): LiveData<List<ShopItem>> = shopListDao.getShopList()

    override fun getShopItem(shopItemId: Int): ShopItem {
        return mapper.mapDbModelToEntity(shopListDao.getShopItem(shopItemId))
    }

    override fun editShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }

    override fun deleteShopItem(shopItem: ShopItem) {
        shopListDao.deleteShopItem(shopItem.id)
    }

    override fun addShopItem(shopItem: ShopItem) {
        shopListDao.addShopItem(mapper.mapEntityToDbModel(shopItem))
    }


}