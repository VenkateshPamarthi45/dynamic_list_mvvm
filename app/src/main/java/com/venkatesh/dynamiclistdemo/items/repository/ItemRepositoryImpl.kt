
package com.venkatesh.dynamiclistdemo.items.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.venkatesh.dynamiclistdemo.common.LiveDataResource
import com.venkatesh.dynamiclistdemo.items.repository.ItemRepository
import com.venkatesh.dynamiclistdemo.items.repository.model.Item
import com.venkatesh.networklibrary.model.ModelManager

/**
 *
 * @property modelManager ModelManager
 * @constructor
 */
class ItemRepositoryImpl(var modelManager: ModelManager): ItemRepository {

    /**
     *
     * @param pageId String
     * @param closure Function4<[@kotlin.ParameterName] Response<ResponseBody>?, [@kotlin.ParameterName] List<Item>?, [@kotlin.ParameterName] String?, [@kotlin.ParameterName] Deferred<Response<ResponseBody>>?, Unit>
     */
    override fun getItems(pageId: String, closure: (liveDataSource: LiveDataResource<List<Item>>) -> Unit) {


        modelManager.getRequest(pageId){ response, responseBodyString,  call ->
            if(responseBodyString == null && response == null && call != null){
                println(" call is not null")
                closure(LiveDataResource.loading(null, call))
            } else if(responseBodyString != null){
                val token = object : TypeToken<List<Item>>() {}
                var items: List<Item> = Gson().fromJson(responseBodyString, token.type)
                val statusCode = response?.code() ?: 0
                closure(LiveDataResource.success(statusCode,items, call))
            }else if(response != null){
                println("responseBodyString is null")
                closure(LiveDataResource.error(response.code(), response.message(), null, call))
            }else{
                println(" response is null")
                closure(LiveDataResource.error(500, "Internal Error", null, call))
            }
        }
    }
}