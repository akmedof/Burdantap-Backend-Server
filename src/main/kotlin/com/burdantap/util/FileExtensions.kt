package com.burdantap.util

import com.burdantap.domain.model.endpoint.FileDirectionPath
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import java.io.File

fun fileProductDirectionCreate(storeSlug: String, modelCode: String, colorSlug: String): File {
    val uploadDir = File("${FileDirectionPath.Stores.path}/$storeSlug/${FileDirectionPath.Products.path}/$modelCode/$colorSlug")
    if (!uploadDir.exists()) {
        uploadDir.mkdirs()
    }
    return uploadDir
}

fun createProductImagePath(
    storeSlug: String,
    modelCode: String,
    colorSlug: String,
    imageName: String,
):String{
    return "/${FileDirectionPath.Gallery.path}/${FileDirectionPath.Products.path}/$storeSlug/$modelCode/$colorSlug/$imageName"
}

fun getProductImagePath(
    storeSlug: String,
    modelCode: String,
    colorSlug: String,
    imageName: String,
):File{
    return File("./${FileDirectionPath.Stores.path}/$storeSlug/${FileDirectionPath.Products.path}/$modelCode/$colorSlug/$imageName")
}

suspend fun getCustomerPhotoImage(call: ApplicationCall, image: String) {
    call.respondFile(File("./${FileDirectionPath.Uploads}/${FileDirectionPath.Customers}/$image"))
}

fun getFileUrl(call: ApplicationCall, direction: String): String =
    "${call.request.origin.scheme}://${call.request.host()}:${call.request.port()}/${FileDirectionPath.Gallery.path}/$direction"

fun fileDirectionStoreCreate(
    storeSlug: String,
    productModelCode: String,
    productDetailId: String,
): File {
    val uploadDir = File("${FileDirectionPath.Uploads.path}/${FileDirectionPath.Stores.path}/$storeSlug/$productModelCode/$productDetailId")
    if (!uploadDir.exists()) {
        uploadDir.mkdirs()
    }
    return uploadDir
}

fun readProductImageUrlAndSize(
    storeSlug: String,
    productModelCode: String,
    productDetailId: String,
    file: File,
    call: ApplicationCall,
    part: PartData.FileItem,
    onFileFeatures: (String, Long) -> Unit
) {
    val targetFile = getProductImagesFileTarget(
        file = file,
        fileName = part.originalFileName.toString(),
        contentType = part.contentType ?: ContentType.Image.JPEG
    )

    var fileSize = 0L
    part.streamProvider().use { input ->
        targetFile?.outputStream()?.buffered()?.use { output ->
            fileSize = input.copyTo(output)
        }
    }
    val photoUrl = getFileUrl(
        call = call,
        direction = "/$storeSlug/$productModelCode/$productDetailId/${part.originalFileName}.${part.contentType?.contentSubtype}"
    )
    onFileFeatures(photoUrl, fileSize)
}

fun getProductImagesFileTarget(
    file: File,
    fileName: String,
    contentType: ContentType
): File? =
    if (contentType == ContentType.Image.JPEG || contentType == ContentType.Image.PNG) {
        File(file, "${fileName}.${contentType.contentSubtype}")
    } else null

//fun getImageFileTarget(fileName: String, contentType: ContentType): File? =
//    if (contentType == ContentType.Image.JPEG || contentType == ContentType.Image.PNG) {
//        deleteImagesByFileName(
//            directoryPath = FileDirectionPath.Customers.path,
//            fileName = fileName
//        )
//        File(fileDirectionCreate(FileDirectionPath.Customers.path), "${fileName}.${contentType.contentSubtype}")
//    } else null

fun deleteImagesByFileName(directoryPath: String, fileName: String) {
    val directory = File("${FileDirectionPath.Uploads.path}/$directoryPath")
    if (directory.exists() && directory.isDirectory) {
        val matchingFiles = directory.listFiles { file ->
            file.isFile && file.nameWithoutExtension == fileName
        }
        matchingFiles?.forEach { file ->
            if (file.delete()) {
                println("Deleted: ${file.name}")
            } else {
                println("Failed to delete: ${file.name}")
            }
        }
    } else {
        println("Directory does not exist or is not a directory.")
    }
}