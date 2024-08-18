package com.projet.app.dto;

public record LoginResponse(String jwt,String role,Long userId,String numeroCarte) {

}
