package com.mvvmdaggerroomdb.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvvmdaggerroomdb.model.UserModel
import com.mvvmdaggerroomdb.repository.AppRepository
import com.mvvmdaggerroomdb.repository.DashboardRepository
import com.mvvmdaggerroomdb.util.Coroutines

class DashBoardViewModel(private val repository: AppRepository) : ViewModel() {

    var progressBarObserver: MutableLiveData<Boolean> = MutableLiveData()
    var userList: MutableLiveData<List<UserModel>> = MutableLiveData()

    fun getUserList(): LiveData<List<UserModel>> = userList

    fun getUser() {
        progressBarObserver.postValue(true)
        Coroutines.main {
            val userLists = repository.getAllUser()
            userList.postValue(userLists)
            progressBarObserver.postValue(false)
        }
    }

}