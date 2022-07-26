package com.otus.homework.book_catalog_with_mongodb.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

abstract class UniqueIdentifier {
    @Id
    var id: String = ObjectId().toHexString()
}