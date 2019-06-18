package com.empty.jinux.baselibaray.utils

import com.empty.jinux.baselibaray.log.logd
import com.empty.jinux.baselibaray.log.loge
import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream
import java.util.zip.ZipOutputStream

fun ensureDirectoryExist(dir: File): File {
    if (dir.exists() && dir.isDirectory) {
        return dir
    }

    if (!dir.exists()) {
        dir.mkdir()
        return dir
    }

    return ensureDirectoryExist(File(dir.absolutePath + "1"))
}

private val BUFFER_SIZE = 8192//2048;

fun zip(
    sourcePath: String,
    destinationPath: String,
    destinationFileName: String,
    includeParentFolder: Boolean?
): Boolean {
    var destinationPath = destinationPath
    File(destinationPath).mkdirs()
    val fileOutputStream: FileOutputStream
    var zipOutputStream: ZipOutputStream? = null
    try {
        if (!destinationPath.endsWith("/")) destinationPath += "/"
        val destination = destinationPath + destinationFileName
        val file = File(destination)
        if (!file.exists()) file.createNewFile()

        fileOutputStream = FileOutputStream(file)
        zipOutputStream = ZipOutputStream(BufferedOutputStream(fileOutputStream))
        val sourceRootPath = if (includeParentFolder!!)
            File(sourcePath).parent + "/"
        else
            sourcePath

        zipFile(zipOutputStream, sourcePath, sourceRootPath)
    } catch (ioe: IOException) {
        logd(ioe)
        return false
    } finally {
        if (zipOutputStream != null)
            try {
                zipOutputStream.close()
            } catch (e: IOException) {
            }

    }
    return true
}

@Throws(IOException::class)
private fun zipFile(
    zipOutputStream: ZipOutputStream,
    sourcePath: String,
    sourceRootPath: String
) {
    val files = File(sourcePath)
    val fileList = files.listFiles()

    var entryPath = ""
    var input: BufferedInputStream
    for (file in fileList) {
        if (file.isDirectory) {
            zipFile(zipOutputStream, file.path, sourceRootPath)
        } else {
            val fileInputStream = FileInputStream(file.path)
            input = BufferedInputStream(fileInputStream, BUFFER_SIZE)
            entryPath = file.absolutePath.replace(sourceRootPath, "")

            val entry = ZipEntry(entryPath)
            zipOutputStream.putNextEntry(entry)
            copy(zipOutputStream, input)
            input.close()
        }
    }
}

fun unzip(sourceFile: File, destinationFolder: File): Boolean {
    var zis: ZipInputStream? = null

    try {
        zis = ZipInputStream(BufferedInputStream(FileInputStream(sourceFile)))
        var zipEntry: ZipEntry?

        while (true) {
            zipEntry = zis.nextEntry
            if (zipEntry == null) {
                break
            }

            var fileName = zipEntry.name
            val file = File(destinationFolder, fileName)
            val dir = if (zipEntry.isDirectory) file else file.parentFile

            if (!dir.isDirectory && !dir.mkdirs())
                throw FileNotFoundException("Invalid path: " + dir.absolutePath)
            if (zipEntry.isDirectory) continue
            val fout = FileOutputStream(file)
            try {
                copy(fout, zis)
            } finally {
                fout.close()
            }

        }
    } catch (ioe: IOException) {
        loge(ioe)
        return false
    } finally {
        if (zis != null)
            try {
                zis.close()
            } catch (e: IOException) {

            }

    }
    return true
}

val buffer = ByteArray(BUFFER_SIZE)

fun copy(output: OutputStream, input: InputStream) {
    var count: Int
    while (true) {
        count = input.read(buffer)
        if(count == -1){
            break
        }
        output.write(buffer, 0, count)
    }
}

fun saveToFile(destinationPath: String, data: String, fileName: String) {
    try {
        File(destinationPath).mkdirs()
        val file = File(destinationPath + fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileOutputStream = FileOutputStream(file, true)
        fileOutputStream.write((data + System.getProperty("line.separator")).toByteArray())

    } catch (ex: FileNotFoundException) {
        loge(ex)
    } catch (ex: IOException) {
        loge(ex)

    }

}