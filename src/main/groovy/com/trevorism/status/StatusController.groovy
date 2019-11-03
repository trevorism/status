package com.trevorism.status

import com.google.gson.Gson
import com.trevorism.http.HttpClient
import com.trevorism.http.JsonHttpClient
import com.trevorism.status.model.EventDetail
import io.swagger.annotations.Api

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Api("Status Operations")
@Path("status")
class StatusController {

    String EVENT_BASE_URL = "https://event.trevorism.com"
    String DATASTORE_BASE_URL = "https://datastore.trevorism.com"

    private HttpClient httpClient = new JsonHttpClient()
    private Gson gson = new Gson()

    @GET
    @Path("topic")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> getTopics(){
        String topicsJson = httpClient.get(EVENT_BASE_URL + "/admin/topic")
        String storedDataJson = httpClient.get(DATASTORE_BASE_URL + "/api")

        List<String> topics = gson.fromJson(topicsJson, List)
        List<String> datastores = gson.fromJson(storedDataJson, List)

        topics.remove("testresult")

        return topics.intersect(datastores)
    }

    @GET
    @Path("topic/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<EventDetail> getEventDetails(@PathParam("id") String topicName){
        String eventsJson = httpClient.get(DATASTORE_BASE_URL + "/api/${topicName}")
        List allEvents = gson.fromJson(eventsJson, List)
        return allEvents.collect {
            String backToJson = gson.toJson(it)
            EventDetail eventDetail = gson.fromJson(backToJson, EventDetail)
            eventDetail.eventDetailsForDisplay = backToJson
            eventDetail.topic = topicName
            return eventDetail
        }
    }

}
