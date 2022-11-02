package wizeline.crypto.currency.utils

import android.content.Context
import android.graphics.drawable.Drawable

fun getDrawable(context: Context, fileName: String): Drawable {
    val id: Int =context.resources.getIdentifier(fileName, "drawable", context.packageName)
    val drawable: Drawable = context.getResources().getDrawable(id, null)
    return drawable
}