package org.acme.rabbitmq.producer

import io.smallrye.mutiny.Multi
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.acme.rabbitmq.model.Quote
import org.eclipse.microprofile.reactive.messaging.Channel
import org.eclipse.microprofile.reactive.messaging.Emitter

import java.util.UUID

@Path("/quotes")
class QuotesResource {

    @Channel("quote-requests")
    lateinit var quoteRequestEmitter: Emitter<String>

    @Channel("quotes")
    lateinit var quotes: Multi<Quote>

    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    fun createRequest(): String {
        val uuid = UUID.randomUUID()
        quoteRequestEmitter.send(uuid.toString())
        return uuid.toString()
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    fun stream(): Multi<Quote> {
        return quotes
    }
}