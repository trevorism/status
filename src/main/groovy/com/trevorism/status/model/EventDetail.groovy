package com.trevorism.status.model

import groovy.transform.ToString

@ToString
class EventDetail {

    String topic
    long id
    Date datecreated
    String correlationid
    String eventDetailsForDisplay
}
