package com.desafios.cripftografia.dtos;

import com.desafios.cripftografia.entities.client.AcessLevel;

public record RegisterDto(String loggin, String password, AcessLevel level) {
}
