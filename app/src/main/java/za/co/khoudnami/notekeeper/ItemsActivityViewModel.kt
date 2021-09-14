package za.co.khoudnami.notekeeper

import androidx.lifecycle.ViewModel

class ItemsActivityViewModel : ViewModel() {
    //default selection when activity is first created is nav_notes
    var navDrawerDisplaySelection = R.id.nav_notes
}