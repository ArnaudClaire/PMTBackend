package com.mooc.formulaone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mooc.formulaone.models.Project;
import com.mooc.formulaone.models.Task;
import com.mooc.formulaone.models.TaskPriority;
import com.mooc.formulaone.models.TaskStatus;
import com.mooc.formulaone.models.User;
import com.mooc.formulaone.services.ProjectService;
import com.mooc.formulaone.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
/**
 * Verifie les endpoints REST de creation et de consultation des taches.
 */
class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    /**
     * Verifie qu'une tache creee via l'API peut ensuite etre recuperee
     * avec ses informations principales et ses relations utiles.
     */
    @Test
    void shouldCreateTaskAndFetchItThroughApi() throws Exception {
        User creator = new User();
        creator.setUsername("bob");
        creator.setEmail("bob@example.com");
        creator.setPasswordHash("hash");
        Long creatorId = userService.create(creator);

        Project project = new Project();
        project.setName("Board");
        project.setDescription("Task board");
        project.setOwner(userService.findById(creatorId));
        Long projectId = projectService.create(project);

        Task task = new Task();
        task.setTitle("Create API");
        task.setDescription("Implement PMT backend");
        task.setStatus(TaskStatus.TODO);
        task.setPriority(TaskPriority.HIGH);
        task.setDueDate(LocalDate.of(2026, 3, 31));
        task.setProject(projectService.findById(projectId));
        task.setCreatedBy(userService.findById(creatorId));

        String responseBody = mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(task)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        mockMvc.perform(get("/tasks/{id}", Long.valueOf(responseBody)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Create API")))
                .andExpect(jsonPath("$.status", is("TODO")))
                .andExpect(jsonPath("$.priority", is("HIGH")))
                .andExpect(jsonPath("$.project.name", is("Board")))
                .andExpect(jsonPath("$.createdBy.email", is("bob@example.com")));
    }
}
