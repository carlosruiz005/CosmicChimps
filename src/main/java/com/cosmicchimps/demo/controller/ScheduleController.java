package com.cosmicchimps.demo.controller;

import com.cosmicchimps.demo.dto.NewSchedule;
import com.cosmicchimps.demo.service.ScheduleService;
import com.cosmicchimps.demo.util.ApiConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos Ruiz <Carlos Ruiz>
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(ApiConstants.ExamenControllerConstants.URL)
@ApiResponses(
        value = {
            @ApiResponse(
                    responseCode = ApiConstants.RESPONSE_CODE_200,
                    description = ApiConstants.RESPONSE_CODE_200_DESCRIPTION,
                    content = {
                        @Content(mediaType = ApiConstants.CONTENT_TYPE_JSON_APPLICATION),}
            ),
            @ApiResponse(
                    responseCode = ApiConstants.RESPONSE_CODE_404,
                    description = ApiConstants.RESPONSE_CODE_404_DESCRIPTION,
                    content = @Content
            ),}
)
public class ScheduleController {

    private static final Logger LOG = Logger.getLogger(ScheduleController.class.getName());

    @Autowired
    private ScheduleService scheduleService;

    /**
     * *
     * Add new scheduler.
     *
     * @param newSchedule
     * @return
     */
    @PostMapping(ApiConstants.ExamenControllerConstants.AddSchedule.URL)
    @Operation(
            summary = ApiConstants.ExamenControllerConstants.AddSchedule.URL_SUMMARY,
            description = ApiConstants.ExamenControllerConstants.AddSchedule.URL_DESCRIPTION
    )
    @ApiResponse(
            content = {
                @Content(schema = @Schema(implementation = String.class))}
    )
    public ResponseEntity<Map<String, String>> addSchedule(@RequestBody NewSchedule newSchedule) {
        return ResponseEntity.ok(this.scheduleService.create(newSchedule));
    }

    /**
     * *
     * Validate schedule date.
     *
     * @param scheduleId
     * @param date
     * @return
     */
    @GetMapping(ApiConstants.ExamenControllerConstants.ValidSchedule.URL)
    @Operation(
            summary = ApiConstants.ExamenControllerConstants.ValidSchedule.URL_SUMMARY,
            description = ApiConstants.ExamenControllerConstants.ValidSchedule.URL_DESCRIPTION
    )
    @ApiResponse(
            content = {
                @Content(schema = @Schema(implementation = String.class))}
    )
    public ResponseEntity<Map<String, Boolean>> isValidSchedule(@PathVariable String id, @PathVariable String date) {
        return ResponseEntity.ok(this.scheduleService.isValid(id, date));
    }

    /**
     * *
     * Create bad request response.
     *
     * @param constraintViolationException
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handle(ConstraintViolationException constraintViolationException) {
        Map<String, Object> err = new HashMap();
        Set<ConstraintViolation<?>> violations = constraintViolationException.getConstraintViolations();
        violations.forEach(v -> {
            err.put(
                    (v.getPropertyPath().toString().equals("") ? "error" : v.getPropertyPath().toString()),
                    v.getMessage() + (v.getInvalidValue() instanceof String ? ": " + v.getInvalidValue() : ""));
        });
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
