package com.venkatesh.dynamiclistdemo.viewmodel

import com.venkatesh.dynamiclistdemo.items.viewmodel.ItemViewModel

import com.venkatesh.dynamiclistdemo.viewmodel.MockItemRepository

import org.junit.Assert
import org.junit.Test

class ItemViewModelTest {
    private val mockItemRepository = MockItemRepository()
    private var sut = ItemViewModel(mockItemRepository)

    @Test
    fun testCheckItemRepositoryGetMethodIsCalledWhenGetItemsInViewModelIsCalled(){
        val items = sut.getItems("sampleUrl")
        Assert.assertTrue(mockItemRepository.isGetItemsMethodIsCalled)
    }
}