package com.utn.TPFUDEE.Config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class conf {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
