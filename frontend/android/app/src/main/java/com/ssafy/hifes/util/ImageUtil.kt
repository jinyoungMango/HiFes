package com.ssafy.hifes.util

import android.content.Context
import android.net.Uri
import android.os.Environment
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.io.FileOutputStream
import java.util.Calendar
import java.util.UUID

object FileUtil {
    // 임시 파일 생성
    fun createTempFile(context: Context, fileName: String): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(storageDir, fileName)
    }

    // 파일 내용 스트림 복사
    fun copyToFile(context: Context, uri: Uri, file: File) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val outputStream = FileOutputStream(file)

        val buffer = ByteArray(4 * 1024)
        while (true) {
            val byteCount = inputStream!!.read(buffer)
            if (byteCount < 0) break
            outputStream.write(buffer, 0, byteCount)
        }

        outputStream.flush()
    }
}

object UriUtil {
    // URI -> File
    fun toFile(context: Context, uri: Uri): File {
        val fileName = getFileName(context, uri)

        val file = FileUtil.createTempFile(context, fileName)
        FileUtil.copyToFile(context, uri, file)

        return File(file.absolutePath)
    }

    // get file name & extension
    fun getFileName(context: Context, uri: Uri): String {
        //val name = uri.toString().split("/").last()
        val uuid = UUID.randomUUID()
        val cal = Calendar.getInstance()
        val name = cal.get(Calendar.YEAR).toString() +
                cal.get(Calendar.MONTH + 1).toString() +
                cal.get(Calendar.DATE).toString() +
                cal.get(Calendar.HOUR).toString() +
                cal.get(Calendar.MINUTE).toString() +
                cal.get(Calendar.SECOND).toString() +
                uuid.toString() +
                "mobile"
        val ext = context.contentResolver.getType(uri)!!.split("/").last()

        return "$name.$ext"
    }
}

object MultipartUtil {
    fun getImageBody(file: File): MultipartBody.Part {
        val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), file)
        return MultipartBody.Part.createFormData("image", file.name, requestFile)
    }
}