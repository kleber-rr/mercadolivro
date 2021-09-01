package com.mercadolivro.exceptions

import com.mercadolivro.controller.response.ErrorResponse
import com.mercadolivro.controller.response.FieldErrorResponse
import com.mercadolivro.enums.Errors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.NOT_FOUND
        val erro = ErrorResponse(
            status.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(erro, status)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.BAD_REQUEST
        val erro = ErrorResponse(
            status.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(erro, status)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest): ResponseEntity<ErrorResponse> {
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val erro = ErrorResponse(
            status.value(),
            Errors.ML0001.message,
            Errors.ML0001.code,
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "invalid", it.field) }
        )
        return ResponseEntity(erro, status)
    }

}