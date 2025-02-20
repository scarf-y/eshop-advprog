package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class HomeControllerTest {

    @Test
    void testHomePageReturnsHomeView() {
        HomeController homeController = new HomeController();
        Model model = mock(Model.class);
        String viewName = homeController.homePage(model);
        assertEquals("home", viewName);
    }
}
