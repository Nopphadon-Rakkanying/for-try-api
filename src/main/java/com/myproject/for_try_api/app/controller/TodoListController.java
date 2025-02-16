package com.myproject.for_try_api.app.controller;

import com.myproject.for_try_api.app.model.dto.TodoListRequest;
import com.myproject.for_try_api.app.model.dto.TodoListResponse;
import com.myproject.for_try_api.app.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("myapp")
public class TodoListController{
    private final TodoListService todoListService;

    @Autowired
    public TodoListController(TodoListService todoListService){
        this.todoListService = todoListService;
    }

    @GetMapping(path = "/todo",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TodoListResponse>> getTodoList(){
        List<TodoListResponse> result = todoListService.getTodoList();
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/todo/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TodoListResponse> getTodo(@PathVariable int id){
        TodoListResponse response = todoListService.getTodo(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/todo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> createTodo(@RequestBody TodoListRequest todoListRequest){
        String response = todoListService.createTodo(todoListRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(path = "/todo",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> updateTodo(@RequestBody TodoListRequest todoListRequest){
        String response = todoListService.updateTodo(todoListRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(path = "/todo/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<String> deleteTodo(@PathVariable int id){
        String response = todoListService.deleteTodo(id);
        return ResponseEntity.ok(response);
    }

}
