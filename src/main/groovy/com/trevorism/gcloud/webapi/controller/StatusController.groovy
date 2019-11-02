package com.trevorism.gcloud.webapi.controller

import com.google.gson.Gson
import com.trevorism.http.HttpClient
import com.trevorism.http.JsonHttpClient
import io.swagger.annotations.Api

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Api("Status Operations")
@Path("status")
class StatusController {

    String EVENT_BASE_URL = "https://event.trevorism.com";

    @GET
    @Path("topic")
    @Produces(MediaType.APPLICATION_JSON)
    List<String> getTopics(){
        HttpClient httpClient = new JsonHttpClient()
        Gson gson = new Gson()
        String json = httpClient.get(EVENT_BASE_URL + "/admin/topic")
        gson.fromJson(json, List)
    }

}
