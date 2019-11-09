package com.trevorism.status

import org.junit.Test

/**
 * @author tbrooks
 */
class RootControllerTest {

    @Test
    void testRootControllerEndpoints() {
        RootController rootController = new RootController()
        assert rootController.displayHelpLink().contains("/api/help")
    }

    @Test
    void testRootControllerPing() {
        RootController rootController = new RootController()
        assert rootController.ping() == "pong"
    }

    @Test
    void testHelpPage() {
        RootController rootController = new RootController()
        assert rootController.help()
    }
}
