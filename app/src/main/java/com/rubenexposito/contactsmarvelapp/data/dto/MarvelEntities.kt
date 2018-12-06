package com.rubenexposito.contactsmarvelapp.data.dto

data class GetCharactersResponse(val data: GetCharactersResponseData)

data class GetCharactersResponseData(val results: List<MarvelCharacter>)

data class MarvelCharacter(val id: Long, val name: String, val thumbnail: MarvelCharacterThumbnail)

data class MarvelCharacterThumbnail(val path: String, val extension: String)