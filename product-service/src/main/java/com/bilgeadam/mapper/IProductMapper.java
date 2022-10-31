package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.response.ProductResponseDto;
import com.bilgeadam.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {

    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product toProduct (final CreateRequestDto dto);

    ProductResponseDto toProductResponseDto(final Product product);

    Product toProduct(final UpdateRequestDto dto);


}
