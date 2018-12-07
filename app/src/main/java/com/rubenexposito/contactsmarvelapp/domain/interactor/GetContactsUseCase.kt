package com.rubenexposito.contactsmarvelapp.domain.interactor

import com.rubenexposito.contactsmarvelapp.domain.model.Contact
import com.rubenexposito.contactsmarvelapp.domain.ContactsRepository
import com.rubenexposito.contactsmarvelapp.domain.MarvelRepository
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class GetContactsUseCase(
    private val marvelRepository: MarvelRepository,
    private val contactsRepository: ContactsRepository,
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) {

    private var subscription: Disposable = Disposables.empty()

    fun execute(
        onComplete: (MutableList<Contact>) -> Unit,
        onError: (Throwable) -> Unit,
        offset: Int = 0, limit: Int = 50
    ) {
        subscription = marvelRepository.getCharacters(limit, offset)
            .map { it.addAll(contactsRepository.getPhoneContacts())
                it.sortedBy { contact -> contact.name }.toMutableList()
            }
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