package com.ssafy.hifes.ui.group

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ssafy.hifes.data.model.Group
import com.ssafy.hifes.data.model.OrganizedFestivalDto

class GroupViewModel : ViewModel()  {
    private var _groupList : MutableLiveData<MutableList<Group>> = MutableLiveData()
    val groupList : LiveData<MutableList<Group>> = _groupList

    private var _selectedGroup: MutableLiveData<Group> = MutableLiveData()
    val selectedGroup: LiveData<Group> = _selectedGroup

    init {
        getGroupList()
    }

    fun getGroupList(){
        //추후 서버 통신 코드가 생기면 이 부분을 서버에게서 모임 리스트를 받아오는것으로 변경한다
        var groupListDummyData = mutableListOf<Group>()
        val tmp = Group(
            "https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68",
            "제목",
            "내용",
            hashtag = listOf("1", "2", "3", "4"),
            3,
            6
        )
        groupListDummyData.apply {
            add(tmp)
            add(tmp)
            add(tmp)
            add(tmp)
            add(tmp)
            add(tmp)
        }

        _groupList.postValue(groupListDummyData)
    }

    fun getGroupDetail(group: Group) {
        _selectedGroup.postValue(group)
    }
}