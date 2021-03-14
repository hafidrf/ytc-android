package com.yureka.technology.ytc.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes

/**
 * Screens available for display in the main screen, with their respective titles,
 * icons, and menu item IDs and fragments.
 */
enum class Screen(
    @IdRes val menuItemId: Int,
    @DrawableRes val menuItemIconId: Int,
    @StringRes val titleStringId: Int
) {
    BERANDA(
        com.yureka.technology.ytc.R.id.navigation_home,
        com.yureka.technology.ytc.R.drawable.ic_home_black_24dp,
        com.yureka.technology.ytc.R.string.title_home
    ),
    PERINGKAT(
        com.yureka.technology.ytc.R.id.navigation_dashboard,
        com.yureka.technology.ytc.R.drawable.ic_dashboard_black_24dp,
        com.yureka.technology.ytc.R.string.title_dashboard
    ),
    AVATAR(
        com.yureka.technology.ytc.R.id.navigation_notifications,
        com.yureka.technology.ytc.R.drawable.ic_notifications_black_24dp,
        com.yureka.technology.ytc.R.string.title_notifications
    ),
    PROFIL(
        com.yureka.technology.ytc.R.id.navigation_profil,
        com.yureka.technology.ytc.R.drawable.ic_home_black_24dp,
        com.yureka.technology.ytc.R.string.title_profil
    )
}

fun getMainScreenForMenuItem(menuItemId: Int): Screen? {
    for (mainScreen in Screen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}
