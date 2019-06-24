package com.venkatesh.dynamiclistdemo.viewmodel


import com.venkatesh.dynamiclistdemo.common.LiveDataResource
import com.venkatesh.dynamiclistdemo.items.repository.ItemRepository
import com.venkatesh.dynamiclistdemo.items.repository.model.Item

class MockItemRepository: ItemRepository {

    var isGetItemsMethodIsCalled = false
    override fun getItems(pageId: String, closure: (liveDataResource: LiveDataResource<List<Item>>) -> Unit) {
        isGetItemsMethodIsCalled = true
        if(pageId == "sampleUrl1"){
            closure(LiveDataResource.error(404, "Invalid user", null, null))
        }
    }

}
