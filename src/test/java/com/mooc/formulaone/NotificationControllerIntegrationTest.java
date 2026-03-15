package com.mooc.formulaone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.formulaone.models.Notification;
import com.mooc.formulaone.models.NotificationStatus;
import com.mooc.formulaone.models.NotificationType;
import com.mooc.formulaone.models.User;
import com.mooc.formulaone.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
/**
 * Verifie les endpoints REST de creation et de consultation des notifications.
 */
class NotificationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    /**
     * Verifie qu'une notification creee via l'API apparait bien dans la liste retournee.
     */
    @Test
    void shouldCreateNotificationAndListIt() throws Exception {
        User user = new User();
        user.setUsername("eve");
        user.setEmail("eve@example.com");
        user.setPasswordHash("hash");
        Long userId = userService.create(user);

        Notification notification = new Notification();
        notification.setType(NotificationType.TASK_ASSIGNED);
        notification.setStatus(NotificationStatus.SENT);
        notification.setMessage("A new task has been assigned to you");
        notification.setSentAt(Timestamp.from(Instant.parse("2026-03-14T12:00:00Z")));
        notification.setUser(userService.findById(userId));

        mockMvc.perform(post("/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(notification)))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/notifications"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type", is("TASK_ASSIGNED")))
                .andExpect(jsonPath("$[0].status", is("SENT")))
                .andExpect(jsonPath("$[0].user.email", is("eve@example.com")));
    }
}
