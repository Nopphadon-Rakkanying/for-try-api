package com.myproject.for_try_api.app.controller;

import com.myproject.for_try_api.app.model.dto.TodoListRequest;
import com.myproject.for_try_api.app.model.dto.TodoListResponse;
import com.myproject.for_try_api.app.service.TodoListService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoListControllerTest {
    @Mock
    private TodoListService todoListService;

    @InjectMocks
    private TodoListController todoListController;

    private TodoListResponse todoListResponse;
    private TodoListRequest todoListRequest;

    @BeforeEach
    void setUp() {
        todoListResponse=TodoListResponse.builder()
                .id(1)
                .title("title")
                .build();

        todoListRequest= TodoListRequest.builder()
                .id(1)
                .title("title")
                .build();
    }

    @Test
    void getTodoList() {
        when(todoListService.getTodoList()).thenReturn(List.of(todoListResponse));

        ResponseEntity<List<TodoListResponse>> response = todoListController.getTodoList();

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getTodo() {
        when(todoListService.getTodo(anyInt())).thenReturn(todoListResponse);

        ResponseEntity<TodoListResponse> response = todoListController.getTodo(1);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void createTodo() {
        when(todoListService.createTodo(any())).thenReturn("succes");

        ResponseEntity<String> response = todoListController.createTodo(todoListRequest);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void updateTodo() {
        when(todoListService.updateTodo(any())).thenReturn("succes");

        ResponseEntity<String> response = todoListController.updateTodo(todoListRequest);

        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void deleteTodo() {
        when(todoListService.deleteTodo(anyInt())).thenReturn("succes");

        ResponseEntity<String> response = todoListController.deleteTodo(1);

        assertEquals(200, response.getStatusCodeValue());
    }
}
