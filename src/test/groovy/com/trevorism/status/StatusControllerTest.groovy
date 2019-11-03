package com.trevorism.status

import com.trevorism.http.HttpClient
import com.trevorism.status.model.EventDetail
import org.junit.Test

class StatusControllerTest {

    private StatusController statusController

    StatusControllerTest(){
        statusController = new StatusController()
    }

    @Test
    void testGetTopics() {
        statusController.httpClient = [get: {url -> return '["topic1","topic2"]'}] as HttpClient
        List<String> topics = statusController.getTopics()
        assert topics
        assert topics.size() == 2
        assert topics[0] == "topic1"
        assert topics[1] == "topic2"
    }

    @Test
    void testGetEventDetails() {
        statusController.httpClient = [get: {url -> return '[{"id":4505759559712768,"projectname":"trevorism-gcloud","servicename":"cinvoke","datecreated":"2018-06-16T02:00:48.249Z"},{"id":4536157173972992,"projectname":"trevorism-gcloud","servicename":"cinvoke","correlationid":"test1","datecreated":"2018-08-27T02:33:15.273Z"}]'}] as HttpClient
        List<EventDetail> details = statusController.getEventDetails("workcomplete")

        assert details
        assert details.size() == 2
        assert details[0].getTopic() == "workcomplete"
        assert details[1].getTopic() == "workcomplete"

        assert details[0].getDatecreated()
        assert details[1].getDatecreated()

        assert !details[0].correlationid
        assert details[1].correlationid == "test1"

    }
}
