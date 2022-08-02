package com.otus.homework.model.mongo

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id

abstract class UniqueIdentifier {
    @Id
    var id: String = ObjectId().toHexString()
}