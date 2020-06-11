package ilya.myasoedov.ocs.providers

import android.app.ActivityManager
import android.content.Context
import android.widget.ImageView
import com.squareup.picasso.LruCache
import com.squareup.picasso.Picasso
import ilya.myasoedov.ocs.R

private const val defaultMemoryCachePercent = 20

class ImageLoadProvider(
    private val context: Context
) {

    /**
     * should be called in the app class
     */
    fun setup() {
        val builder = Picasso.Builder(context)
            .memoryCache(LruCache(getBytesForMemCache(defaultMemoryCachePercent)))
        Picasso.setSingletonInstance(builder.build())
    }

    fun load(
        url: String?,
        imageView: ImageView
    ) {
        if (url != null) {
            Picasso.get()
                .load(url)
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(imageView)
        } else {
            Picasso.get()
                .load(R.drawable.ic_no_image)
                .placeholder(R.drawable.ic_no_image)
                .into(imageView)
        }
    }

    @Suppress("SameParameterValue")
    private fun getBytesForMemCache(percent: Int): Int {
        val memoryInfo = ActivityManager.MemoryInfo()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        activityManager.getMemoryInfo(memoryInfo)
        return (percent * memoryInfo.availMem.toDouble() / 100).toInt()
    }
}