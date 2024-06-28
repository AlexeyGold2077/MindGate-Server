package com.alexeygold2077.api.proxyapi.DTO;

import java.util.List;

public record ResponseDefault(String id,
                              String object,
                              String created,
                              String model,
                              List<Choice> choices,
                              Usage usage,
                              String system_fingerprint) {}