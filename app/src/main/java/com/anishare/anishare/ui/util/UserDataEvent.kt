package com.anishare.anishare.ui.util

import com.anishare.anishare.domain.model.UserData
import java.util.*

sealed class UserDataEvent {
    object GetListData: UserDataEvent()
    data class GetItem(val id: UUID): UserDataEvent()
    data class AddItem(val userData: UserData): UserDataEvent()
}
