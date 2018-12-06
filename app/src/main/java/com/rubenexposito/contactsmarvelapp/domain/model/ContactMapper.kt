package com.rubenexposito.contactsmarvelapp.domain.model

import com.rubenexposito.contactsmarvelapp.data.dto.GetCharactersResponse

class ContactMapper {
    fun mapMarvel(response: GetCharactersResponse): List<Contact> =
        response.data.results.map {
            Contact(it.name, it.thumbnail.path)
        }
}