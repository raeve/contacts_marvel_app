package com.rubenexposito.contactsmarvelapp.domain.model

import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse

class ContactMapper {
    fun mapMarvel(response: GetCharactersResponse): MutableList<Contact> =
        response.data.results.map {
            Contact(it.name, it.thumbnail.path + "." + it.thumbnail.extension)
        }.toMutableList()
}