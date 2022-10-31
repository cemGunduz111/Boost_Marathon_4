package com.bilgeadam.service;

import com.bilgeadam.dto.request.AddStockRequestDto;
import com.bilgeadam.dto.request.CreateStockRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.StockResponseDto;
import com.bilgeadam.exception.ErrorMessage;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.StockServiceException;
import com.bilgeadam.mapper.IStockMapper;
import com.bilgeadam.repository.IStockRepository;
import com.bilgeadam.repository.entity.Stock;
import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService extends ServiceManager<Stock, Long> {
    private final IStockRepository iStockRepository;


    public StockService(IStockRepository iStockRepository) {
        super(iStockRepository);
        this.iStockRepository = iStockRepository;
    }

    public List<StockResponseDto> getAllStock() {
        List<Stock> stock = iStockRepository.findAll();
        return stock.stream().map(list-> IStockMapper.INSTANCE.toStockResponseDto(list)).collect(Collectors.toList());
    }

    public Boolean createStock(CreateStockRequestDto dto) {
        Stock stock = iStockRepository.save(IStockMapper.INSTANCE.toStock(dto));
        return true;
    }

    public Boolean updateStock(UpdateStockRequestDto dto) {
        Optional<Stock> stock = iStockRepository.findOptionalByProductid(dto.getProductid());
        if (stock.isPresent()){
            stock.get().setName(dto.getName());
            stock.get().setBrand(dto.getBrand());
            stock.get().setUpdated(System.currentTimeMillis());
            save(stock.get());
            return true;
        }else
            throw new StockServiceException(ErrorType.STOCK_NOT_UPDATED);
    }

    public Boolean deleteStock(Long id) {
        Optional<Stock> stock = iStockRepository.findOptionalByProductid(id);
        if (stock.isPresent()){
            deleteById(stock.get().getId());
            return true;
        }else
            throw new StockServiceException(ErrorType.STOCK_NOT_DELETED);
    }

    public Boolean addStock(AddStockRequestDto dto) {
        Optional<Stock> stock = iStockRepository.findOptionalByProductid(dto.getProductid());
        Integer stocknow = stock.get().getStock();

        if (stock.isPresent()){
            if (stocknow > dto.getStock() && dto.getStock() > 0){
                stock.get().setStock(dto.getStock());
                stock.get().setStockOutUpdate(System.currentTimeMillis());
                stock.get().setStatus(Status.IN_STOCK);
            } else if (stocknow < dto.getStock() && dto.getStock() > 0) {
                stock.get().setStock(dto.getStock());
                stock.get().setStockInUpdate(System.currentTimeMillis());
                stock.get().setStatus(Status.IN_STOCK);
            }else if(dto.getStock() == 0) {
                stock.get().setStock(dto.getStock());
                stock.get().setStockOutUpdate(System.currentTimeMillis());
                stock.get().setStatus(Status.NOT_IN_STOCK);
            } else
                throw new StockServiceException(ErrorType.STOCK_NOT_DEFINED);

            save(stock.get());
            return true;
        }else
            throw new StockServiceException(ErrorType.STOCK_NOT_ADDED);
    }
}
