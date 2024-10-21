package org.acme.rabbitmq.producer

import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter

import java.util.UUID

@Path("/quotes")
class QuotesResource {

    @Channel("quote-requests")
    lateinit var quoteRequestEmitter: Emitter<String>

    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    fun createRequest(): String {
        val uuid = UUID.randomUUID()
        quoteRequestEmitter.send(uuid.toString())
        return uuid.toString()
    }
}