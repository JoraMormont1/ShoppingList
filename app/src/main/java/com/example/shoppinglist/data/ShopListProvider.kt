package com.example.shoppinglist.data

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.content.UriMatcher.NO_MATCH
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.shoppinglist.presentation.ShopListApplication
import javax.inject.Inject

class ShopListProvider: ContentProvider() {

    private val uriMatcher = UriMatcher(NO_MATCH).apply {
        addURI("com.example.shoppinglist", "shop_items", GET_SHOP_ITEMS_QUERY)
        addURI("com.example.shoppinglist","shop_items/#" , GET_SHOP_ITEM_BY_ID_QUERY)
    }

    private val component by lazy{
        (context as ShopListApplication).component
    }

    @Inject
    lateinit var shopListDao: ShopListDao

    override fun onCreate(): Boolean {
        component.inject(this)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val code = uriMatcher.match(uri)
        when(code){
            GET_SHOP_ITEMS_QUERY ->{
                return shopListDao.getShopListCursor()
            }
            else -> {
                return null
            }
        }
        Log.d("ShopListProvider", "query $uri code $code")

    }

    override fun getType(p0: Uri): String? {
        TODO("Not yet implemented")
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        TODO("Not yet implemented")
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    companion object{
        private const val GET_SHOP_ITEMS_QUERY = 100
        private const val GET_SHOP_ITEM_BY_ID_QUERY = 101
    }
}