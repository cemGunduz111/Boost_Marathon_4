package com.bilgeadam.mapper;

import com.bilgeadam.dto.request.CreateStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.StockResponseDto;
import com.bilgeadam.repository.entity.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IStockMapper {

    IStockMapper INSTANCE = Mappers.getMapper(IStockMapper.class);

    Stock toStock (final CreateStockRequestDto dto);

    Stock toStock(final UpdateStockRequestDto dto);

    StockResponseDto toStockResponseDto(final Stock stock);
}
