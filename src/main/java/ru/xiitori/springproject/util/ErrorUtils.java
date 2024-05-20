package ru.xiitori.springproject.util;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.List;

public class ErrorUtils {
   public static ResponseEntity<ErrorResponse> getResponse(BindingResult bindingResult) {
       if (!bindingResult.hasErrors()) {
           return null;
       }
       List<String> errors = bindingResult.getAllErrors().stream().
               map(DefaultMessageSourceResolvable::getDefaultMessage).
               toList();
       return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
   }
}
