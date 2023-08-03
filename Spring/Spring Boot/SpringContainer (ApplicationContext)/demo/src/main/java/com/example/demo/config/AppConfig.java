/*
Resources:
[1] https://chat.openai.com/c/5971add2-c40c-4fc2-84ef-dc234fa90400 [search phrase: "rules of bean resolution"]
*/

package com.example.demo.config;

import com.example.demo.model.Address;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public String getName() {
        return "Peter";
    }

    //This address bean is chosen over the Address class annotated with @Component
    //due to the rules of bean resolution. See [1].
    @Bean
    public Address getAddress() {
        return new Address();
    }
}
