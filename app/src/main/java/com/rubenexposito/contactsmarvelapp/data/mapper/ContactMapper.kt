package com.rubenexposito.contactsmarvelapp.data.mapper

import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse
import com.rubenexposito.contactsmarvelapp.domain.model.Contact

class ContactMapper {
    fun mapMarvel(response: GetCharactersResponse): MutableList<Contact> =
        response.data.results.map {
            Contact(
                "marvel:${it.id}",
                it.name,
                it.thumbnail.path + "." + it.thumbnail.extension
            )
        }.toMutableList()
}