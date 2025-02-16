package com.myproject.for_try_api.app.service;

import com.myproject.for_try_api.app.model.dto.TodoListRequest;
import com.myproject.for_try_api.app.model.dto.TodoListResponse;
import java.util.List;

public interface TodoListService {
    List<TodoListResponse> getTodoList();
    TodoListResponse getTodo(int id);
    String createTodo(TodoListRequest todoListRequest);
    String updateTodo(TodoListRequest todoListRequest);
    String deleteTodo(int id);
}
