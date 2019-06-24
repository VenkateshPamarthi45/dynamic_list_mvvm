
package com.venkatesh.dynamiclistdemo.items.repository
import com.venkatesh.dynamiclistdemo.common.LiveDataResource
import com.venkatesh.dynamiclistdemo.items.repository.model.Item

interface ItemRepository {
    fun getItems(pageId: String, closure: (liveDataResource: LiveDataResource<List<Item>>) -> Unit)
}