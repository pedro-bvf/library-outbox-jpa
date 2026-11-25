package com.mercadona.library.books.driving.controllers.error;

import com.mercadona.library.api.web_books.model.ErrorResourceResponse;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Handles Spring Boot's /error fallback (unmapped routes, servlet-level errors before any
// controller runs). Native replacement for fwkcna-lib-error-handler's CustomErrorController.
@Slf4j
@RestController
public class CustomErrorController implements ErrorController {

  @GetMapping("/error")
  public ResponseEntity<ErrorResourceResponse> handleError(HttpServletRequest request) {
    var errorMessage =
        Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_MESSAGE))
            .map(Object::toString)
            .orElse("");
    var errorUri =
        Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI))
            .map(Object::toString)
            .orElse("/");
    var status =
        Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
            .map(Object::toString)
            .map(Integer::valueOf)
            .orElse(null);

    if (status != null) {
      var reason = errorMessage.isEmpty() ? "" : " (Reason: " + errorMessage + ")";

      if (HttpStatus.NOT_FOUND.value() == status) {
        var error = "Unable to find resource: " + errorUri + reason;
        log.error(error);
        return DefaultExceptionHandler.build("G03", error, HttpStatus.NOT_FOUND);
      }

      var error = "Not handled error for URI: " + errorUri + reason;
      log.error(error);
      return DefaultExceptionHandler.build("G04", error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return DefaultExceptionHandler.build("G01", "Forced error", HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
