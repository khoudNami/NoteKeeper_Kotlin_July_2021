package za.co.khoudnami.notekeeper

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class NoteGetTogetherHelper(val context: Context, val lifecycle: Lifecycle) : LifecycleObserver {
    init {
        lifecycle.addObserver(this)
    }

    private val tag = this::class.simpleName
    var currentLat = 0.0
    var currentLon = 0.0


    val locManager = PseudoLocationManager(context) { lat, lon ->
        currentLat = lat
        currentLon = lon
        Log.d(tag, "Location Callback Lat: $currentLat Lon: $currentLon")
    }

    val msgManager = PseudoMessagingManager(context)
    var msgConnection: PseudoMessagingConnection? = null

    fun sendMessage(note: NoteInfo) {
        val getTogetherMessage = "$currentLat | $currentLon | ${note.title} | ${note.course?.title}"
        msgConnection?.send(getTogetherMessage)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun startHandler() {
        Log.d(tag, "startHandler")
        locManager.start()
        msgManager.connect { conection ->
            Log.d(tag, "Connection callback - Lifecycle state: ${lifecycle.currentState}")
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                msgConnection = conection
            else
                msgConnection?.disconnect()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun stopHandler() {
        Log.d(tag, "stopHandler")
        locManager.stop()
        msgConnection?.disconnect()
    }


}