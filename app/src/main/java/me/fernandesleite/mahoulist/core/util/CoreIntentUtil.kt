package me.fernandesleite.mahoulist.core.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import me.fernandesleite.mahoulist.R

class CoreIntentUtil {
    companion object {
        fun openBrowser(context: Context, url: String) {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                context.startActivity(intent)
            } catch (exception: ActivityNotFoundException) {
                Toast.makeText(context, context.getString(R.string.no_browser_was_found), Toast.LENGTH_SHORT).show()
            }
        }
    }
}