package org.acme.rabbitmq.model

import io.quarkus.runtime.annotations.RegisterForReflection

@RegisterForReflection
class Quote(id:String, price:Int) {

    private val id: String
    private val price: Int

    init {
        this.id = id
        this.price = price
    }

    @Override
    override fun toString(): String {
        return "Quote{" +
                "id='" + id + '\'' +
                ", price=" + price +
                '}'
    }

}