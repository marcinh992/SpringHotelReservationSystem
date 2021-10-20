package pl.overlook.springhotelreservation.domain.guest;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@AutoConfigureMockMvc
class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGuestsPage() throws Exception {

        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("listGuests"))
                .andExpect(view().name("guests"));
    }

    @Test
    public void handlePost() throws Exception {

        String postContent = "firstName=Marcin&lastName=Pype%C4%87&birthDate=1998-04-16";
        MockHttpServletRequestBuilder request = post("/newguest")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(postContent);

        mockMvc.perform(request)
        .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void handleDelete() throws Exception{

        MockHttpServletRequestBuilder request=
                get("/guest/delete/1");

        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/guests"));
    }

}