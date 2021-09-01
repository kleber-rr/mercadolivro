package com.mercadolivro.service

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val repository: PurchaseRepository,
    private val eventPublisher: ApplicationEventPublisher
) {

    fun create(purchaseModel: PurchaseModel){
        repository.save(purchaseModel)
        eventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        repository.save(purchaseModel)
    }

}
