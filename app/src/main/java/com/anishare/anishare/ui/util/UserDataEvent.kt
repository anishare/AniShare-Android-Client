package com.anishare.anishare.ui.util

import java.util.*

sealed class UserDataEvent {
    object GetListData: UserDataEvent()
    data class GetItem(val id: UUID): UserDataEvent()
}
