package com.utn.TPFUDEE.Utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class EntityURLBuilder {

    public static URI buildURL(final String entity, final Integer id){
        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path(("/{entity}/{id}"))
                .buildAndExpand(entity, id)
                .toUri();
    }
}
