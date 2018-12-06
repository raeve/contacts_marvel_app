package com.rubenexposito.contactsmarvelapp.domain

import com.rubenexposito.contactsmarvelapp.data.ContactsRepository
import com.rubenexposito.contactsmarvelapp.data.MarvelRepository
import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables

class GetContactsUseCase(
    private val contactsRepository: ContactsRepository,
    private val marvelRepository: MarvelRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) {

    private var subscription = Disposables.empty()

    fun execute(onComplete: (MutableList<Contact>) -> Unit, onError: (Throwable) -> Unit) {
        subscription = contactsRepository.getContacts()
            .mergeWith(marvelRepository.getCharacters())
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }

    fun clear() {
        if (!subscription.isDisposed) {
            subscription.dispose()
        }
    }
}