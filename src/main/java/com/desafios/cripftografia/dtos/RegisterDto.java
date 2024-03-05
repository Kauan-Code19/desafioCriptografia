package com.desafios.cripftografia.dtos;

import com.desafios.cripftografia.entities.client.AcessLevel;

public record RegisterDto(String login, String password, AcessLevel level) {
}
