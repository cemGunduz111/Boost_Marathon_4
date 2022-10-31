package com.bilgeadam.service;

import com.bilgeadam.dto.request.CreateRequestDto;
import com.bilgeadam.dto.request.CreateStockRequestDto;
import com.bilgeadam.dto.request.UpdateRequestDto;
import com.bilgeadam.dto.request.UpdateStockRequestDto;
import com.bilgeadam.dto.response.ProductResponseDto;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.exception.ProductServiceException;
import com.bilgeadam.manager.IStockManager;
import com.bilgeadam.mapper.IProductMapper;
import com.bilgeadam.repository.IProductRepository;
import com.bilgeadam.repository.entity.Product;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService extends ServiceManager<Product, Long> {
    private final IProductRepository iProductRepository;

    private final IStockManager iStockManager;
    public ProductService(IProductRepository iProductRepository, IStockManager iStockManager) {
        super(iProductRepository);
        this.iProductRepository = iProductRepository;
        this.iStockManager = iStockManager;
    }

    public Product createProduct(CreateRequestDto dto) {
        Product product = iProductRepository.save(IProductMapper.INSTANCE.toProduct(dto));
        CreateStockRequestDto createStock = CreateStockRequestDto.builder()
                .productid(product.getId())
                .name(dto.getName())
                .brand(dto.getBrand())
                .build();
        iStockManager.createProduct(createStock);
        return product;
    }

    public List<ProductResponseDto> getAll() {
        List<Product> products = iProductRepository.findAll();
        return products.stream().map(list-> IProductMapper.INSTANCE.toProductResponseDto(list)).collect(Collectors.toList());

    }

    public Boolean updateProduct(UpdateRequestDto dto) {
        Optional<Product> product = iProductRepository.findOptionalById(dto.getId());

        if (product.isPresent()){
            product.get().setName(dto.getName());
            product.get().setPrice(dto.getPrice());
            product.get().setBrand(dto.getBrand());
            product.get().setPhoto(dto.getPhoto());
            product.get().setUpdated(System.currentTimeMillis());
            save(product.get());
            UpdateStockRequestDto updateStockRequestDto = UpdateStockRequestDto.builder()
                    .name(dto.getName())
                    .brand(dto.getBrand())
                    .productid(product.get().getId())
                    .build();
            iStockManager.updateProduct(updateStockRequestDto);
            return true;
        }else {
            throw new ProductServiceException(ErrorType.PRODUCT_NOT_UPDATED);
        }
    }

    public Boolean deleteProduct(Long id) {
        Optional<Product> product = iProductRepository.findOptionalById(id);
        if (product.isPresent()){
            deleteById(product.get().getId());
            iStockManager.deleteProduct(product.get().getId());
            return true;
        }else {
            throw new ProductServiceException(ErrorType.PRODUCT_NOT_DELETED);
        }
    }
}
