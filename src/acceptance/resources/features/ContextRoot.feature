Feature: Context Root of this API
  In order to use the status API, it must be available

  Scenario: Root of the API HTTPS
    Given the application is alive
    When I navigate to "https://status-dot-trevorism-eventhub.appspot.com/api"
    Then then a link to the help page is displayed

  Scenario: Ping HTTPS
    Given the application is alive
    When I ping the application deployed to "https://status-dot-trevorism-eventhub.appspot.com/api"
    Then pong is returned, to indicate the service is alive

