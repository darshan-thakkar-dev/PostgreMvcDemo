package com.example.demo.util;

import org.mapstruct.Context;

public interface IBaseMapper<DTO, DOMAIN> {

	DTO domainToDto(DOMAIN domain, @Context CycleDependencyResolver resolver);

	DOMAIN dtoToDomain(DTO dto, @Context CycleDependencyResolver resolver);
}
