package com.psutools.reminder.utils.ui

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

object BitmapUtils {

    fun convertBitmapToByteArray(bitmap: Bitmap, compressionQuality: Int = 0): ByteArray {
        return ByteArrayOutputStream().use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, it)
            it.toByteArray()
        }
    }

    fun getBitmapFromUri(uri: Uri, contentResolver: ContentResolver): Bitmap {
        return if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images.Media.getBitmap(contentResolver, uri)
        } else {
            val source: ImageDecoder.Source = ImageDecoder.createSource(contentResolver, uri)
            ImageDecoder.decodeBitmap(source)
        }
    }
}
