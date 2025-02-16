package com.myproject.for_try_api.app.service.impl;

import com.myproject.for_try_api.app.model.dbs.TodoListModel;
import com.myproject.for_try_api.app.model.dto.TodoListRequest;
import com.myproject.for_try_api.app.model.dto.TodoListResponse;
import com.myproject.for_try_api.app.repositories.TodoListRepositories;
import com.myproject.for_try_api.app.service.Impl.TodoListServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest
class TodoListServiceImplTest {
    @InjectMocks
    private TodoListServiceImpl todoListServiceImpl;

    @Mock
    private TodoListRepositories todoListRepositories;

    private TodoListModel todoListModel;
    private TodoListRequest todoListRequest;

    @BeforeEach
    void setUp() {
        todoListModel = TodoListModel.builder()
                .id(1)
                .title("title")
                .build();

        todoListRequest = TodoListRequest.builder()
                .id(1)
                .title("title")
                .build();
    }

    @Test
    void getTodoListSuccess(){
        when(todoListRepositories.findAll())
                .thenReturn(List.of(todoListModel));

        List<TodoListResponse> response = todoListServiceImpl.getTodoList();
        assert response.size() == 1;
        assertEquals(1, response.get(0).getId());
        assertEquals("title", response.get(0).getTitle());
    }

    @Test
    void getTodoListNotFound(){
        when(todoListRepositories.findAll())
                .thenReturn(List.of());

        List<TodoListResponse> response = todoListServiceImpl.getTodoList();
        assert response.size() == 0;
    }

    @Test
    void getTodoListException(){
        doThrow(new RuntimeException("Simulated error")).when(todoListRepositories).findAll();

        List<TodoListResponse> response = todoListServiceImpl.getTodoList();

        assertEquals(List.of(), response);
    }

    @Test
    void getTodoSuccess(){
        when(todoListRepositories.findById(anyInt()))
                .thenReturn(Optional.of(todoListModel));

        TodoListResponse response = todoListServiceImpl.getTodo(1);
        assertEquals(1, response.getId());
        assertEquals("title", response.getTitle());
    }

    @Test
    void getTodoNotFound(){
        when(todoListRepositories.findById(anyInt()))
                .thenReturn(Optional.empty());

        TodoListResponse response = todoListServiceImpl.getTodo(1);
        assertNull(response);
    }

    @Test
    void getTodoException(){
        doThrow(new RuntimeException("Simulated error")).when(todoListRepositories).findById(anyInt());

        TodoListResponse response = todoListServiceImpl.getTodo(1);

        assertNull(response);
    }

    @Test
    void createTodoSuccess(){
        String response = todoListServiceImpl.createTodo(todoListRequest);
        assertEquals("create success", response);
    }

    @Test
    void createTodoException(){
        doThrow(new RuntimeException("Simulated error")).when(todoListRepositories).saveAndFlush(any());

        String result = todoListServiceImpl.createTodo(todoListRequest);

        assertNull(result);
    }

    @Test
    void updateTodoSuccess(){
        when(todoListRepositories.findById(anyInt()))
                .thenReturn(Optional.of(todoListModel));

        String response = todoListServiceImpl.updateTodo(todoListRequest);
        assertEquals("update success", response);
    }

    @Test
    void updateTodoNotFound(){
        when(todoListRepositories.findById(anyInt()))
                .thenReturn(Optional.empty());

        String response = todoListServiceImpl.updateTodo(todoListRequest);
        assertEquals("not found", response);
    }

    @Test
    void updateTodoException(){
        doThrow(new RuntimeException("Simulated error")).when(todoListRepositories).findById(anyInt());

        String response = todoListServiceImpl.updateTodo(todoListRequest);

        assertNull(response);
    }

    @Test
    void deleteTodoSuccess(){
        String response = todoListServiceImpl.deleteTodo(1);
        assertEquals("delete success", response);
    }

    @Test
    void deleteTodoException(){
        doThrow(new RuntimeException("Simulated error")).when(todoListRepositories).deleteById(1);

        String result = todoListServiceImpl.deleteTodo(1);

        assertNull(result);
    }
}
