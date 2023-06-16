package ru.test.buyandsell.Configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

public class OpenApiDef {
    @OpenAPIDefinition(
            info = @Info(
                    title = "BuyAndSell",
                    description = "Avito clone", version = "1.0.0",
                    contact = @Contact(
                            name = "Kirill Kalinkin",
                            email = "kalinkin_kir@bk.ru",
                            url = "https://github.com/KalinkinKir"
                    )
            )
    )
    public class OpenApiConfig {}
}
